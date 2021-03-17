package machine;

import java.util.ArrayList;
import java.util.HashMap;

public class MMU {
    ArrayList<Memory> memList;

    public MMU(Memory RAM, Memory cache0, Memory cache1) {
        memList = new ArrayList<>();
        memList.add(RAM);
        memList.add(cache0);
        memList.add(cache1);
    }

    public void findInMemory(int address) {
        for (Memory mem : memList) {
            mem.getValue(address);
        }
    }
}
