public class Memory 
{
    // Fields 
    public byte[] bytes = new byte[256]; // array of bytes initialized with 256
    LongWord result = new LongWord();
    int value;
    int v;
    int res;

    // methods
    public LongWord read(LongWord address, int numBytes)// reads into memory to extract data 
    {
        // pointer to memory address as integer
        int memoryAddress = address.getSigned();
        
        // Error handling for number of bytes allowed to be accessed
        if (0 < numBytes && numBytes > 4)
        {
            System.out.println("Error! Illegal Argument Value!");
        }
        else
        {
            for(int i = memoryAddress; i < bytes.length - (bytes.length - numBytes); i++) // Loop through byte array  ( memory)
            {
                Byte value = bytes[i]; // take the byte from the memory address out of the memory
                v = Byte.toUnsignedInt(value); // convert the byte into an unsigned integer
                res += v << (numBytes - i - 1) * 8; // take that int and leftshift by the numBytes - memAddress and 1 and multiply my 8 then store incremetally into res counter
                result.set(res); // result sets the value of res
            }
        }
        return result;
    }

    public void write(LongWord address, LongWord word, int numBytes)// reads into memory to extract data
    {
        int memoryAddress = address.getSigned(); // pointer to memory address as integer
       
        if (0 < numBytes && numBytes > 4) // check for number of bytes being 4 or less and greater than 0
        {
            System.out.println("Error! Illegal Argument Value!");
        }
        else
        {
            // the for loop is the pointer for the amount of the memory address within the bytes in array 
            for(int i = memoryAddress; i < bytes.length - (bytes.length - numBytes); i++)
            {
                v = word.getSigned();  //converts the longword to int 
                value = v >> (numBytes - i - 1) * 8; //take that int and rightshift by the numBytes - memAddress and 1 and multiply my 8 then store in int
                bytes[i] = (byte) value; // cast value to byte and store in index of memory
                 
            }
            
        }
        
    }

}
