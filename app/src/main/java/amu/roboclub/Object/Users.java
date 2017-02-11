package amu.roboclub.Object;

import java.util.HashMap;

/**
 * Created by divy on 12/02/17.
 */

public class Users {
    private HashMap<String, User> userMap = new HashMap<>();

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }
    public void addEntry(String uid, User user){
        if(!isUser(uid) && user != null)
            userMap.put(uid,user);
    }
    public boolean isUser(String uid){
        return userMap.containsKey(uid);
    }
    public User getUser(String uid){
        if (isUser(uid))
            return userMap.get(uid);
        return (new User());
    }
}
