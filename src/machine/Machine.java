package machine;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Machine {
    Queue<Instructions> instructions;
    ArrayList<Memory> mList;
    MMU mmu;

    public Machine(Queue<Instructions> instructions, int... n) {
        for (int num : n) {
            Memory temp = new Memory(num, 4);
        }
        MMU mmu = new MMU(mList.toArray(new Memory[mList.size()]));
        this.instructions = instructions;
    }

    public void translate() {
        for (Instructions i : instructions) {

        }
    }

    public void interpret(int opcode, int address, int address2) {
        int value1, value2;
        value1 = mmu.findInMemory(address);
        value2 = mmu.findInMemory(address2);
        switch (opcode) {
            case 0 -> mmu.setValue(address2, value1 + value2);
            case 1 -> mmu.setValue(address2, value1 - value2);
            case -1 -> printInfo();
        }
    }

    private void printInfo() {
        for (Memory mem : mList) {
            System.out.println(mem.getHit() + ";" + mem.getMiss());
        }
    }

    // Na pratica, este metodo não deveria existir, e sim as instruçoes entregues a maquinas que deveriam ser responsa-
    // veis pela alocação de valores na memoria.
    private void populateRam(Memory ram) {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            ram.setValue(i, r.nextInt(10000));
        }
    }

}
