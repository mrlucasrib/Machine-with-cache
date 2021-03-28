package machine;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    private HashMap<Integer, MemoryBlock> MemoryBlocks;
    private final int size;
    private final int sizeBlock;

    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }

    private int hit;
    private int miss;


    public Memory(int size, int sizeBlock) {
        this.size = size;
        this.sizeBlock = sizeBlock;

    }

    public MemoryBlock setValue(int address, int value) {
        MemoryBlock block = MemoryBlocks.get(address);
        if (block == null) {
            MemoryBlock newBlock = new MemoryBlock(address, sizeBlock);
            newBlock.addWords(address, value);
            if (size <= MemoryBlocks.size()) {
                MemoryBlocks.put(address, newBlock);
                return null;
            } else {
                return discartBlock();
            }
        } else {
            if (!block.addressExists(address))
                block.addWords(address, value);
        }
        return null;
    }

    public MemoryBlock discartBlock() {
        int worst = 0;
        MemoryBlock discarded = null;
        for (Map.Entry<Integer, MemoryBlock> m : MemoryBlocks.entrySet()) {
            int frequency = m.getValue().getFrequency();
            if (frequency < worst) {
                worst = frequency;
                discarded = m.getValue();
            }
        }
        return discarded;
    }

    public Integer getValue(int address) {
        MemoryBlock block = MemoryBlocks.get(address);
        Integer value = block.findWord(address);
        if (value == null)
            this.miss++;
        else
            this.hit++;
        return value;
    }


    public MemoryBlock removeBlock(int address) {
        MemoryBlock m = MemoryBlocks.get(address);
        MemoryBlocks.remove(address);
        return m;
    }

    public MemoryBlock addBlock(int address, MemoryBlock block) {
        if (MemoryBlocks.size() >= size) {
            return discartBlock();
        } else {
            MemoryBlocks.put(address, block);
            return null;
        }

    }
}
