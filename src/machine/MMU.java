package machine;

import java.util.ArrayList;

public class MMU {
    ArrayList<Memory> memList;

    public MMU(Memory RAM, Memory cache0, Memory cache1) {
        memList = new ArrayList<>();
        memList.add(RAM);
        memList.add(cache0);
        memList.add(cache1);
    }

    public Integer findInMemory(int address) {
        for (Memory mem : memList) {
            Integer value = mem.getValue(address);
            if(value==null)
                continue;
            return value;

        }

    }
}
