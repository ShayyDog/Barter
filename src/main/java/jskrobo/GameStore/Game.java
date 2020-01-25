package jskrobo.GameStore;

import java.util.*;
import jskrobo.Blockchain.StringUtil;

public class Game {
    public static long gameCount;

    private String title;
    private String gameID;
    private Developer dev;
    private List<License> activeLicenses;
    /* Removing inactive licenses to explore the idea of only generating licenses when purchased from developer,
       therefore making all created licenses active.
     */
    //private List<License> inactiveLicenses;

    public Game(String t, Developer d) {
        gameCount++;
        title = t;
        dev = d;
        gameID = StringUtil.applySHA256(title + Long.toString(gameCount) + dev.getID());

        activeLicenses = new ArrayList<License>();
        //inactiveLicenses = new ArrayList<License>();
    }

    public void addLicense(License license) {
        activeLicenses.add(license);
    }

    public void deactivateLicense(License license) {
        activeLicenses.remove(license);
        //inactiveLicenses.add(license);
    }

    public String getID() {
        return gameID;
    }

    public boolean equals(Game game) {
        if (gameID.equals(game.gameID)) {
            return true;
        }
        else {
            return false;
        }
    }
}
