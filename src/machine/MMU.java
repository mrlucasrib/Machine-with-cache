package machine;

import java.util.ArrayList;
import java.util.Arrays;

public class MMU {
    ArrayList<Memory> memList;

    public MMU(Memory... mem) {
        memList = new ArrayList<>();
        memList.addAll(Arrays.asList(mem));
    }

    public Integer findInMemory(int address, int addrWord) {
        Integer value = null;
        for (int i = 0; i < memList.size(); i++) {
            value = memList.get(i).getValue(address);
            if (value != null) {
                if (i > 0) {
                    swapMemory(address, i);
                }
            }
        }
        // Sempre será encontrado na RAM, por isso nunca retornará null
        return value;

    }

    private void swapMemory(int address, int index) {
        if (index > 0) {
            Memory mem = memList.get(index);
            Integer value = mem.getValue(address);
            // Como a memoria RAM sempre tera espaço, ele não terá que fazer swap
            Memory mem2 = memList.get(index + 1);
            MemoryBlock block = mem2.setValue(address, value);
            if (block != null)
                swapMemory(address, index + 1);
        }
    }

    private void swapToFirst(int address, int index) {
        Memory mem = memList.get(index);
        MemoryBlock block = mem.removeBlock(address);
        MemoryBlock discarded = mem.addBlock(address, block);

    }

    public void setValue(int address, int n) {

    }
}
