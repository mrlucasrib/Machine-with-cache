package machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MMU {
    LinkedList<Memory> memList;

    public MMU(Memory... mem) {
        memList = new LinkedList<>();
        memList.addAll(Arrays.asList(mem));
    }

    private void swapMemory(MemoryBlock block, int i) {

        Memory m = memList.get(i);
        if(m.getBlock(block.getBigAddress()) == null){
            MemoryBlock blk = m.addBlock(block);
            if(blk != null)
                swapMemory(block,++i);
        }

    }

    public Integer findInMemory(int address, int addrWord) {
        for (Memory m : memList) {
            Integer value = m.getValue(address, addrWord);
            if (value != null) {
                swapMemory(m.getBlock(address), 0);
                return value;
            }
        }
        // Sempre será encontrado na RAM, por isso nunca retornará null
        return null;
    }


    public void setValue(int address, int addrWord, int value) {
        for (Memory m : memList) {
            if (m.getBlock(address) != null) {
                m.getBlock(address).addWords(addrWord, value);
            }
        }

    }
}
