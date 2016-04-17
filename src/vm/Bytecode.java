package vm;

/**
 * Created by Ozzy on 17.04.2016.
 */
public class Bytecode {
    public static final short IADD = 1; // Add integers
    public static final short ISUB = 2;
    public static final short IMUL = 3;
    public static final short LT = 4; // int less than
    public static final short GTE = 5; // greater or equal
    public static final short EQ = 6; // int equal
    public static final short BR = 7; // branch
    public static final short BNE = 8; // Branch if not equal
    public static final short ICONST = 9; //push integer constant
    public static final short LOAD = 10; // Load from local
    public static final short GLOAD = 11; // LOad from global
    public static final short STORE = 12; // Store local
    public static final short GSTORE = 13; // Store global
    public static final short PRINT = 14; // Print TOS
    public static final short POP = 15; // Pop TOS
    public static final short HALT = 16;

    public static class Instruction {
        String name;
        int numOperands = 0;
        public Instruction(String name){
            this.name = name;
        }
        public Instruction(String name, int numOperands){
            this.name = name;
            this.numOperands = numOperands;
        }
    }

    // LIST OF ALL AVAILABLE INSTRUCTIONS AND THEIR NUMBER OF OPERANDS
    public static Instruction[] instructions = {
            null, // Instruction number 0 doesn't exist
            new Instruction("IADD"),
            new Instruction("ISUB"),
            new Instruction("IMUL"),
            new Instruction("LT"),
            new Instruction("GTE"),
            new Instruction("EQ"),
            new Instruction("BR", 1),
            new Instruction("BNE"),
            new Instruction("ICONST",1),
            new Instruction("LOAD", 1),
            new Instruction("GLOAD",1),
            new Instruction("STORE",1),
            new Instruction("GSTORE",1),
            new Instruction("PRINT"),
            new Instruction("POP"),
            new Instruction("HALT")
    };


}
