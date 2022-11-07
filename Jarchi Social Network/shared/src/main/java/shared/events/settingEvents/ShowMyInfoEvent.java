package shared.events.settingEvents;

import shared.events.Event;
import shared.events.EventVisitor;
import shared.responses.Response;

public class ShowMyInfoEvent extends Event {

    public ShowMyInfoEvent(int authToken) {
        super(authToken);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.showMyInfo(this);
    }

}
