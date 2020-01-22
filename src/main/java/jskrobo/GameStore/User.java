package jskrobo.GameStore;

import java.util.*;

public class User {
    public static long userCount;

    private String username;
    private String userID;
    private List<Game> library;
    private List<Game> wishList;
    private List<User> friends;

    public User(String name, String assignedID) {
        username = name;
        userID = assignedID;

        library = new ArrayList<Game>();
        wishList = new ArrayList<Game>();
        friends = new ArrayList<User>();
        userCount++;
    }

    public void setUserName(String name) {
        username = name;
    }

    public void addGame(Game newGame) {
        //Add check for if game is already in library
        library.add(newGame);
    }

    public void removeGame(Game gameToRemove) {
        //Add check for if game is not in library
        library.remove(gameToRemove);
    }

    public void addToWishList(Game newGame) {
        //Check if already in wishlist
        wishList.add(newGame);
    }

    public void removeFromWishList(Game gameToRemove) {
        wishList.remove(gameToRemove);
    }

    public void addFriend(User newFriend) {
        friends.add(newFriend);
    }

    public void removeFriend(User friendToRemove) {
        friends.remove(friendToRemove);
    }
}
