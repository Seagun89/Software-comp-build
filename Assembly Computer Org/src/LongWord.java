/* 
Author: George Tackie 
Assignement: 1 (The Bit and the Word)
V.1
*/
import java.util.BitSet;
public class LongWord implements ILongWord
{
    // Fields
    BitSet BitVector = new BitSet(32); // Bit-Vector
    StringBuilder stringResult = new StringBuilder(); // String interpretation of longword
    StringBuilder hexResult = new StringBuilder();
    Boolean bitBoolVal;
    int intVal;
    long longVal;

    // Constructor
    public LongWord()
    {
        BitVector.set(0, 32, false); // Setting all initialized bits to 0   
    }

    // Methods 
    @Override
    public String toString()
    {
        for (int i = 31; i >= 0; i--) // iterating thorugh bits and puts 0/false & 1/true
        {
            if (!BitVector.get(i))
            {
                stringResult.append("0").toString();
            }
            else if (BitVector.get(i))
            {
                stringResult.append("1").toString();
            }
        }
        int padding = stringResult.length() - 4; // removes 4 chars from string 
        while (padding > 0) // While string hasn't reached 0 
        {
            stringResult.insert(padding, " "); // inserts a space infront of the string without the 4 chars towards the end
            padding -= 4; // have to keep subtracting till loop is finished
        }
        String string = stringResult.toString();
        String[] splitString = string.split(" ");
        for (int i = 0; i < 8; i++)
        {
            switch (splitString[i])
            {
                case("0000"):
                    hexResult.append("0");
                    break;
                case("0001"):
                    hexResult.append("1");
                    break;
                case("0010"):
                    hexResult.append("2");
                    break;
                case("0011"):
                    hexResult.append("3");
                    break;
                case("0100"):
                    hexResult.append("4");
                    break;
                case("0101"):
                    hexResult.append("5");
                    break;
                case("0110"):
                    hexResult.append("6");
                    break;
                case("0111"):
                    hexResult.append("7");
                    break;
                case("1000"):
                    hexResult.append("8");
                    break;
                case("1001"):
                    hexResult.append("9");
                    break;
                case("1010"):
                    hexResult.append("A");
                    break;
                case("1011"):
                    hexResult.append("B");
                    break;
                case("1100"):
                    hexResult.append("C");
                    break;
                case("1101"):
                    hexResult.append("D");
                    break;
                case("1110"):
                    hexResult.append("E");
                    break;
                case("1111"):
                    hexResult.append("F");
                    break;
                default:
                    break;
            }
        }
        String hex = hexResult.toString();
        return  string + " \t" + "0x" + hex;
    }

    @Override
    public boolean getBit(int i) 
    {
       
        bitBoolVal = BitVector.get(i); // gets bool value for index of bit
        return bitBoolVal;
    }

    @Override
    public void setBit(int i) 
    {
        BitVector.set(i, true); // sets bit value to 1/true        
    }

    @Override
    public void clearBit(int i) 
    {
        BitVector.set(i, false); // clears bit value to 0/false
    }

    @Override
    public void toggleBit(int i) 
    {
        BitVector.flip(i); // flips bit value 
    }
    @Override
    public int getSigned() 
    {
        intVal = 0;
        for (int i = 0; i < 31; i++) // iterates through the longword
        {
            if (BitVector.get(i)) // if the index is true 
            {
                intVal = (int) (intVal + Math.pow(2, i));
            }       
        }
        if (BitVector.get(31)) // if 31th bit is set to true 
        {
            intVal = (int) (intVal - Math.pow(2, 31));
        }
        return intVal;
    }

    @Override
    public long getUnsigned() 
    {
        longVal = 0;
        for (int i = 0; i < 32; i++) // iterates through the longword
        {
            if (BitVector.get(i)) // if the index is true 
            {
                longVal += (1)*(long)(Math.pow(2, i)); // append 1 * 2^ith converting to long 
            }
        }
        return longVal;
    }

    @Override
    public void set(int value) 
    {
        BitVector.set(0);
        for(int i = 0; i < 32; i++)
        {   
            if (value % 2 == 1)
            {
                BitVector.set(i);  
            }
            else if (value % 2 == 0)
            {
                BitVector.clear(i);  
            }  
            value >>>= 1;       
        }
        System.out.println("Longword: " + this.getSigned());
    }

    @Override
    public void copy(LongWord other) 
    {
        for (int i = 0; i < 32; i++)
        {
            BitVector.set(i , other.getBit(i));
        }
        
    }
    /*for (int i = 31; i >= 0; i--)
    {
        if (other.getBit(i))
        {
            setBit(i);
        }
        else if (other.getBit(i) == false)
        {
            clearBit(i);
        }
    }*/
    @Override
    public LongWord shiftLeftLogical(int amount) 
    {
        LongWord result = new LongWord();
        for (int i = 0; i < 31; i++)
        {
            if(BitVector.get(i) == true)
            {
                result.BitVector.set(i + amount);
            }
        }
        return result;
    }

    @Override
    public LongWord shiftRightLogical(int amount) 
    {
        LongWord result = new LongWord();
        for (int i = 0; i < 32; i++)
        {
            if(BitVector.get(i) == true && i > amount)
            {
                result.BitVector.set(i - amount);
                
            }
            else if(BitVector.get(i) == true && i < amount)
            {
                result.BitVector.clear(i);
                
            }
           
        }         
                                                                         
        return result;
    }

    @Override
    public LongWord shiftRightArithmetic(int amount) 
    {
        LongWord result = new LongWord();
        for (int i = 0; i < BitVector.length(); i++)
        {
            if(BitVector.get(i) == true && i > amount && BitVector.get(31))
            {
                result.BitVector.set(31-amount, BitVector.length());
            }
            else if(BitVector.get(i))
            {
                result.BitVector.set(i - amount, 32);
            }
        }   
        return result;
    }

    @Override
    public LongWord not()
    {
        LongWord result = new LongWord();
        for (int i = 31; i >= 0; i--)
        {
            if (BitVector.get(i) == true) 
            {
                result.clearBit(i);
            }
            else if (BitVector.get(i) == false)
            {
                result.setBit(i);
            }
        }
        return result;
    }

    @Override
    public LongWord and(LongWord other) 
    {
        LongWord result = new LongWord();
        BitVector.and(other.BitVector.get(0, 32));
        for (int i = BitVector.length() - 1; i >= 0; i--)
        { 
            if (BitVector.get(i) == true)
            {
                result.setBit(i);
            }
        }
        return result;
    }

    @Override
    public LongWord or(LongWord other) 
    {
        LongWord result = new LongWord();
        BitVector.or(other.BitVector.get(0, 32));
        for (int i = BitVector.length() - 1; i >= 0; i--)
        { 
            if (BitVector.get(i) == true)
            {
                result.setBit(i);
            }
        }
        return result;
    }

    @Override
    public LongWord xor(LongWord other) 
    {
        LongWord result = new LongWord();
        BitVector.xor(other.BitVector.get(0, 32));
        for (int i = BitVector.length() - 1; i >= 0; i--)
        { 
            if (BitVector.get(i) == true)
            {
                result.setBit(i);
            }
        }
        return result;
    }
    
    @Override
    public boolean isZero() 
    {
        return BitVector.isEmpty();
    }   
}