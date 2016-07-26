package bpm.togol.enuns;

import bpm.togol.R;

/**
 * Created by igor on 01/07/16.
 */
public enum SportIconEnum {

    FOOTBALL(R.drawable.ic_footbal),
    SOCCER(R.drawable.ic_soccer),
    VOLLEYBALL(R.drawable.ic_volei),
    ATHLETICS(R.drawable.athletics),
    BASKETBALL(R.drawable.basketball),
    BOXING(R.drawable.boxing),
    BADMINTON(R.drawable.badminton),
    STREET_RACE(R.drawable.street_race),
    OBSTACLE_RACE(0),
    CROSSFIT(R.drawable.crossfit),
    CANOEING(R.drawable.canoeing),
    CYCLING(R.drawable.cycling),
    FENCING(R.drawable.fencing),
    GOLF(R.drawable.golf),
    ARTISTIC_GYMNASTICS(R.drawable.artistic_gymnastics),
    RHYTHMIC_GYMNASTICS(R.drawable.rhythmic_gymnastics),
    EQUESTRIANISM(0),
    HANDBALL(R.drawable.handbal),
    HOCKEY(R.drawable.hockey),
    JUDO(0),
    WEIGHTLIFTING(R.drawable.crossfit),
    OLYMPIC_FIGHT(R.drawable.olympic_fight),
    MMA(R.drawable.mma),
    MOTORCYCLING(R.drawable.motocycling),
    MARATHON_SWIMMING(0),
    SWIMMING(0),
    SYNCHRONIZED_SWIMMING(0),
    WATER_POLO(0),
    RODEOS(0),
    ROWING(0),//mesmo de canoagem
    RUGBY(0),
    SURFING(0),
    SHOWBOL(0),
    STOCKCAR(0),
    SKATEBOARD(0),
    CLIFFJUMP(0),
    DIVING(0),
    SHOOTING_SPORTS(0),
    TRIATHLON(0),
    SNEAKERS(0),
    TABLE_TENNIS(R.drawable.tenis_table),
    TENIS(R.drawable.tenis),
    TAEKWONDO(0),
    ARCHERY(0),
    TRAMPOLINING(0),
    BEACH_VOLLEYBALL(0),
    SNOOKER(0);


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
