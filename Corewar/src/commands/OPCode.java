package commands;

import computer.*;
import operations.Operations;

public abstract class OPCode {

    protected char typeA;
    protected int A;
    protected char typeB;
    protected int B;

    /**
     * Allows you to create an operator code.
     * @param typeA : the OpCode A field (for ICWS88 standard : ' ', '#' or '@')
     * @param A : the value of the OpCode A field
     * @param typeB : the OpCode B field (for ICWS88 standard : ' ', '#' or '@')
     * @param B : the value of the OpCode B field
     */
    public OPCode (Character typeA, Integer A, Character typeB, Integer B) {
        this.typeA = typeA;
        this.A = A;
        this.typeB = typeB;
        this.B = B;
    }

    /**
     * @return the type of the OpCode A field (for ICWS88 standard : ' ', '#' or '@')
     */
    public char getTypeA() {
        return this.typeA;
    }
    
    /**
     * @return the value of the OpCode A field
     */
    public int getA() {
        return this.A;
    }
    
    /**
     * @return the type of the OpCode B field (for ICWS88 standard : ' ', '#' or '@')
     */
    public char getTypeB() {
        return this.typeB;
    }
    
    /**
     * @return the value of the OpCode B field
     */
    public int getB() {
        return this.B;
    }
    
    /**
     * Sets the value of the OpCode A field with the value given as argument.
     * @param value : the value to be taken by the OPCode A field 
     */
    public void setA(int value) {
        this.A = value;
    }
    
    /**
     * Sets the value of the OpCode B field with the value given as argument.
     * @param value : the value to be taken by the OPCode B field 
     */
    public void setB(int value) {
        this.B = value;
    }
    
    /**
     * Executes an OpCode for a given player and a given core.
     * @param players : the player who executes the command
     * @param core : the core in which the command is executed
     * @param A
     * @param B
     */
    public abstract void execute(ProcessTracker players, Memory[] core, int A, int B);
    
    /**
     * @return the OpCode name
     */
    public abstract String getName();

    /**
     * Allows to make a sum or a difference according to the multiplier given as argument.
     * This function is used by OpCodes ADD and SUB.
     * @param position
     * @param players
     * @param core
     * @param multiplier
     * @param A
     * @param B
     */
    protected void sum (int position, ProcessTracker players, Memory[] core, int multiplier, int A, int B) {
        int pos = Operations.getPos(position, B, core.length);
        Command com = core[pos].getCommand();
        int noEvalA = core[players.getPosition()].getCommand().getA();
        int noEvalB = core[players.getPosition()].getCommand().getB();
        if (this.typeA == '#') com.setB(com.getB() + noEvalA*multiplier);
        else {
            com.setA(com.getA() + noEvalA*multiplier);
            com.setB(com.getB() + noEvalB*multiplier);
        }
        players.incrementPosition();
    }

    /**
     * Allows to jump to another memory address.
     * This function is used by OpCodes JMZ, JMP and JMN.
     * @param players
     * @param condition
     * @param A
     * @param B
     */
    protected void jump (ProcessTracker players, boolean condition, int A, int B) {
        if (condition) players.updatePosition(A); 
        else players.incrementPosition();
    }

    /**
     * Allows to skip a memory address.
     * This function is used by OpCodes CMP and SLT.
     * @param players
     * @param condition
     * @param A
     * @param B
     */
    protected void skip (ProcessTracker players, boolean condition, int A, int B) {
        if (condition) players.updatePosition(2);
        else players.incrementPosition(); 
    }
}