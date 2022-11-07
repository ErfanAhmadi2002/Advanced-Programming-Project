package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.User;

import java.io.*;
import java.util.Scanner;

public class UserDatabase implements DataBase<User> {
    static private final Logger logger = LogManager.getLogger(UserDatabase.class);
    private final Object syncObject;

    public UserDatabase() {
        syncObject = new Object();
    }

    @Override
    public User Load(int id) throws IOException {
        synchronized (syncObject) {
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class, "UserDirectory") + "\\" + id + ".json");
            FileReader fileReader = new FileReader(file);
            Gson gson = new Gson();
            User user = gson.fromJson(fileReader, User.class);
            fileReader.close();
            logger.info("the user with id : " + id + " was loaded successfully");
            return user;
        }
    }

    @Override
    public void update(User user) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "UserDirectory") + "\\" + user.getId() + ".json" );
            while (true) {
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    FileWriter fileWriter = new FileWriter(file , false);
                    gson.toJson(user, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                    break;
                } catch (Exception e) {
                    file.createNewFile();
                }
            }
            logger.info("the user with id : " + user.getId() + " was updated");
        }
    }

    @Override
    public void delete(int id) {
        synchronized (syncObject){
            try {
                Config config = Config.getConfig("dataBaseAddress");
                File file = new File(config.getProperty(String.class , "UserDirectory") + "\\" + id + ".json");
                file.delete();
            }catch (Throwable throwable){
            }
        }
    }

    @Override
    public int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeUser"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastUserId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastUserId2 = LastUserId + 1;
        printStream.println("NumberOfUsers: " + LastUserId2);
        printStream.flush();
        printStream.close();
        return LastUserId;
    }
}
