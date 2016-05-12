package bpm.repository;

import java.util.ArrayList;
import java.util.List;

import bpm.model.Event;

/**
 * Created by Igor on 28/04/2016.
 */
public class EventRepository {

    public List<Event> findEventsByLocation(double latitude, double longitude) {
        return getEvents();
    }

    private List<Event> getEvents() {
        List<Event> eventList = new ArrayList<>();

        return eventList;
    }
}
