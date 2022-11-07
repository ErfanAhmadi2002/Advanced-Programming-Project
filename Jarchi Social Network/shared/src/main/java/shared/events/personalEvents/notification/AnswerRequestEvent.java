package shared.events.personalEvents.notification;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.models.UserCopy;
import shared.responses.Response;

public class AnswerRequestEvent extends Event {
    private final int answerType;
    private final UserCopy requested;

    public AnswerRequestEvent(int authToken, int answerType, UserCopy requested) {
        super(authToken);
        this.answerType = answerType;
        this.requested = requested;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.answerRequest(this);
    }

    public int getAnswerType() {
        return answerType;
    }

    public UserCopy getRequested() {
        return requested;
    }
}
