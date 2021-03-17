package machine;

import java.util.LinkedHashMap;

public class MemoryBlock {
    LinkedHashMap<Integer, Integer> words;
    int size;

    public MemoryBlock(int size) {
        words = new LinkedHashMap<>(size);
        this.size = size;
    }

    public void addWords(int address, int value) {
        words.put(address % size, value);
    }

    public int findWord(int address) {
        return words.get(address % size);
    }

    public boolean addressExists(int address){
        return words.get(address) != null;
    }
}