package jskrobo.GameStore;

import java.util.*;
import jskrobo.Blockchain.StringUtil;

public class Developer {
    public static long devCount;

    private String devName;
    private String devID;
    private List<Game> publishedGames;

    public Developer(String name) {
        devName = name;
        devID = StringUtil.applySHA256(Long.toString(devCount) + devName);
        publishedGames = new ArrayList<Game>();
    }

    public void publishGame(Game newGame) {
        publishedGames.add(newGame);
    }

    public String getID() {
        return devID;
    }
}
