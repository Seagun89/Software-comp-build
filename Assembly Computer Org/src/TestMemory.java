public class TestMemory {
    static Memory memory = new Memory();
    private static LongWord a = new LongWord();
    private static LongWord b = new LongWord();
    private static LongWord c = new LongWord();
    public static void main(String[] args) 
    {
        runTests();
    }
    public static void runTests()
    {
    
        a.set(0);
        b.set(41394);
        c.set(0);
        System.out.println(b.toString());
        //System.out.println(b.getSigned());
        memory.write(a, b, 2);
        System.out.println(memory.read(c, 2));
        
    }
}
