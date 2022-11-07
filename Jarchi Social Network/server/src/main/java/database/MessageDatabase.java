package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.Message;

import java.io.*;
import java.util.Scanner;

public class MessageDatabase implements DataBase<Message>{
    static private final Logger logger = LogManager.getLogger(MessageDatabase.class);
    private final Object syncObject;

    public MessageDatabase() {
        syncObject = new Object();
    }

    @Override
    public Message Load(int id) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "MessageDirectory") + "\\" + id + ".json");
            FileReader fileReader = new FileReader(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Message message = gson.fromJson(fileReader , Message.class);
            fileReader.close();
            logger.info("the message with id : " + id + " was loaded successfully");
            return message;
        }
    }

    @Override
    public void update(Message message) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "MessageDirectory") + "\\" + message.getId() + ".json" );
            if (!file.exists()){
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            gson.toJson(message, fileWriter);
            logger.info("the message with id : " + message.getId() + " was updated");
            fileWriter.flush();
            fileWriter.close();
        }
    }

    @Override
    public void delete(int id) {
        synchronized (syncObject){
            try {
                Config config = Config.getConfig("dataBaseAddress");
                File file = new File(config.getProperty(String.class , "MessageDirectory") + "\\" + id + ".json");
                file.delete();
            }catch (Throwable ignored){
            }
        }
    }

    @Override
    public int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeMessage"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastMessageId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastMessageId2 = LastMessageId + 1;
        printStream.println("NumberOfMessages: " + LastMessageId2);
        printStream.flush();
        printStream.close();
        return LastMessageId;
    }
}
