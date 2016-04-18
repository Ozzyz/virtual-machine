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

            // Print out the name and operand of the current opcode
            printInstruction(opcode);

            // Declare variables here, since the scope of a switch case is kinda weird
            int a;
            int b;

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
                case LOAD:
                    // Relative load
                    // Loads from a stack offset
                case GLOAD:
                    // Fetch memory address from TOS
                    // Get value stored in memory
                    // Add the value onto the stack
                    a = code[ip];  // Memory address
                    ip++;
                    sp++;
                    stack[sp] = a;

                case ISUB:
                    b = stack[sp];
                    sp--;
                    a = stack[sp];
                    stack[sp] = a-b;
                case IADD:
                    b = stack[sp];
                    sp--;
                    a = stack[sp];
                    stack[sp] = a+b;
                    break;
                case IMUL:
                    b = stack[sp];
                    sp--;
                    a = stack[sp];
                    stack[sp] = a*b;
                case POP:
                    // Get rid of top element of stack
                    sp--;
                case LT:
                    // Compare 2nd (a) item on stack to 1st (b)
                    // If a < b, push 1
                    // else push 0
                    b = stack[sp];
                    sp--;
                    a = stack[sp];
                    if(a<b){
                        stack[sp] = 1;
                    }
                    else{ stack[sp] = 0;}
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
