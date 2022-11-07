package shared.models;

import java.util.ArrayList;

public class Group {
    private String name;
    private int id;
    private ArrayList<Integer> userIds;
    private ArrayList<Integer> allMessages;

    public Group(String name , int lastId) {
        lastId++;
        this.id = lastId;
        this.name = name;
        this.allMessages = new ArrayList<>();
        this.userIds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<Integer> userIds) {
        this.userIds = userIds;
    }

    public ArrayList<Integer> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(ArrayList<Integer> allMessages) {
        this.allMessages = allMessages;
    }
}
