package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.Tweet;

import java.io.*;
import java.util.Scanner;

public class CommentDatabase implements DataBase<Tweet>{
    static private final Logger logger = LogManager.getLogger(CommentDatabase.class);
    private final Object syncObject;

    public CommentDatabase() {
        syncObject = new Object();
    }

    @Override
    public Tweet Load(int id) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "CommentDirectory") + "\\" + id + ".json");
            FileReader fileReader = new FileReader(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Tweet tweet = gson.fromJson(fileReader , Tweet.class);
            fileReader.close();
            logger.info("the comment with id : " + id + " was loaded successfully");
            return tweet;
        }
    }

    @Override
    public void update(Tweet tweet) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "CommentDirectory") + "\\" + tweet.getId() + ".json" );
            if (!file.exists()){
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            logger.info("the comment with id : " + tweet.getId() + " was updated");
            gson.toJson(tweet, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    @Override
    public void delete(int id) {
        synchronized (syncObject){
            try {
                Config config = Config.getConfig("dataBaseAddress");
                File file = new File(config.getProperty(String.class , "CommentDirectory") + "\\" + id + ".json");
                file.delete();
            }catch (Throwable throwable){
            }
        }
    }

    @Override
    public int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeTweet"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastTweetId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastTweetId2 = LastTweetId + 1;
        printStream.println("NumberOfTweets: " + LastTweetId2);
        printStream.flush();
        printStream.close();
        return LastTweetId;
    }
}
