package machine;

import java.util.HashMap;

public class Memory {
    HashMap<Integer, MemoryBlock> MemoryBlocks;
    int size;

    public Memory(int size, int sizeBlock) {
        this.size = size;

    }

    public void addValue(int address, int value) {
        MemoryBlock block = MemoryBlocks.get(address);
        if (block == null) {
            MemoryBlock newBlock = new MemoryBlock(size);
            newBlock.addWords(address, value);
            MemoryBlocks.put(address, newBlock);
        } else {
            if (!block.addressExists(address))
                block.addWords(address, value);
        }
    }

    public int getValue(int address) {
        MemoryBlock block = MemoryBlocks.get(address);
        return block.findWord(address);
    }
}
