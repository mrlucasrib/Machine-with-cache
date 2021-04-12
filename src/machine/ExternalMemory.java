package machine;

import java.util.Random;

public class ExternalMemory extends Memory implements IMemory {


    public ExternalMemory(int size, int sizeBlock) {
        super(size, sizeBlock);
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                super.setValue(i, j, r.nextInt(10000));
            }
        }
    }


}
