package machine;

import java.util.Arrays;
import java.util.LinkedList;

public class MMU {
    LinkedList<IMemory> memList;

    public MMU(Memory... mem) {
        memList = new LinkedList<>();
        memList.addAll(Arrays.asList(mem));
    }

    private void swapMemory(MemoryBlock block, int i) {

        IMemory m = memList.get(i);
        if(m.getBlock(block.getBigAddress()) == null){
            MemoryBlock blk = m.addBlock(block);
            if(blk != null)
                swapMemory(block,++i);
        }

    }

    public Integer findInMemory(int address, int addrWord) {
        for (IMemory m : memList) {
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
        for (IMemory m : memList) {
            if (m.getBlock(address) != null) {
                m.getBlock(address).addWords(addrWord, value);
            }
        }

    }
}
