package bpm.repository;

import java.util.ArrayList;
import java.util.List;

import bpm.model.Event;
import bpm.model.EventLocation;

/**
 * Created by Igor on 28/04/2016.
 */
public interface EventRepository {

    List<Event> findEventsByLocation(double latitude, double longitude);

}
