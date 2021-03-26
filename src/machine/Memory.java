package machine;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    HashMap<Integer, MemoryBlock> MemoryBlocks;
    int size, sizeBlock, hit, miss;


    public Memory(int size, int sizeBlock) {
        this.size = size;
        this.sizeBlock = sizeBlock;

    }

    public MemoryBlock addValue(int address, int value) {
        MemoryBlock block = MemoryBlocks.get(address);
        if (block == null) {
            MemoryBlock newBlock = new MemoryBlock(sizeBlock);
            newBlock.addWords(address, value);
            if (size <= MemoryBlocks.size()) {
                MemoryBlocks.put(address, newBlock);
                size++;
            } else {

            }
        } else {
            if (!block.addressExists(address))
                block.addWords(address, value);
        }
    }

    public MemoryBlock discartBlock() {

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


}
