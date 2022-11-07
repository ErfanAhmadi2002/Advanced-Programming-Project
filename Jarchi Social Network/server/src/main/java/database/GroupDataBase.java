package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.models.Group;

import java.io.*;
import java.util.Scanner;

public class GroupDataBase implements DataBase<Group>{
    static private final Logger logger = LogManager.getLogger(GroupDataBase.class);
    private final Object syncObject;

    public GroupDataBase() {
        syncObject = new Object();
    }

    @Override
    public Group Load(int id) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "GroupDirectory") + "\\" + id + ".json");
            FileReader fileReader = new FileReader(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Group group = gson.fromJson(fileReader , Group.class);
            logger.info("the group with id : " + id + " was loaded successfully");
            fileReader.close();
            return group;
        }
    }

    @Override
    public void update(Group group) throws IOException {
        synchronized (syncObject){
            Config config = Config.getConfig("dataBaseAddress");
            File file = new File(config.getProperty(String.class , "GroupDirectory") + "\\" + group.getId() + ".json" );
            if (!file.exists()){
                file.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(file, false);
            gson.toJson(group, fileWriter);
            logger.info("the group with id : " + group.getId() + " was updated");
            fileWriter.flush();
            fileWriter.close();
        }
    }

    @Override
    public void delete(int id) {
        synchronized (syncObject){
            try {
                Config config = Config.getConfig("dataBaseAddress");
                File file = new File(config.getProperty(String.class , "GroupDirectory") + "\\" + id + ".json");
                file.delete();
            }catch (Throwable ignored){
            }
        }
    }

    @Override
    public int getLastId() throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File initialize = new File(config.getProperty(String.class , "initializeGroup"));
        Scanner scanner = new Scanner(initialize);
        scanner.next();
        int LastGroupId = scanner.nextInt();
        scanner.close();
        PrintStream printStream = new PrintStream(new FileOutputStream(initialize) , false);
        Integer LastGroupId2 = LastGroupId + 1;
        printStream.println("NumberOfGroups: " + LastGroupId2);
        printStream.flush();
        printStream.close();
        return LastGroupId;
    }
}
