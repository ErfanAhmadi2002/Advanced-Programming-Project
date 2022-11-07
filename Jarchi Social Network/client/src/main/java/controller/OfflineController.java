package controller;

import network.SocketEventSender;
import offlineDateBase.EventDataBase;
import shared.events.Event;

import java.io.IOException;
import java.util.ArrayList;

public class OfflineController {
    private final EventDataBase eventDataBase;

    public OfflineController() {
        eventDataBase = new EventDataBase();
    }

    public EventDataBase getEventDataBase() {
        return eventDataBase;
    }

    public void sendSavedEvents (SocketEventSender socketEventSender , int authToken){
        try {
            ArrayList<Event> allEvents = eventDataBase.loadAllEvents(authToken);
            for (Event event:allEvents) {
                socketEventSender.request(event);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void saveEvent (Event event , int type){
        try {
            eventDataBase.update(event , type);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
