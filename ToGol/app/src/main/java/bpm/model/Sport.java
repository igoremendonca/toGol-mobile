package bpm.model;

/**
 * Created by igor on 31/05/16.
 */
public class Sport {

    private String name;
    private int icon;

    public Sport() {}

    public Sport(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
