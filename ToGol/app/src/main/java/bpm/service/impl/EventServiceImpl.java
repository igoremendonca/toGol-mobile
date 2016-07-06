package bpm.service.impl;

import java.util.List;

import bpm.model.Event;
import bpm.repository.EventRepository;
import bpm.repository.RepositoryFactory;
import bpm.service.EventService;

/**
 * Created by igor on 01/07/16.
 */
public class EventServiceImpl implements EventService {

    private RepositoryFactory repositoryFactory = new RepositoryFactory();
    private EventRepository eventRepository;

    public EventServiceImpl() {
        eventRepository = repositoryFactory.build();
    }

    @Override
    public List<Event> findEventsByLocation(double latitude, double longitude) {
        return eventRepository.findEventsByLocation(latitude, longitude);
    }
}
