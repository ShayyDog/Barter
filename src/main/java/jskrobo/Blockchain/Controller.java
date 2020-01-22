package jskrobo.Blockchain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import jskrobo.GameStore.*;

public class Controller<T> {

    public static List<Block<License>> blockchain = new ArrayList<Block<License>>();
    public static int difficulty = 3;

    public static void main(String[] args) {

        List<License> temp = new ArrayList<License>();

        for (int i = 0; i < 2; i++) {
            temp.add(new License());
        }

        boolean mining = true;
        blockchain.add(new Block(temp, "0"));
        System.out.println("Mining block 1");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block(temp, blockchain.get(blockchain.size()-1).hash));
        System.out.println("Mining block 2");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block(temp, blockchain.get(blockchain.size()-1).hash));
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
