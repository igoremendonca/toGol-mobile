package bpm.repository;

import java.util.ArrayList;
import java.util.List;

import bpm.model.EventLocation;

/**
 * Created by Igor on 28/04/2016.
 */
public class EventRepository {

    public List<EventLocation> findEventsByLocation(double latitude, double longitude) {
        return getEvents();
    }

    private List<EventLocation> getEvents() {
        List<EventLocation> eventLocationList = new ArrayList<>();

        return eventLocationList;
    }
}
