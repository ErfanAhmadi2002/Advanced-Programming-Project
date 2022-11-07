package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.Tweet;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class TweetDatabase implements DataBase<Tweet>{
    static private final Logger logger = LogManager.getLogger(TweetDatabase.class);
    private final Object syncObject;

    public TweetDatabase() {
        syncObject = new Object();
    }

    @Override
    public Tweet Load(int id) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            try {
                File file = new File(config.getProperty(String.class , "TweetDirectory") + "\\" + id + ".json");
                FileReader fileReader = new FileReader(file);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Tweet tweet = gson.fromJson(fileReader , Tweet.class);
                logger.info("the tweet with id : " + id + " was loaded successfully");
                fileReader.close();
                return tweet;
            }catch (Throwable throwable){
                File file = new File(config.getProperty(String.class , "CommentDirectory") + "\\" + id + ".json");
                FileReader fileReader = new FileReader(file);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Tweet tweet = gson.fromJson(fileReader, Tweet.class);
                fileReader.close();
                return tweet;
            }
        }
    }

    @Override
    public void update(Tweet tweet) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "TweetDirectory") + "\\" + tweet.getId() + ".json" );
            if (!file.exists()){
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            logger.info("the tweet with id : " + tweet.getId() + " was updated");
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
                File file = new File(config.getProperty(String.class , "TweetDirectory") + "\\" + id + ".json");
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

    public ArrayList<Integer> getMainTweetsIdList(){
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "TweetDirectory"));
        ArrayList<Integer> list = new ArrayList<>();
        for (File file1: Objects.requireNonNull(file.listFiles())) {
            String name = file1.getName();
            int length = name.length();
            name = name.substring(0 , length - 5);
            list.add(Integer.valueOf(name));
        }
        Collections.shuffle(list);
        return list;
    }

}
