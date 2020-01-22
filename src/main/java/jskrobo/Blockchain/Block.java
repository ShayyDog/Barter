package jskrobo.Blockchain;

import java.util.Date;
import java.util.List;

public class Block<T> {

    public String hash;
    public String previousHash;
    private List<T> data;
    private long timestamp;
    private int nonce;

    //Constructor
    public Block(List<T> data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedHash = StringUtil.applySHA256(
                previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data.toString()
        );
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            if (nonce % 1000000 == 0) {
                System.out.print(".");
            }
            hash = calculateHash();
        }

        System.out.println("\nBlock mined!  :  " + hash + "\n");
    }
}
