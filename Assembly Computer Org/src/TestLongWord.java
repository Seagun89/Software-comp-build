
import java.io.IOException;
import java.util.Scanner;

public class TestLongWord extends LongWord
{
    // Fields
    static String in;
    static int set;
    static int get;
    static int clear;
    static int toggle;
    static int copy;
    static int left;
    static int right;
    static int rightA;
    static int s;

    // Methods
    public static void main(String[] args) throws IOException
    {
       //runTests();
       runTests();
    }
    
    public static void runTests() throws IOException
    {
        // test for copy with this longwordCopy
        Scanner input = new Scanner(System.in); // user input
        LongWord longword1 = new LongWord(); // initialization of a longword
        LongWord longword2 = new LongWord();
        LongWord result = new LongWord();
        System.out.println("Longword Initialized - "); 
        while (in != "done") // loop till done is called
        { 
            System.out.println("\nWhich function do you wish to perform? ");
            in = input.next();
            switch (in) // switch case for commands of user and each function attached
            {
                case("setBit"):
                    System.out.println("Select the index to set the bit: ");
                    set = input.nextInt();
                    if (set < 32 && set >= 0) // Setting bounds for setBit
                    {
                        longword1.setBit(set);
                        System.out.println("Task Completed!");
                    }
                    else 
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31).\n");
                    } 
                    break;
                case("getBit"):
                    System.out.println("Select an index to get its boolean value: ");
                    get = input.nextInt();
                    if(get < 32 && get >= 0) // Setting bounds for getBit
                    {
                        System.out.println("Boolean Value of specified index: " + longword1.getBit(get));
                    }
                    else 
                    {   
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                case ("clearBit"):
                    System.out.println("Select the index to clear a bit: ");
                    clear = input.nextInt();
                    if(clear < 32 && clear >= 0) // Setting bounds for clearBit
                    {
                        longword1.clearBit(clear);
                        System.out.println("Task Completed!");
                    }
                    else 
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                case ("toggleBit"):
                    System.out.println("Select the index to toggle a bit: ");
                    toggle = input.nextInt();
                    if(toggle < 32 && toggle >= 0) // Setting bounds for toggleBit
                    {
                        longword1.toggleBit(toggle);
                        System.out.println("Task Completed!");
                    }
                    else 
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                case ("copy"):
                    LongWord longwordCopy = new LongWord(); // test longword for copy
                    longwordCopy.setBit(16);
                    longword1.copy(longwordCopy);
                    break;
                case ("getsigned"):
                    System.out.println("Longword signed value: " + longword1.getSigned());
                    break;
                case ("getunsigned"):
                    System.out.println("Longword unsigned value: " + longword1.getUnsigned());
                    break;
                case ("set"):
                    System.out.println("What value do you want to convert to binary?");
                    s = input.nextInt();
                    longword1.set(s);
                    System.out.println("Task Completed!");
                    break;
                case ("and"):
                    longword2.setBit(1);
                    longword2.setBit(2);
                    longword2.setBit(4);
                    result = longword1.and(longword2);
                    System.out.println("AND Result: " + result.toString());
                    System.out.println("Task Completed!");
                    break;
                case ("or"):
                    longword2.setBit(1);
                    longword2.setBit(2);
                    longword2.setBit(4);
                    result = longword1.or(longword2);
                    System.out.println("OR Result: " + result.toString());
                    System.out.println("Task Completed!");
                    break;
                case ("xor"):
                    longword2.setBit(1);
                    longword2.setBit(2);
                    longword2.setBit(4);
                    result = longword1.xor(longword2);
                    System.out.println("XOR Result: " + result.toString());
                    System.out.println("Task Completed!");
                    break;
                case ("not"):
                    result = longword1.not();
                    System.out.println("NOT Result: " + result.toString());
                    System.out.println("Task Completed!");
                    break;
                case ("iszero"):
                    System.out.println("The longword is empty: " + longword1.isZero());
                    break;
                case ("leftshiftL"):
                    System.out.println("What index amount would you like to shift these bits to?");
                    left = input.nextInt();
                    if(left < 32 && left >= 1) // Setting bounds for leftShift
                    {
                        result = longword1.shiftLeftLogical(left);
                        System.out.println("LeftShift: " + result.toString());
                        System.out.println("Task Completed!");
                    }
                    else
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                    case ("rightshiftL"):
                    System.out.println("What index amount would you like to shift these bits to?");
                    right = input.nextInt();
                    if(right < 32 && right >= 1) // Setting bounds for leftShift
                    {
                        result = longword1.shiftRightLogical(right);
                        System.out.println("RightShift: " + result.toString());
                        System.out.println("Task Completed!");
                    }
                    else
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                    case ("rightshiftA"):
                    System.out.println("What index amount would you like to shift these bits to?");
                    rightA = input.nextInt();
                    if(rightA < 32 && rightA >= 1) // Setting bounds for leftShift
                    {
                        result = longword1.shiftRightArithmetic(rightA);
                        System.out.println("RightShift: " + result.toString());
                        System.out.println("Task Completed!");
                    }
                    else
                    {
                        System.out.println("Error! Value out of Bounds.");
                        System.out.println("Please input appropriate value (0 - 31). \n");
                    }
                    break;
                case ("done"):
                    in = "done";
                    break;
                default:
                    System.out.println("Invalid function. Try Again."); // error handler for wrong input
                    break;
            }
        }
        System.out.println("Longword: " + longword1.toString()); // prints out longword created 
        input.close();
    }
    
}
/* Tips:
-To turn the longword into a negative number you must always perform the function getsigned after setting the 31st bit.
- When a positive number is made negative with getsigned the negative longword is the negative form of the positive minus 1.
*/