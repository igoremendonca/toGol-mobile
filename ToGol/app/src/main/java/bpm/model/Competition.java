package bpm.model;

import java.util.List;

/**
 * Created by igor on 31/05/16.
 */
public class Competition {

    private Sport sport;
    private String name;
//    private List<Event> events;

    public Competition() {}

    public Competition(Sport sport, String name) {
        this.sport = sport;
        this.name = name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }
}
