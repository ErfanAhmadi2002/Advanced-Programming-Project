package shared.formEvents;


public class NewCommentFormEvent extends Form {
    private String commentText;

    public NewCommentFormEvent(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
