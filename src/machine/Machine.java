package machine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.Random;

public class Machine {
    Queue<Instructions> instructionsProgram1, instructionsProgram2;
    LinkedList<IMemory> mList;
    MMU mmu;

    public Machine(Queue<Instructions> instructionsProgram1,Queue<Instructions> instructionsProgram2, int... n) {
        mList = new LinkedList<>();
        for (int num : n) {
            InternalMemory temp = new InternalMemory(num, 4);
            mList.add(temp);
        }
        IMemory ext_mem = new ExternalMemory(1024, 4);
        mList.add(ext_mem);

        mmu = new MMU(mList);
        this.instructionsProgram1 = instructionsProgram1;
        this.instructionsProgram2 = instructionsProgram2;
    }

    public void run() {
        Iterator<Instructions> iProg1 = instructionsProgram1.iterator();
        Iterator<Instructions> iProg2 = instructionsProgram2.iterator();
        while (iProg1.hasNext() || iProg2.hasNext()) {
            Random r = new Random();
            // 25%
            int probability = r.nextInt(4);
            if (probability == 0) {
                if (iProg1.hasNext())
                    interpret(iProg1.next());
            } else {
                if (iProg2.hasNext())
                    interpret(iProg2.next());
            }
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
        for (IMemory mem : mList) {
            float total = mem.getHit() + mem.getMiss();
            System.out.printf("%.2f;", (mem.getHit() / total) * 100);
        }
    }

//    // Na pratica, este metodo não deveria existir, e sim as instruçoes entregues a maquinas que deveriam ser responsa-
//    // veis pela alocação de valores na memoria.
//    private void populateRam(Memory memory) {
//        Random r = new Random();
//        for (int i = 0; i < memory.getSize(); i++) {
//            for (int j = 0; j < memory.getSizeBlock(); j++) {
//                memory.setValue(i, j, r.nextInt(10000));
//            }
//        }
//    }

}
