package machine;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Queue<Instructions> instructions = readInstructions();
        Machine machine = new Machine(instructions, 8, 16, 32, 1000);
        machine.run();
    }

    private static <FileNotFoundException> Queue<Instructions> readInstructions() {
        Queue<Instructions> qi = new LinkedList<>();
        try {
            File myObj = new File("/home/lucas/Downloads/instructions.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] str = data.split(" ");
                int[] i = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
                Instructions instructions = new Instructions(i[0], i[1],i[2],i[3], i[4], i[5], i[6]);
                qi.add(instructions);
            }
            myReader.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return qi;
    }


}
