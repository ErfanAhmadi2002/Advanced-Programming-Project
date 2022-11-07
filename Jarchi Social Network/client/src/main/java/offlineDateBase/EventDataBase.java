package offlineDateBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import shared.events.Event;
import shared.events.settingEvents.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class EventDataBase {

    public void update(Event event, int type) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class, "offlineSettingDirectory") + "\\" + type + ".json");
        if (!file.exists()) {
            file.createNewFile();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(file, false);
        gson.toJson(event, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public ArrayList<Event> loadAllEvents(int authToken) throws IOException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class, "offlineSettingDirectory"));
        ArrayList<Event> allEvents = new ArrayList<>();
        for (File file1 : Objects.requireNonNull(file.listFiles())) {
            FileReader fileReader = new FileReader(file1);
            String name = file1.getName();
            int length = name.length();
            name = name.substring(0 , length - 5);
            Event event = loadEventByTypeSetting(Integer.parseInt(name) , fileReader);
            fileReader.close();
            Objects.requireNonNull(event).setAuthToken(authToken);
            allEvents.add(event);
        }
        clearDirectory(file);
        return allEvents;
    }

    public void clearDirectory(File directory) {
        File[] files = directory.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            files[i].delete();
        }
    }

    private Event loadEventByTypeSetting(int type , FileReader fileReader) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        switch (type) {
            case 1:
                return gson.fromJson(fileReader , AccountActivationEvent.class);
            case 2:
                return gson.fromJson(fileReader , ChangePasswordEvent.class);
            case 3:
                return gson.fromJson(fileReader , GeneralPrivacyEvent.class);
            case 4:
                return gson.fromJson(fileReader , ItemPrivacyEvent.class);
            case 5:
                return gson.fromJson(fileReader , LastSeenEvent.class);
            default:
                return null;
        }
    }


}
