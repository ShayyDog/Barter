package jskrobo.Blockchain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import jskrobo.GameStore.*;

public class Controller<T> {

    public static List<Block<License>> blockchain = new ArrayList<Block<License>>();
    public static List<License> pending;
    public static int difficulty = 2;

    public static void main(String[] args) {

        pending = new ArrayList<License>();

        //Example users
        User user1 = new User("ShayyDog", 20);
        User user2 = new User("dsboyd4", 21);
        User user3 = new User("jcerota", 21);

        //Example dev
        Developer dev1 = new Developer("Fake Studios");

        //Example games
        Game game1 = new Game("Rust", dev1);
        dev1.publishGame(game1);
        Game game2 = new Game("Tarkov", dev1);
        dev1.publishGame(game2);

        //Example transactions
        user1.purchaseNewLicense(game1);
        user2.purchaseNewLicense(game1);
        user3.purchaseNewLicense(game1);
        user1.purchaseNewLicense(game2);

        pending.toString();


        boolean mining = true;
        blockchain.add(new Block(pending, "0"));
        System.out.println("Mining block 1");
        blockchain.get(0).mineBlock(difficulty);

        pending.clear();

        //More transactions
        user1.exchangeLicense(user1.findLicense(game2), user2);
        user3.purchaseNewLicense(game2);

        pending.toString();

        blockchain.add(new Block(pending, blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2");
        blockchain.get(1).mineBlock(difficulty);

        pending.clear();

        blockchain.add(new Block(pending, blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 3");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid: " + isChainValid());

        String blockchainJSON = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJSON);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes do not match");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hash does not match");
                return false;
            }
        }

        return true;
    }
}
