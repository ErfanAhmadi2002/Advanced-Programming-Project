package managers;

import config.Config;
import database.Context;
import shared.models.Group;
import shared.models.Message;
import shared.models.User;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BotManager {
    private final Context context;
    private final HashMap<Integer, File> availableBots;
    private final HashMap<Integer, User> onlineUsers;
    private final SecureRandom secureRandom;

    public BotManager(Context context, HashMap<Integer, User> onlineUsers, SecureRandom secureRandom) {
        this.context = context;
        this.availableBots = new HashMap<>();
        this.onlineUsers = onlineUsers;
        this.secureRandom = secureRandom;
    }

    public HashMap<Integer, File> getAvailableBots() {
        return availableBots;
    }


    public String handleServerCommand(String command) {
        if (command.startsWith("add bot ")) {
            try {
                String address = command.substring(8);
                Config config = Config.getConfig("botPaths");
                File file = new File(config.getProperty(String.class , address));
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                Class<?> botClass = loadClassByFile(file);
                Object object = botClass.getConstructor().newInstance();
                int lastId = context.userDataBase.getLastId();
                User bot = new User(className,
                        className,
                        className,
                        "1",
                        new Date(),
                        "nothing",
                        "nothing",
                        lastId);
                context.userDataBase.update(bot);
                context.previousData.UpdateUserNames(bot);
                context.botDataBase.update(object, bot.getId());
                availableBots.put(bot.getId(), file);
                int authToken = secureRandom.nextInt(Integer.MAX_VALUE);
                onlineUsers.put(authToken, bot);
                return ("bot successfully added");
            } catch (Exception exception) {
                return ("invalid command");
            }
        } else {
            return ("invalid command");
        }
    }

    public synchronized void handleRequest(String command, int botId, int senderId , int groupId) {
        try {
            File file = availableBots.get(botId);
            Class<?> botClass = loadClassByFile(file);
            Object bot = context.botDataBase.load(botId, botClass);
            Method method = botClass.getMethod("handleRequest", String.class, int.class , int.class);
            String[] answer = (String[]) method.invoke(bot, command, senderId , groupId);
            context.botDataBase.update(bot , botId);
            int answerType = Integer.parseInt(answer[0]);
            switch (answerType){
                case 1:
                    sendMessageToPV(answer , botId);
                    break;
                case 2:
                    sendMessageToGroup(answer , botId);
                    break;
                case 3:
                    sendTweet(answer , botId);
                    break;
                case 4:
                    sendComment(answer , botId);
                    break;
            }
        } catch (Exception ignored) {
        }
    }

    private Class<?> loadClassByFile(File file) throws Exception {
        URL url = file.getParentFile().toURI().toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        String className = file.getName().substring(0, file.getName().lastIndexOf("."));
        return urlClassLoader.loadClass(className);
    }

    private void sendMessageToPV(String[] answer, int botId) {
        String receivers = answer[1];
        ArrayList<Integer> receiversId = new ArrayList<>();
        while (receivers.length() > 0){
            int index = receivers.indexOf(" ");
            if (index == -1){
                receiversId.add(Integer.parseInt(receivers));
                break;
            }
            String id = receivers.substring(0 , index);
            receivers = receivers.substring(index+1);
            receiversId.add(Integer.parseInt(id));
        }
        try {
            int lastId = context.messageDataBase.getLastId();
            Message message = new Message(botId, answer[2], false , lastId);
            context.messageDataBase.update(message);
            for (Integer userId : receiversId) {
                try {
                    User user = context.userDataBase.Load(userId);
                    if (!user.getAllMessages().containsKey(botId)) {
                        user.getAllMessages().put(botId, new ArrayList<>());
                    }
                    user.getAllMessages().get(botId).add(message.getId());
                    context.userDataBase.update(user);
                }catch (Throwable ignored){}
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void sendMessageToGroup(String[] answer, int botId) {
        int groupId = Integer.parseInt(answer[1]);
        try {
            int lastId = context.messageDataBase.getLastId();
            Message message = new Message(botId, answer[2], false , lastId);
            context.messageDataBase.update(message);
            Group group = context.groupDataBase.Load(groupId);
            group.getAllMessages().add(message.getId());
            context.groupDataBase.update(group);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private void sendTweet(String[] answer, int botId) {
    }

    private void sendComment(String[] answer, int botId) {
    }

}
