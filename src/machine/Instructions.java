package machine;

public class Instructions {

    private final int opcode, address1, word1, address2, word2, address3, word3;

    public int getOpcode() {
        return opcode;
    }

    public int getAddress1() {
        return address1;
    }

    public int getWord1() {
        return word1;
    }

    public int getAddress2() {
        return address2;
    }

    public int getWord2() {
        return word2;
    }

    public int getAddress3() {
        return address3;
    }

    public int getWord3() {
        return word3;
    }

    public Instructions(int opcode, int address1, int word1, int address2, int word2, int address3, int word3) {
        this.opcode = opcode;
        this.address1 = address1;
        this.word1 = word1;
        this.address2 = address2;
        this.word2 = word2;
        this.address3 = address3;
        this.word3 = word3;
    }
}
