package bpm.model;

import java.util.Date;
import java.util.List;

/**
 * Created by igor on 31/05/16.
 */
public class Event {

    private Competition competition;
    private Date date;
    private List<Team> teams;

    public Event() {}

    public Event(Competition competition) {
        this.competition = competition;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
