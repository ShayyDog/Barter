package jskrobo.GameStore;

import java.util.*;
import jskrobo.Blockchain.StringUtil;

public class User {
    public static long userCount;

    private String username;
    private String userID;
    private int age;
    private List<License> library;
    private List<Game> wishList;
    private List<User> friends;

    public User(String name, int howOld) {
        userCount++;
        username = name;
        age = howOld;

        userID = StringUtil.applySHA256(Long.toString(userCount) + username + Integer.toString(age));

        library = new ArrayList<License>();
        wishList = new ArrayList<Game>();
        friends = new ArrayList<User>();

    }

    public String toString() {
        return "Username: " + username + "\nID: " + userID + "\nAge: " + age + "\n";
    }

    public void setUserName(String name) {
        username = name;
    }

    /**
     * @pre newLicense is not already in the user's library
     * @param newLicense
     * @post newLicense is in user's library
     */
    public void addLicense(License newLicense) {
        library.add(newLicense);
    }

    public void purchaseNewLicense(Game gameBuying) {
        License newLicense = new License(gameBuying, this);
        addLicense(newLicense);
    }

    public void exchangeLicense(License LicenseToRemove, User newUser) {
        library.remove(LicenseToRemove);
        LicenseToRemove.transferOwnership(newUser);
        newUser.addLicense(LicenseToRemove);
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

    public List<License> getLibrary() {
        return library;
    }

    public License findLicense(Game game) {
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getGame().equals(game)) {
                return library.get(i);
            }
        }

        return new License();
    }

    public String getID() {
        return userID;
    }
}
