package src.view.refresher;

import src.view.Refreshable;

import java.util.HashMap;

/**
 * Created by root on 21.5.16.
 */
public class Refresher {

    private HashMap<String,Refreshable> refreshables;

    public Refresher(){
        refreshables = new HashMap<>();
    }

    public void addRefreshable(String username, Refreshable refreshable){
        refreshables.put(username,refreshable);
    }

    public void removeRefreshable(String username){
        refreshables.remove(username);
    }

    public void refreshTarget(String username){
        Refreshable refreshable = refreshables.get(username);
        if(refreshable != null) {
            refreshable.refresh();
        }
    }



}
