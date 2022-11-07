package shared.formEvents;



public class NewPasswordFormEvent extends Form {
    private String password;

    public NewPasswordFormEvent(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
