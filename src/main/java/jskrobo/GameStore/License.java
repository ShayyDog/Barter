package jskrobo.GameStore;

import java.util.*;
import jskrobo.Blockchain.StringUtil;
import jskrobo.Blockchain.Controller;

public class License {
    private String licenseID;
    private Game game;
    private User currentOwner;
    private List<User> prevUsers;
    //private boolean isActive;

    public License(Game currGame, User newUser) {
        game = currGame;
        currentOwner = newUser;

        licenseID = StringUtil.applySHA256(currGame.toString() + newUser.toString());

        prevUsers = new ArrayList<User>();

        currGame.addLicense(this);
        newUser.addLicense(this);

        //Add to pending transactions to await approval
        Controller.pending.add(this);
    }

    public void transferOwnership(User recepient) {
        prevUsers.add(currentOwner);
        currentOwner = recepient;

        //Add to pending transactions to await approval
        Controller.pending.add(this);
    }

    //Temporary constructor
    public License() {
        licenseID = "placeholder";
        game = null;
        currentOwner = null;
        prevUsers = new ArrayList<User>();
    }

    public Game getGame() {
        return game;
    }

    public String toString() {
        return "License ID: " + licenseID + "\nGame: " + game + "\nCurrent Owner: " + currentOwner.getID() + "\n";
    }
}
