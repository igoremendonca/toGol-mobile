package bpm.togol.enuns;

import bpm.togol.R;

/**
 * Created by igor on 01/07/16.
 */
public enum SportIconEnum {

    FOOTBALL(R.drawable.ic_footbal),
    SOCCER(R.drawable.ic_soccer),
    VOLLEYBALL(R.drawable.ic_volei);

    private int resource;

    SportIconEnum(int resource) {
        this.resource = resource;
    }

    public static SportIconEnum getValueOf(String name){
        try {
            return SportIconEnum.valueOf(name);
        } catch (Exception e){
            return null;
        }

    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
