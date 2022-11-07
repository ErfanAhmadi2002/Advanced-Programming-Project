package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;

import java.io.*;

public class BotDataBase {

    public void update(Object object , int id) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "BotDirectory") + "\\" + id + ".json" );
        if (!file.exists()){
            file.createNewFile();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(file, false);
        gson.toJson(object, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public Object load (int id , Class<?> classType) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "BotDirectory") + "\\" + id + ".json");
        FileReader fileReader = new FileReader(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object bot = gson.fromJson(fileReader , classType);
        fileReader.close();
        return bot;
    }
}
