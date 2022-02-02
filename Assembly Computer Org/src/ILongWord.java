public interface ILongWord 
{
    // returns a string of 0's and 1's & followed by equivalent hex notation
    @Override String toString();
    // Get bit i as a boolean
    boolean getBit(int i);
    // set bit i (set to true/1)
    void setBit(int i);
    // clear bit i (reset to false/0)
    void clearBit(int i); 
    // toggles (flips) bit i
    void toggleBit(int i); 
    // returns the value of this long-word as an int
    int getSigned();
    // returns the value of this long-word as a long
    long getUnsigned();
    // set the value of the bits of this long-word (used for testing only)
    void set(int value);
    // copies the values of the bits from & another long-word into this one
    void copy(LongWord other);
    // left-shift this long-word by amount bits (padding with 0’s), creates a new long-word
    LongWord shiftLeftLogical(int amount); 
    // right-shift this long-word by amount bits (padding with 0’s), creates new long-word
    LongWord shiftRightLogical(int amount);
    // right-shift this long-word by amount bits (sign-extending), creates a new long-word 
    LongWord shiftRightArithmetic(int amount);
    // negate this LongWord, creating another
    LongWord not(); 
    // and two LongWords, returning a third
    LongWord and(LongWord other); 
    // or two LongWords, returning a third
    LongWord or(LongWord other); 
    // xor two LongWords, returning a third
    LongWord xor(LongWord other); 
    // returns true if all bits are 0’s in this long-word
    boolean isZero();
    
}
