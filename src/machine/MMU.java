package machine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MMU {
    LinkedList<IMemory> memList;

    public MMU(List<IMemory> mem) {
        memList = new LinkedList<>();
        memList.addAll(mem);
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
        // Sempre será encontrado na ultima memoria, por isso nunca retornará null
        return null;
    }


    public void setValue(int address, int addrWord, int value) {
        for (IMemory m : memList) {
            MemoryBlock mb = m.getBlock(address);
            if (mb != null) {
                m.getBlock(address).addWords(addrWord, value);
            }
        }

    }
}
