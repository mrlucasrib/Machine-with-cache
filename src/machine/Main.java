package machine;

import java.util.Queue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Memory cache0, cache1, cache2, RAM;
        cache0 = new Memory(Integer.parseInt(System.console().readLine()),4); //8
        cache1 = new Memory(Integer.parseInt(System.console().readLine()),4); //16
        cache2 = new Memory(Integer.parseInt(System.console().readLine()),4); //32

        RAM = new Memory(1000,4);

        MMU mmu = new MMU(cache0, cache1,cache2, RAM);

        populateRam(RAM);
        Queue<int[]> instructions = readInstructions(cache0, cache1, RAM);
        
        printInfo(cache0, cache1, RAM);

    }






    private static Queue<int[]> readInstructions(Memory cache0, Memory cache1, Memory ram) {
        return null;
    }


}
