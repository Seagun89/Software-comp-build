/* 
Author: George Tackie 
Assignement: 2 (The ALU and Ripple Adder Carry)
V.1
*/
public class ALU 
{
    // fields
    private static LongWord resultLongWord = new LongWord(); // longword for ripplecarryadd
    LongWord result = new LongWord(); // longword for operations
    LongWord psw = new LongWord(); // flag register
    private static boolean aXORB; // bool for aXORb
    private static boolean aANDB; // bool for aANDb 
    private static boolean cout; // carry out
    private static boolean sum; // sum of ripple adder
    Status zeroFlag = Status.ZEROFLAG; // Enum for zeroflag
    Status negativeFlag = Status.NEGATIVEFLAG; // Enum for negflag
    Status coFlag = Status.COFLAG; // Enum for carryout flag
    Status ofFlag = Status.OFFLAG; // Enum overflow flaga

    // enum
    public enum Status 
    {
        ZEROFLAG, NEGATIVEFLAG, COFLAG, OFFLAG;
    }

    // properties
    public LongWord getPsw() 
    {
        if (result.isZero()) // zero flag 
        {
            psw.setBit(zeroFlag.ordinal());
            System.out.println(zeroFlag);
        }
        if (result.getBit(31)) // neg flag
        {
            psw.setBit(negativeFlag.ordinal());
            System.out.println(negativeFlag);
        }
        if (result.getBit(31) != cout) // coflag
        {   
            psw.setBit(coFlag.ordinal());
            System.out.println(coFlag);              
        }
        if (!result.getBit(31)) // offlag
        {
            psw.setBit(ofFlag.ordinal());
            System.out.println(ofFlag);
        }
        System.out.println("Program Status Word: " + psw);
        return psw;
    }
    
    // methods
    private LongWord rippleCarryAdd(LongWord a, LongWord b, boolean cin)
    { 
        if (cin == true) // subtraction 
        {
            for (int i = 0; i < 32; i++)
            {
                aXORB = a.getBit(i) != !b.getBit(i); 
                sum = aXORB != cin;
                aANDB = a.getBit(i) && !b.getBit(i);
                cout = aANDB || ((aXORB) && cin);   
                cin = cout;
                if (sum)
                {
                    resultLongWord.setBit(i);
                }        
            }   
        }            
        if (cin == false) // addition
        {
            for (int i = 0; i < 32; i++)
            {
                aXORB = a.getBit(i) != b.getBit(i); 
                sum = aXORB != cin;
                aANDB = a.getBit(i) && b.getBit(i);
                cout = aANDB || ((aXORB) && cin);
                cin = cout;
                if (sum)
                {  
                    resultLongWord.setBit(i); 
                }   
            }      
        }
        return resultLongWord;
    }
    public LongWord operate(int code, LongWord op1, LongWord op2)
    {
        switch(code)
        {
            case (0): // operation for AND
                result  = op1.and(op2);
                getPsw();
            break;
            case (1): // operation for OR 
                result = op1.or(op2);
                getPsw();
            break;
            case (2): // operation for NOT/XOR
                if (op2.getSigned() == -1)
                {
                    result = op1.not();
                }
                else
                {
                    result = op1.xor(op2);
                }
                getPsw();
            break;
            // if unsigned coflag
            case (3): // addition operation
                result = this.rippleCarryAdd(op1, op2, false);
                getPsw();
            break;
            // if signed offlag
            case (4): // subtraction operation
                result = this.rippleCarryAdd(op1, op2, true);
                getPsw();
            break;
            case (5): // Left shift operation
                result = op1.shiftLeftLogical(op2.getSigned());
                getPsw();       
            break;
            // both rightshift are the same if not negative 
            case (6): // right shift operation logical
                result = op1.shiftRightLogical(op2.getSigned());
                getPsw();
            break;
            case (7): // right shift operation Arithmetic
                result = op1.shiftRightArithmetic(op2.getSigned());
                getPsw();
            break;
            default: // Error handler for invalid code inputs
                System.out.println("ERROR! INVALID CODE INPUTED.");
            break;
        }       
        return result;
    }
}
