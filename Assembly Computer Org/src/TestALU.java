public class TestALU 
{
    // fields 
    public static LongWord a = new LongWord();
    public static LongWord b = new LongWord();
    public static ALU alu = new ALU(); // initialization of ALU

    // Main
    public static void main(String[] args) 
    {
        runTests();
    }

    // methods 
    static void runTests()
    {
        rippleAdderTest();
    }

    static void rippleAdderTest()
    {
        // set 31st bit same as number -2147483648
        // Setting the LongWords for test purposes.
        // for code 010 if either of the longwords aren't zero then 
        //not();. Else use the xor(); b longword has to be set to 0 to use not()
        // use .set(amount) for the alu code 110,101, and 111
        a.set(3); 
        System.out.println(a.toString());
        b.set(1);
        System.out.println(b.toString());
        // cin being true for subtraction and  false for addition
        // Testing the ALU
        // Change the Code with ALU Code & 2 longwords with the set func
        System.out.println("ALU Operation Answer: " + alu.operate(3, a, b));
    }
}
