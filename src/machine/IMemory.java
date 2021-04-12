package machine;

public interface IMemory {
    int getHit();

    int getMiss();

    Integer getValue(int address, int addrWord);

    MemoryBlock addBlock(MemoryBlock block);

    MemoryBlock getBlock(int address);
}
