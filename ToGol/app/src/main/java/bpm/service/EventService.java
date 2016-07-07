package bpm.service;

import java.util.List;

import bpm.model.Event;

/**
 * Created by Igor on 28/04/2016.
 */
public interface EventService {

    List<Event> findEventsByLocation(double latitude , double longitude);
}
