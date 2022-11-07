package shared.models;

public class UserCopy {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final int id;
    private final int imageId;

    public UserCopy(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.id = user.getId();
        this.imageId = user.getImageId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }
}
