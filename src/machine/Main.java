package machine;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pathname = scanner.nextLine();
        Queue<Instructions> instructions = readInstructions(pathname);

        Machine machine = new Machine(instructions, Integer.parseInt(scanner.nextLine()),
                Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), 1000);
        machine.run();
    }

    private static <FileNotFoundException> Queue<Instructions> readInstructions(String pathname) {
        Queue<Instructions> qi = new LinkedList<>();
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] str = data.split(" ");
                int[] i = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
                Instructions instructions = new Instructions(i[0], i[1], i[2], i[3], i[4], i[5], i[6]);
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
