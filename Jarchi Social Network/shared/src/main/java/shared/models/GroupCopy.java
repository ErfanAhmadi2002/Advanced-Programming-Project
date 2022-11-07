package shared.models;

public class GroupCopy {
    private final String name;
    private final int id;

    public GroupCopy(Group group) {
        this.name = group.getName();
        this.id = group.getId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
