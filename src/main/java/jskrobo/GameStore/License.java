package jskrobo.GameStore;

import java.util.*;

public class License {
    private String licenseID;
    private Game game;
    private User currentOwner;
    private List<User> prevUsers;
    private boolean isActive;

    public License(Game currGame) {
        //Generate licenseID somehow in here
        licenseID = "placeholder";

        game = currGame;
        isActive = false;

        prevUsers = new ArrayList<User>();
    }

    //Temporary constructor
    public License() {
        licenseID = "placeholder";
        game = null;
        currentOwner = null;
        prevUsers = new ArrayList<User>();
        isActive = true;
    }
}
