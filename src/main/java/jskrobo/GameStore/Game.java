package jskrobo.GameStore;

import java.util.*;

public class Game {
    public static long gameCount;

    private String title;
    private String gameID;
    private Developer dev;
    private List<License> activeLicenses;
    private List<License> inactiveLicenses;

    public Game(String t, String assignedID, Developer d) {
        title = t;
        gameID = assignedID;

        dev = d;

        activeLicenses = new ArrayList<License>();
        inactiveLicenses = new ArrayList<License>();
        gameCount++;
    }

    public void generateLicenses(long num) {
        for (long i = 0; i < num; i++) {
            License newLicense = new License(this);
            inactiveLicenses.add(newLicense);
        }
    }

    public void deleteLicenses(long num) {
        long initialSize = inactiveLicenses.size();
        for (long i = initialSize - 1; i > initialSize - num - 1; i--) {
            inactiveLicenses.remove(i);
        }
    }
}
