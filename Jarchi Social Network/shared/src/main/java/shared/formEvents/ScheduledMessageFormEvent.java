package shared.formEvents;

import java.io.File;

public class ScheduledMessageFormEvent extends Form{
    private final String messageText;
    private final String second;
    private final String minute;
    private final String  hour;
    private final int groupId;
    private final File imageFile;

    public ScheduledMessageFormEvent(String messageText, String second, String minute, String hour, int groupId, File imageFile) {
        this.messageText = messageText;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.groupId = groupId;
        this.imageFile = imageFile;
    }

    public String getSecond() {
        return second;
    }

    public String getMinute() {
        return minute;
    }

    public String getHour() {
        return hour;
    }

    public String getMessageText() {
        return messageText;
    }

    public File getImageFile() {
        return imageFile;
    }

    public int getGroupId() {
        return groupId;
    }
}
