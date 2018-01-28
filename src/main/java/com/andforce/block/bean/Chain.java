package com.andforce.block.bean;

import java.util.ArrayList;

public class Chain {

    private ArrayList<Block> mBlocks = new ArrayList<>();

    private long mLength = 0;

    public ArrayList<Block> getBlocks() {
        return mBlocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        mBlocks = blocks;
        mLength = blocks.size();
    }

    public long getLength() {
        return mLength;
    }

    public void setLength(long length) {
        this.mLength = length;
    }

    public boolean addBlock(Block block){
        boolean add = mBlocks.add(block);
        if (add){
            mLength = mBlocks.size();
        }
        return add;
    }
}
