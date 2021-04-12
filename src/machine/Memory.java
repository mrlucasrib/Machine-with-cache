package machine;

import java.util.HashMap;
import java.util.Map;

public class Memory implements IMemory {
    private HashMap<Integer, MemoryBlock> MemoryBlocks;

    private int getSize() {
        return size;
    }

    private int getSizeBlock() {
        return sizeBlock;
    }

    private final int size;
    private final int sizeBlock;

    @Override
    public int getHit() {
        return hit;
    }

    @Override
    public int getMiss() {
        return miss;
    }

    private int hit;
    private int miss;


    public Memory(int size, int sizeBlock) {
        this.size = size;
        this.sizeBlock = sizeBlock;
        MemoryBlocks = new HashMap<>();

    }

    //Necessario fazer verificaçoes se o bloco existe antes de chamar
    protected void setValue(int address, int addrWord, int value) {
        MemoryBlock block = MemoryBlocks.get(address);
        if (block == null) {
            block = new MemoryBlock(sizeBlock, address);
            MemoryBlocks.put(address, block);
        }
        block.addWords(addrWord, value);
    }

    private MemoryBlock discartBlock() {
        int worst = 0;
        MemoryBlock discarded = null;
        for (Map.Entry<Integer, MemoryBlock> m : MemoryBlocks.entrySet()) {
            int frequency = m.getValue().getFrequency();
            if (frequency < worst) {
                worst = frequency;
                discarded = m.getValue();
            }
        }
        if (discarded != null)
            MemoryBlocks.remove(discarded.getBigAddress());
        else {
            discarded = MemoryBlocks.get(MemoryBlocks.keySet().toArray()[this.size-1]);
            MemoryBlocks.remove(discarded.getBigAddress());
        }
        return discarded;
    }

    @Override
    public Integer getValue(int address, int addrWord) {
        MemoryBlock block = MemoryBlocks.get(address);
        if (block == null) {
            this.miss++;
            return null;
        } else {
            this.hit++;
            return block.findWord(addrWord);
        }
    }

    @Override
    public MemoryBlock addBlock(MemoryBlock block) {
        if (MemoryBlocks.size() >= size) {
            return discartBlock();
        } else {
            MemoryBlocks.put(block.getBigAddress(), block);
            return null;
        }

    }

    @Override
    public MemoryBlock getBlock(int address) {
        return MemoryBlocks.get(address);
    }
}
