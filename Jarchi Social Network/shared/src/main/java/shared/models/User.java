package shared.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class User {
    private int lastId;
    private int id;
    private int imageId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date birthDate;
    private String emailAddress;
    private String phoneNumber;
    private boolean isActive;
    private LocalDateTime lastSeen;
    private boolean isOnline;
    private LinkedList<Integer> myTweets;
    private ArrayList<Integer> favoriteTweets;
    private LinkedList<Integer> followers;
    private LinkedList<Integer> followings;
    private LinkedList<Integer> blackList;
    private LinkedList<Integer> mutedUsers;
    private LinkedList<Integer> savedTweets;
    private HashMap<Integer , ArrayList<Integer>> allMessages;
    private HashMap<String , ArrayList<Integer>> allCategories;
    private ArrayList<Integer> allGroups;
    private Notifications notifications;
    private boolean privacyState;
    private boolean[] privacyStateItems;
    private int lastSeenState;

    public User(String firstName, String lastName, String userName, String password, Date birthDate, String emailAddress, String phoneNumber , int LastId) {
        this.lastId = LastId;
        lastId ++;
        this.id = lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.myTweets = new LinkedList<>();
        this.favoriteTweets = new ArrayList<>();
        this.followers = new LinkedList<>();
        this.followings = new LinkedList<>();
        this.blackList = new LinkedList<>();
        this.mutedUsers = new LinkedList<>();
        this.savedTweets = new LinkedList<>();
        this.allMessages = new HashMap<>();
        this.allCategories = new HashMap<>();
        this.allGroups = new ArrayList<>();
        this.notifications = new Notifications();
        this.isActive = true;
        this.lastSeen = LocalDateTime.now();
        this.isOnline = true;
        this.privacyState = true;
        this.privacyStateItems = new boolean[3];
        this.lastSeenState = 2;
    }

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<String, ArrayList<Integer>> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(HashMap<String, ArrayList<Integer>> allCategories) {
        this.allCategories = allCategories;
    }

    public ArrayList<Integer> getAllGroups() {
        return allGroups;
    }

    public void setAllGroups(ArrayList<Integer> allGroups) {
        this.allGroups = allGroups;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public LinkedList<Integer> getMyTweets() {
        return myTweets;
    }

    public void setMyTweets(LinkedList<Integer> myTweets) {
        this.myTweets = myTweets;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public LinkedList<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<Integer> followers) {
        this.followers = followers;
    }

    public LinkedList<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(LinkedList<Integer> followings) {
        this.followings = followings;
    }

    public LinkedList<Integer> getBlackList() {
        return blackList;
    }

    public void setBlackList(LinkedList<Integer> blackList) {
        this.blackList = blackList;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public boolean isPrivacyState() {
        return privacyState;
    }

    public void setPrivacyState(boolean privacyState) {
        this.privacyState = privacyState;
    }

    public int getLastSeenState() {
        return lastSeenState;
    }

    public void setLastSeenState(int lastSeenState) {
        this.lastSeenState = lastSeenState;
    }

    public ArrayList<Integer> getFavoriteTweets() {
        return favoriteTweets;
    }

    public void setFavoriteTweets(ArrayList<Integer> favoriteTweets) {
        this.favoriteTweets = favoriteTweets;
    }

    public LinkedList<Integer> getSavedTweets() {
        return savedTweets;
    }

    public void setSavedTweets(LinkedList<Integer> savedTweets) {
        this.savedTweets = savedTweets;
    }

    public LinkedList<Integer> getMutedUsers() {
        return mutedUsers;
    }

    public void setMutedUsers(LinkedList<Integer> mutedUsers) {
        this.mutedUsers = mutedUsers;
    }

    public HashMap<Integer, ArrayList<Integer>> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(HashMap<Integer, ArrayList<Integer>> allMessages) {
        this.allMessages = allMessages;
    }

    public boolean[] getPrivacyStateItems() {
        return privacyStateItems;
    }

    public void setPrivacyStateItems(boolean[] privacyStateItems) {
        this.privacyStateItems = privacyStateItems;
    }

}
