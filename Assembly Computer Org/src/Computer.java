public class Computer 
{
    // Fields
    public boolean haltFlag = false; // halt conditional flag
    public ALU alu = new ALU(); // initializing ALU class
    public Memory memory = new Memory(); // initializing Memory class
    private LongWord[] registers = new LongWord[16]; // registers array of longwords
    private LongWord PC; // program counter
    private LongWord IR; // instruction register
    private LongWord OP1; // operand 1
    private LongWord OP2; // operand 2 
    private LongWord Result; // result of ALU computation 
    private LongWord code; // OP Code
    private LongWord register1; // index register for move
    private LongWord OP1Index; // OP1 index for register
    private LongWord OP2Index; // OP2 index for register
    private LongWord destIndex; // destination index 
    private LongWord evenJump; // longword need for jump 
    LongWord Jump;
    int opcode;
    // Constructor
    public Computer() // initializing all fields in computer class 
    {
        for (int index = 0; index < 16; index++)
        {
            registers[index] = new LongWord();
        }
        PC = new LongWord();
        IR = new LongWord();
        OP1 = new LongWord();
        OP2 = new LongWord();
        Result = new LongWord();
        code = new LongWord();
        register1 = new LongWord();
        OP1Index = new LongWord();
        OP2Index = new LongWord();
        destIndex = new LongWord();
        evenJump = new LongWord();
        Jump = new LongWord();
    }

    // Methods
    public void run()
    {
        for(int i = PC.getSigned(); i < registers.length - 14; i++) // loop through registers starting from PC address
        {
            fetch();
            decode();
            execute();
            if (haltFlag == true) // if statement for halt bool flag
            {
                break;
            }
            store();     
        }
    }

    private void fetch()
    {
        IR = memory.read(PC, 4); // PC goes into memory and extracts 4 bytes and stores in instruction register
        System.out.println(IR.toString());
        System.out.println("Instruction Recieved.");
        PC.set(this.PC.getSigned() + 2); // incremented PC by 2
        System.out.println(PC.getSigned());
        System.out.println("PC Incremented.");
        System.out.println("Fetch Done!");
    }
    
    private void decode()
    {
        for (int i = 0; i < 4; i++) // for loop for first OP1 in IR
        {
            if (IR.getBit(20 + i)) // starting from index 20 in the IR
            {
                OP1Index.setBit(i); 
            } 
            else if (!IR.getBit(20 + i))
            {
                OP1Index.clearBit(i);
            }
        }
        OP1.copy(OP1Index);
        for (int i = 0; i < 4; i++) // for loop for second OP2 in IR
        {
            if (IR.getBit(16 + i)) // starting from index 16 in the IR
            {
                OP2Index.setBit(i);
                
            } 
            else if (!IR.getBit(16 + i))
            {
                OP2Index.clearBit(i);
            }
        }
        OP2.copy(OP2Index); // copy the OP2 index into OP2
        registers[OP1Index.getSigned()] = OP1; // Store OP1 in the index OP1Index of the registers
        registers[OP2Index.getSigned()] = OP2; // Store OP2 in the index OP2Index of the registers
        System.out.println("OP1: " + OP1.toString());
        System.out.println("OP2: " + OP2.toString());
        System.out.println("Decode done!");
    }

    private void execute()
    {
        for (int i = 0; i < 4; i++) // loop for op code in IR
        {
            if (IR.getBit(28 + i)) // starting from 28 up to 31
            {
                code.setBit(i); // set the bits of a longword code
            } 
            else if (!IR.getBit(28 + i)) 
            {
                code.clearBit(i);
            }
        }
        opcode = code.getSigned() % 8; // to be able to perform arithmetic and logical operations when the 31st bit is 1 by modulo the opcode by 8
        System.out.println("ALU opCode: " + code.toString());
        if (!code.getBit(0) && !code.getBit(1) && !code.getBit(2) && !code.getBit(3)) // Halt instruction in opcode
        {
            haltFlag = true; //sets halt flag to true
            System.out.println("Halt Instruction called.");
        }
        else if (code.getBit(0) && !code.getBit(1) && !code.getBit(2) && !code.getBit(3)) // Interrupt instruction in opcode 
        {
            if (!IR.getBit(16)) // if the 16th bit in the IR is 0
            {
                for (int i = 0; i < 16; i++)
                {
                    System.out.println("Interrupt instruction 0: " + registers[i]); // print all the registers
                }
            }
            else if (IR.getBit(16)) // if the 16th bit in IR is 1 
            {
                for (int i = 0; i < 256; i++)
                {
                    System.out.println("Interrupt instruction 1: " + memory.bytes[i]); // print all of memory (memory dump)
                }
                 
            }
        }
        else if (!code.getBit(0) && code.getBit(1) && !code.getBit(2) && !code.getBit(3)) // Move instruction in Opcode 
        {
            for (int i = 0; i < 4; i++) // for loop to get the register1
            {
                if (IR.getBit(16 + i)) // starting from 16th bit to 19
                {
                    register1.setBit(i); // setting the register index 
                    
                } 
                else if (!IR.getBit(16 + i))
                {
                    register1.clearBit(i);
                }
            }
            OP2.copy(register1); // copy the new value into OP2
            registers[register1.getSigned()] = OP2; // put the longword OP2 into the register array
            
            System.out.println(register1.toString());
            System.out.println("Move Instruction done!");
        } 
        else if (code.getBit(0) && code.getBit(1) && !code.getBit(2) && !code.getBit(3)) // Jump Instruction in Opcode
        {
            for (int i = OP2.getSigned(); i < memory.bytes.length; i++) // for loop through memory 
            {
                if (i % 2 == 0) // if the OP2 is even 
                {
                    evenJump = registers[PC.getSigned()].shiftRightLogical(1); // use PC as an index for the registers array and shift to the right by 1 and store in longword evenJump
                    memory.bytes[i] = (byte) Math.abs(evenJump.getSigned()); // case the evenjump longword which was the abs val and store in new space for memory 
                }
                System.out.println(memory.bytes[i]); // print out the memory bytes
            }
            
            System.out.println("JUMP Instruction done!");
        }
        else if (!code.getBit(0) && !code.getBit(1) && code.getBit(2) && !code.getBit(3))
        {
            // bonus
        }
        else if (code.getBit(0) && !code.getBit(1) && code.getBit(2) && !code.getBit(3))
        {
            //bonus
        }
        Result = alu.operate(opcode, OP1, OP2); // resulting longword of the ALU operation 
        System.out.println("Result of ALU: " + Result.toString());
        System.out.println("Execute done!");
    }

    private void store()
    {
        for (int i = 0; i < 4; i++) // for loop for destination index 
        {
            if (IR.getBit(24 + i)) //staring in the 24th bit up 4 bits of IR
            {
                destIndex.setBit(i); // setting the bit to destIndex longword
                
            } 
            else if (!IR.getBit(24 + i))
            {
                destIndex.clearBit(i);
            }
        }
        //Result.copy(destIndex);
        registers[destIndex.getSigned()] = Result; // store the result in the index destination found earlier in the registers array
        if (code.getSigned() == 3) // if the opCode is 3
        {
            Jump = PC.shiftLeftLogical(1); // shift the PC back to the left by 1
        }
        
        System.out.println("Store Done!");
    }

    public void preload(String[] string)
    {
        LongWord instruction = new LongWord();
        LongWord padBits = new LongWord();
        char one = '1'; // char to equal to 1 for replaceString
        for (int i = 0; i < string.length; i++) // Loop through the string array
        {
            String replaceString = string[i].replace(" ", ""); // replace every string's spaces with no space
            for(int j = 0; j  < replaceString.length(); j++) //  go through the string itself
            {
                if (replaceString.charAt(j) == one) // if the char is 1 
                {
                    instruction.setBit((replaceString.length()-1) - j); // set the instruction longowrd to the 15 - i
                }
            }
            if (string.length % 2 == 0) // if the string array is even 
            {
                PC.set(0); // set  PC to 0 for the address
                memory.write(PC, instruction, 2); // write the instruction longword to the address in memory for 2 bytes
            }
            else if (string.length % 2 != 0) // if the string array is odd 
            {
                memory.write(PC, padBits, 2); // write into memory the padded bits of 0 in the address PC
            }
        }
    }
}
