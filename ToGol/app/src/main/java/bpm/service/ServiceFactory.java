package bpm.service;

import bpm.service.impl.EventServiceImpl;

/**
 * Created by igor on 06/07/16.
 */
public class ServiceFactory {

    public EventService build(){
        return new EventServiceImpl();
    }
}
