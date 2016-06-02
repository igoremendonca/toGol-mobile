package bpm.model;

/**
 * Created by Igor on 28/04/2016.
 */
public class EventLocation {

    double latitude;
    double longitude;
    String place;
    Event event;

    //TODO:Deve ser excluida quando forem criadas as equipes
    String nameTest;

    public EventLocation(Event event) {
        this.event = event;
    }

    public EventLocation(Event event, double latitude, double longitude) {
        this.event = event;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
