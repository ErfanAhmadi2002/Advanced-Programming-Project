package database;

import config.Config;
import shared.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PreviousData {
    private HashMap<String , Integer> allUserNames;
    private ArrayList<String> allEmails;
    private ArrayList<String> allPhoneNumbers;

    public PreviousData() throws FileNotFoundException {
        allUserNames = loadAllUserNames();
        allPhoneNumbers = loadAllPhoneNumbers();
        allEmails = loadAllEmailAddresses();
    }

    public HashMap<String, Integer> getAllUserNames() {
        return allUserNames;
    }
    public void setAllUserNames(HashMap<String, Integer> allUserNames) {
        this.allUserNames = allUserNames;
    }
    public ArrayList<String> getAllEmails() {
        return allEmails;
    }
    public void setAllEmails(ArrayList<String> allEmails) {
        this.allEmails = allEmails;
    }
    public ArrayList<String> getAllPhoneNumbers() {
        return allPhoneNumbers;
    }
    public void setAllPhoneNumbers(ArrayList<String> allPhoneNumbers) {
        this.allPhoneNumbers = allPhoneNumbers;
    }



    public void UpdateUserNames (User user) throws FileNotFoundException {
        allUserNames.put(user.getUserName() , user.getId());
        saveNewAllUserNames();
    }

    public void UpdateEmailAddresses (String NewEmail) throws FileNotFoundException {
        allEmails.add(NewEmail);
        saveNewAllEmailAddresses();
    }

    public void UpdatePhoneNumbers (String NewPhoneNumber) throws FileNotFoundException {
        allPhoneNumbers.add(NewPhoneNumber);
        saveNewAllPhoneNumbers();
    }

    public HashMap<String , Integer> loadAllUserNames () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllUserNames"));
        Scanner scanner = new Scanner(file);
        HashMap<String , Integer> AllUsernames = new HashMap<>();
        while (scanner.hasNext()){
            String username = scanner.next();
            int id = scanner.nextInt();
            AllUsernames.put(username , id);
        }
        scanner.close();
        return AllUsernames;
    }

    public ArrayList<String> loadAllEmailAddresses () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllEmailAddresses"));
        Scanner scanner = new Scanner(file);
        ArrayList<String> AllEmailAddresses = new ArrayList<>();
        while (scanner.hasNext()){
            String Email = scanner.next();
            AllEmailAddresses.add(Email);
        }
        scanner.close();
        return AllEmailAddresses;
    }

    public ArrayList<String> loadAllPhoneNumbers () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllPhoneNumbers"));
        Scanner scanner = new Scanner(file);
        ArrayList<String> AllPhoneNumbers = new ArrayList<>();
        while (scanner.hasNext()){
            String Email = scanner.next();
            AllPhoneNumbers.add(Email);
        }
        scanner.close();
        return AllPhoneNumbers;
    }

    public void saveNewAllUserNames () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllUserNames"));
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (String username:allUserNames.keySet()) {
            printStream.println(username + " " + allUserNames.get(username));
        }
        printStream.flush();
        printStream.close();
    }

    public void saveNewAllPhoneNumbers () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllPhoneNumbers"));
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (String phoneNumber: allPhoneNumbers){
            printStream.println(phoneNumber);
        }
        printStream.flush();
        printStream.close();
    }

    public void saveNewAllEmailAddresses () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "AllEmailAddresses"));
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (String email: allEmails){
            printStream.println(email);
        }
        printStream.flush();
        printStream.close();
    }

    public HashMap<Integer , Integer> getReportList () throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "Reports"));
        Scanner scanner = new Scanner(file);
        HashMap<Integer , Integer> reportList = new HashMap<>();
        while (scanner.hasNext()) {
            int tweetId = scanner.nextInt();
            int reportNumbers = scanner.nextInt();
            reportList.put(tweetId , reportNumbers);
        }
        scanner.close();
        return reportList;
    }

    public void saveReportList (HashMap<Integer , Integer> reportList) throws FileNotFoundException {
        Config config = Config.getConfig("dataBaseAddress");
        File file = new File(config.getProperty(String.class , "Reports"));
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (Integer tweetId:reportList.keySet()) {
            printStream.println(tweetId + " " + reportList.get(tweetId));
        }
        printStream.flush();
        printStream.close();
    }

}
