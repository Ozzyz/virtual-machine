package vm;

import static vm.Bytecode.*;
public class ProgramTest {
    static int[] testCode = {
            ICONST, 1,
            ICONST, 2,
            IADD,
            PRINT,
            HALT
    };

    public static void main(String[] args) {
        VM vm = new VM(testCode, 0, 0);
        vm.cpu();
    }
}
