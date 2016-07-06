package bpm.model;

import bpm.model.enuns.SportIconEnum;

/**
 * Created by igor on 31/05/16.
 */
public class Sport {

    private String name;
    private SportIconEnum icon;

    public Sport() {}

    public Sport(String name, SportIconEnum icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SportIconEnum getIcon() {
        return icon;
    }

    public void setIcon(SportIconEnum icon) {
        this.icon = icon;
    }
}
