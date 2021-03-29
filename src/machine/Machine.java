package machine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Machine {
    Queue<Instructions> instructions;
    LinkedList<Memory> mList;
    MMU mmu;

    public Machine(Queue<Instructions> instructions, int... n) {
        mList = new LinkedList<>();
        for (int num : n) {
            Memory temp = new Memory(num, 4);
            mList.add(temp);
        }
        mmu = new MMU(mList.toArray(new Memory[mList.size()]));
        this.instructions = instructions;
        populateRam(mList.getLast());
    }

    public void run() {
        for (Instructions i : instructions) {
            interpret(i);
        }
    }

    public void interpret(Instructions i) {
        int value1, value2;
        value1 = mmu.findInMemory(i.getAddress1(), i.getWord1());
        value2 = mmu.findInMemory(i.getAddress2(), i.getWord2());
        switch (i.getOpcode()) {
            case 0 -> mmu.setValue(i.getAddress3(), i.getWord3(), value1 + value2);
            case 1 -> mmu.setValue(i.getAddress3(), i.getWord3(), value1 - value2);
            case -1 -> printInfo();
        }
    }

    private void printInfo() {
        for (Memory mem : mList) {
            float total = mem.getHit() + mem.getMiss();
            System.out.printf("%.2f;",(mem.getHit()/total)*100);
        }
    }

    // Na pratica, este metodo não deveria existir, e sim as instruçoes entregues a maquinas que deveriam ser responsa-
    // veis pela alocação de valores na memoria.
    private void populateRam(Memory memory) {
        Random r = new Random();
        for (int i = 0; i < memory.getSize(); i++) {
            for (int j = 0; j < memory.getSizeBlock(); j++) {
                memory.setValue(i, j, r.nextInt(10000));
            }
        }
    }

}
