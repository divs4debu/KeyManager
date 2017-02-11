package amu.roboclub.Object;

/**
 * Created by divy on 12/02/17.
 */

public class Key {
    private String current;
    private String previous;
    private  int timestamp;

    public Key(){}

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
