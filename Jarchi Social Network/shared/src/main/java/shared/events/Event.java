package shared.events;

import shared.responses.Response;

public abstract class Event {
    private int authToken;

    protected Event(int authToken) {
        this.authToken = authToken;
    }

    public abstract Response visit(EventVisitor eventVisitor);

    public int getAuthToken() {
        return authToken;
    }

    public void setAuthToken(int authToken) {
        this.authToken = authToken;
    }
}
