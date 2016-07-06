package bpm.model;

import java.util.Date;
import java.util.List;

/**
 * Created by igor on 31/05/16.
 */
public class Event {

    private Competition competition;
    private Date date;
    private EventLocation eventLocation;
    private List<Game> games;

    public Event() {}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventLocation getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocation eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
