package bpm.service;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import bpm.model.Competition;
import bpm.model.Event;
import bpm.model.EventLocation;
import bpm.model.Sport;
import bpm.repository.EventRepository;
import bpm.togol.R;

/**
 * Created by Igor on 28/04/2016.
 */
public class EventoService {

    private EventRepository eventRepository = new EventRepository();

    public List<EventLocation> findEventsByLocation(double latitude , double longitude){
//        return eventRepository.findEventsByLocation(latitude, longitude);

        //Codigo Mock -> Deve ser substituido pelo codigo acima
        List<EventLocation> list = new ArrayList<>();

        Sport basebol = new Sport("Base", R.drawable.ic_footbal);
        Competition competition = new Competition(basebol, "Amistoso Internacional");
        Event event = new Event(competition);
        EventLocation eventLocation = new EventLocation(event, -18.914221, -48.296323);
        eventLocation.setNameTest("Buffalo Bills X New England Patriots\nEstádio Glória - 22:00");
        list.add(eventLocation);

        Sport futebol = new Sport("Futebol", R.drawable.ic_soccer);
        Competition competition1 = new Competition(futebol, "Copa Futel de Futsal");
        Event event1 = new Event(competition1);
        EventLocation eventLocation1 = new EventLocation(event1, -18.917991, -48.259115);
        eventLocation1.setNameTest("JuntoMisturados X Roosevelt\nUFU - 19:00");
        list.add(eventLocation1);

        Competition competition2 = new Competition(futebol, "Campeonato Mineiro de Futebol");
        Event event2 = new Event(competition2);
        EventLocation eventLocation2 = new EventLocation(event2, -18.933047, -48.271303);
        eventLocation2.setNameTest("Boa Esporte X Uberlandia\nParque Sabiá - 20:00");
        list.add(eventLocation2);

        Sport volei = new Sport("Volei", R.drawable.ic_volei);
        Competition competition3 = new Competition(volei, "Copa Uberlandense de Volei");
        Event event3 = new Event(competition3);
        EventLocation eventLocation3 = new EventLocation(event3, -18.895410, -48.284324);
        eventLocation3.setNameTest("Poderosas X MiniSaia\nPoliesportivo Q - 18:00");
        list.add(eventLocation3);

        return list;
    }
}
