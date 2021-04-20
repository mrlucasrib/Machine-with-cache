package machine;

import java.io.*;
import java.util.Random;

public class ExternalMemory implements IMemory {

    String path;
    private final int size;
    private final int sizeBlock;

    public ExternalMemory(int size, int sizeBlock) {
        this.size = size;
        this.sizeBlock = sizeBlock;
        this.path = "/home/lucas/Downloads/MEM/";
        populateExternalMemory();
    }
    private void populateExternalMemory() {
        Random r = new Random();
        FileOutputStream fileSave;
        ObjectOutputStream objSave;
        for (int i = 0; i < this.size; i++) {
            File file = new File(path+i);
            if(file.exists())
                continue;
            MemoryBlock block = new MemoryBlock(sizeBlock, i);
            for (int j = 0; j < 4; j++) {
                block.addWords(j, r.nextInt(10000));
            }
            try {
                fileSave = new FileOutputStream(file, false);
                objSave = new ObjectOutputStream(fileSave);
                objSave.writeObject(block);
                objSave.flush();
                objSave.close();
                fileSave.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getHit() {
        return 0;
    }

    @Override
    public int getMiss() {
        return 0;
    }

    @Override
    public Integer getValue(int address, int addrWord) {
        try {
            ObjectInputStream objstream = new ObjectInputStream(
                    new FileInputStream(path + address));
            MemoryBlock m = (MemoryBlock) objstream.readObject();
            objstream.close();
            return m.findWord(addrWord);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Nunca retornará a null
        return null;
    }

    @Override
    public MemoryBlock addBlock(MemoryBlock block) {
        try {
            FileOutputStream fileSave;
            ObjectOutputStream objSave;
            fileSave = new FileOutputStream(path + block.getBigAddress(), false);
            objSave = new ObjectOutputStream(fileSave);
            objSave.writeObject(block);
            objSave.flush();
            objSave.close();
            fileSave.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Como não tem limite maximo de blocos, não é necessario retornar algum excedente
        return null;
    }

    /// Utilizado apenas para informar que o bloco sempre ira existir na memoria externa
    @Override
    public MemoryBlock getBlock(int address) {
        return new MemoryBlock(4,address);
    }
}
