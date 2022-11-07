package shared.formEvents;



public class NewNameFormEvent extends Form {
    private String newName;

    public NewNameFormEvent(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
