package bpm.repository.impl;

import java.util.Arrays;
import java.util.List;

import bpm.model.Competition;
import bpm.model.Event;
import bpm.model.EventLocation;
import bpm.model.Game;
import bpm.model.Sport;
import bpm.model.Team;
import bpm.repository.EventRepository;
import bpm.util.DateUtil;

import static bpm.model.enuns.SportIconEnum.FOOTBALL;
import static bpm.model.enuns.SportIconEnum.SOCCER;
import static bpm.model.enuns.SportIconEnum.VOLLEYBALL;

/**
 * Created by igor on 01/07/16.
 */
public class EventRepositoryImpl implements EventRepository {
    @Override
    public List<Event> findEventsByLocation(double latitude, double longitude) {
//        return null;

        Sport basebol = new Sport("Football", FOOTBALL);
        Competition competition = new Competition(basebol, "Amistoso Internacional");
        Team t1 = new Team("Buffalo Bills", basebol);
        Team t2 = new Team("New England", basebol);

        EventLocation eventLocation = new EventLocation(-18.914221, -48.296323);
        eventLocation.setPlace("Estádio Glória");
        Game game = new Game();
        game.setTeams(Arrays.asList(t1, t2));
        game.setHour("22:00");

        Event event = new Event();
        event.setCompetition(competition);
        event.setEventLocation(eventLocation);
        event.setDate(DateUtil.createDate(2016,5,2,"22:00"));
        event.setGames(Arrays.asList(game));

//##################################################################################################

        Sport futebol = new Sport("Futebol", SOCCER);
        Competition competition1 = new Competition(futebol, "Copa Futel de Futsal");
        Team t3 = new Team("JuntoMisturados", futebol);
        Team t4 = new Team("Roosevelt", futebol);

        EventLocation eventLocation1 = new EventLocation(-18.917991, -48.259115);
        eventLocation1.setPlace("UFU");
        Game game1 = new Game();
        game1.setTeams(Arrays.asList(t3,t4));
        game1.setHour("19:00");

        Event event1 = new Event();
        event1.setCompetition(competition1);
        event1.setEventLocation(eventLocation1);
        event1.setDate(DateUtil.createDate(2016,5,2,"19:00"));
        event1.setGames(Arrays.asList(game1));

//##################################################################################################

        Competition competition2 = new Competition(futebol, "Campeonato Mineiro de Futebol");
        Team t5 = new Team("Boa Esporte", futebol);
        Team t6 = new Team("Uberlandia", futebol);

        EventLocation eventLocation2 = new EventLocation(-18.933047, -48.271303);
        eventLocation2.setPlace("Parque Sabiá");
        Game game2 = new Game();
        game2.setTeams(Arrays.asList(t5, t6));
        game2.setHour("20:00");

        Event event2 = new Event();
        event2.setCompetition(competition2);
        event2.setEventLocation(eventLocation2);
        event2.setDate(DateUtil.createDate(2016,5,2,"20:00"));
        event2.setGames(Arrays.asList(game2, game1));

//##################################################################################################
        Sport volei = new Sport("Volei", VOLLEYBALL);
        Competition competition3 = new Competition(volei, "Copa Uberlandense de Volei");
        Team t7 = new Team("Poderosas", volei);
        Team t8 = new Team("MiniSaia", volei);

        EventLocation eventLocation3 = new EventLocation(-18.895410, -48.284324);
        eventLocation3.setPlace("Poliesportivo Q");
        Game game3 = new Game();
        game3.setTeams(Arrays.asList(t7, t8));
        game3.setHour("18:00");

        Event event3 = new Event();
        event3.setCompetition(competition3);
        event3.setEventLocation(eventLocation3);
        event3.setDate(DateUtil.createDate(2016,5,2,"18:00"));
        event3.setGames(Arrays.asList(game3));

        return Arrays.asList(event, event1, event2, event3);
    }
}
