package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.MyImage;

import java.io.*;
import java.util.Scanner;

public class ImageDataBase implements DataBase<MyImage>{
    static private final Logger logger = LogManager.getLogger(ImageDataBase.class);
    private final Object syncObject;

    public ImageDataBase() {
        syncObject = new Object();
    }

    @Override
    public MyImage Load(int id) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "ImageDirectory") + "\\" + id + ".json");
            FileReader fileReader = new FileReader(file);
            Gson gson = new Gson();
            MyImage myImage = gson.fromJson(fileReader , MyImage.class);
            fileReader.close();
            logger.info("the image with id : " + id + " was loaded successfully");
            return myImage;
        }
    }

    @Override
    public void update(MyImage myImage) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "ImageDirectory") + "\\" + myImage.getId() + ".json" );
            while (true) {
                try {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    FileWriter fileWriter = new FileWriter(file , false);
                    gson.toJson(myImage, fileWriter);
                    fileWriter.flush();
                    fileWriter.close();
                    break;
                } catch (Exception e) {
                    file.createNewFile();
                }
            }
            logger.info("the image with id : " + myImage.getId() + " was updated");
        }
    }

    @Override
    public void delete(int id) {
        synchronized (syncObject){
            try {
                Config config = Config.getConfig("dataBaseAddress");
                File file = new File(config.getProperty(String.class , "ImageDirectory") + "\\" + id + ".json");
                file.delete();
            }catch (Throwable ignored){
            }
        }
    }

    @Override
    public int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeImage"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastImageId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastImageId2 = LastImageId + 1;
        printStream.println("NumberOfImages: " + LastImageId2);
        printStream.flush();
        printStream.close();
        return LastImageId;
    }
}
