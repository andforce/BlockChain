package com.andforce.block;

import com.andforce.block.bean.Block;
import com.andforce.block.bean.Chain;
import com.andforce.block.bean.Transactions;
import com.andforce.block.utils.HashUtils;
import com.andforce.block.utils.JsonUtils;
import com.andforce.block.utils.TimeUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BlockChainHelper {

    private static BlockChainHelper mInstance = new BlockChainHelper();

    private Chain mChain = new Chain();

    private ArrayList<Transactions> mTransactions = new ArrayList<>();


    public static BlockChainHelper getInstance() {
        return mInstance;
    }

    private BlockChainHelper(){
        super();
    }


    public Chain getChain() {
        return mChain;
    }

    public long newTransaction(String sender, String recipient, double amount) {
        Transactions transactions = new Transactions();
        transactions.setSender(sender);
        transactions.setRecipient(recipient);
        transactions.setAmount(amount);

        return lastBlock().getIndex() + 1;
    }

    public Block newBlock(long proof, String previousHash) {
        Block block = new Block();
        block.setIndex(mChain.getLength() + 1);
        block.setTimestamp(TimeUtils.getTimeLong());
        block.setTransactions(new ArrayList<>(mTransactions));
        block.setProof(proof);
        block.setPreviousHash(previousHash);

        mTransactions.clear();

        mChain.addBlock(block);
        return block;
    }

    public Block lastBlock() {
        ArrayList<Block> blocks = mChain.getBlocks();
        int size = blocks.size();
        return size == 0 ? null : blocks.get(size - 1);
    }

    public String hash(Block block) {
        String json = JsonUtils.toStringSortByKey(block);
        return HashUtils.getSHA256StrJava(json);
    }

    public double proofOfWork(Block lastBlock) {

        long proof = 0;

        long lastProof = lastBlock.getProof();
        String lastHash = hash(lastBlock);
        while (validProof(lastProof, proof, lastHash)) {
            proof++;
        }
        return proof;
    }

    private boolean validProof(long lastProof, long proof, String lastHash) {
        String guess = lastProof + "" + proof + "" + lastHash;
        String guessHash = HashUtils.getSHA256StrJava(guess);
        return guessHash.startsWith("0000");
    }

    public boolean validChain(List<Block> chain){

        Block lastBlock = chain.get(0);
        int currentIndex = 1;

        while (currentIndex < chain.size()){
            Block block = chain.get(currentIndex);

            if (block.getPreviousHash().equals(hash(lastBlock))){
                return false;
            }

            lastBlock = block;
            currentIndex ++;
        }

        return false;
    }
}
