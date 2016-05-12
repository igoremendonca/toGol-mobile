package bpm.service;

import java.util.List;

import bpm.model.Event;
import bpm.repository.EventRepository;

/**
 * Created by Igor on 28/04/2016.
 */
public class EventoService {

    private EventRepository eventRepository = new EventRepository();

    public List<Event> findEventsByLocation(double latitude , double longitude){
        return eventRepository.findEventsByLocation(latitude, longitude);
    }
}
