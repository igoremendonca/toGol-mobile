package bpm.repository;

import bpm.repository.impl.EventRepositoryImpl;

/**
 * Created by igor on 06/07/16.
 */
public class RepositoryFactory {

    public EventRepository build(){
        return new EventRepositoryImpl();
    }
}
