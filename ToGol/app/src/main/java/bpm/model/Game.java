package bpm.model;

import java.util.List;

/**
 * Created by igor on 01/07/16.
 */
public class Game {
    private List<Team> teams;
    private String hour;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
