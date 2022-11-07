package shared.responses.settingResponses;

import shared.responses.Response;

public class ShowMyInfoResponse extends Response {
    private final String userName;
    private final String phoneNumber;
    private final String emailAddress;
    private final String name;

    public ShowMyInfoResponse(String userName, String phoneNumber, String emailAddress, String name) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }
}
