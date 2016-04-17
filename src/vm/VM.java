package vm;
import static vm.Bytecode.*;
public class VM {
    int[] code; // Code memory
    int[] stack; // Stack
    int[] data; // Data memory


    // Local registers
    int sp = -1; //Stack pointer
    int fp; //Frame pointer
    int ip; // Instruction pointer

    // Maybe add A,X,Y,Z registers as 6502?


    public VM(int[] code, int start, int datasize) {
        this.code = code;
        this.ip = start;
        data = new int[datasize];
        stack = new int[5]; // Set max size of stack
    }

    public void cpu(){
        while(ip < code.length) {
            int opcode = code[ip]; // Fetch opcode from code memory
            ip++;

            printInstruction(opcode);

            // Decode
            switch (opcode) {

                case ICONST:
                    int value = code[ip]; // Fetch operand
                    ip ++;
                    sp++;
                    stack[sp] = value;  // Push it onto the stack
                    break;
                case PRINT:
                    // Print also pops the element
                    value = stack[sp];
                    sp--;
                    System.out.println(value); // Print TOS
                    break;
                case IADD:
                    int b = stack[sp];
                    sp--;
                    int a = stack[sp];
                    stack[sp] = a+b;

                    break;
                case HALT:
                    return;
            }


        }
    }

    public void printInstruction(int opcode){
        // Prints out the name of the belonging bytecode, along with the arguments given
        Instruction instr = Bytecode.instructions[opcode];

        String ops = "";
        if( instr.numOperands == 1){
            ops += Integer.toString(code[ip+1]);
        }
        else if( instr.numOperands == 2){
            ops += Integer.toString(code[ip+1])+  " ";
            ops += Integer.toString(code[ip+2])+ " ";

        }
        System.err.printf("%04d: %s %s \n", ip, instr.name, ops);
    }

}
