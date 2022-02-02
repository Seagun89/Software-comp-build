public class TestComputer 
{
    /*
    uncomment out parts of code you want to test 
    */
    public static void main(String[] args) 
    {
        runTest();
    }

    public static void runTest()
    {
        cpuTest1();
    }
    public static void cpuTest1() 
    {
        // preload codition of needing even instructions
        //System.out.println("preload codition: ");
        //Computer cpu = new Computer();
        //cpu.preload(new String[]{"0001 0001 1001 0000"});
        //System.out.println("Preload done!");
        //cpu.run();
        //System.out.println("--------------------------------------------");

        // Arithm. and Logical Instruction
        System.out.println("Arith & Logic instruction: ");
        Computer cpu1 = new Computer();
        cpu1.preload(new String[]{"1011 0000 0011 0001", "0000 0000 0000 0000"});
        System.out.println("Preload done!");
        cpu1.run();
        System.out.println("--------------------------------------------");

        // Halt Instruction
        /*
        System.out.println("Halt instruction: ");
        Computer cpu2 = new Computer();
        cpu2.preload(new String[]{"0000 0000 0011 0001", "0000 0000 0000 0000"});
        System.out.println("Preload done!");
        cpu2.run();
        System.out.println("--------------------------------------------");
        */
        // INT Instruction 0
        //System.out.println("INT Instruction 0: ");
        //Computer cpu3 = new Computer();
        //cpu3.preload(new String[]{"0001 0001 0010 0010", "0000 0000 0000 0000"});
        //System.out.println("Preload done!");
        //cpu3.run();
        //System.out.println("--------------------------------------------");

        // INT Instruction 1
        //System.out.println("INT Instruction 1: ");
        //Computer cpu4 = new Computer();
        //cpu4.preload(new String[]{"0001 0001 0010 0011", "0000 0000 0000 0000"});
        //System.out.println("Preload done!");
        //cpu4.run();
        //System.out.println("--------------------------------------------");

        // MOV Instruction 
        //System.out.println("MOV Instruction 1: ");
        //Computer cpu5 = new Computer();
        //cpu5.preload(new String[]{"0010 0001 0010 0011", "0000 0000 0000 0000"});
        //System.out.println("Preload done!");
        //cpu5.run();
        //System.out.println("--------------------------------------------");

        // Jump Instruction
        //System.out.println("Jump Instruction: ");
        //Computer cpu6 = new Computer();
        //cpu6.preload(new String[]{"0011 0001 0001 1010", "0000 0000 0000 0000"});
        //System.out.println("Preload done!");
        //cpu6.run();
        //System.out.println("-------------------------------------------");
    }
}
