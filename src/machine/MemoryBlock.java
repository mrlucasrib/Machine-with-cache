package machine;

import java.util.LinkedHashMap;

public class MemoryBlock {
    LinkedHashMap<Integer, Integer> words;
    private final int size;

    public int getBigAddress() {
        return bigAddress;
    }

    private final int bigAddress;
    int frequency;

    public int getFrequency() {
        return frequency;
    }

    public MemoryBlock(int size, int bigAddress) {
        words = new LinkedHashMap<>(size);
        this.size = size;
        this.bigAddress = bigAddress;
    }

    public void addWords(int address, int value) {
        words.put(address % size, value);
    }

    public Integer findWord(int address) {
        frequency++;
        return words.get(address % size);
    }

    public boolean addressExists(int address) {
        return words.get(address) != null;
    }
}
