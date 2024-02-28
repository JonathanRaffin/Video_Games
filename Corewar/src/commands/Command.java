package commands;

import java.lang.reflect.Constructor;

import commands.ICWS88.DAT;
import computer.*;
import operations.*;

public class Command {

    private OPCode command;
    
    /**
     * Constructor using the default standard (ICWS-88).
     * @param name : OpCode name (examples : DAT, ADD, SUB ...)
     * @param typeA : modifier of the first argument of the OpCode
     * @param A : first argument of the OpCode
     * @param typeB : modifier of the second argument of the OpCode
     * @param B : second argument of the OpCode
     */
    public Command (String name, char typeA, int A, char typeB, int B) {
        this(name, typeA, A, typeB, B, "88");
    }

    /**
     * Constructor that allows you to choose the standard you want to use.
     * @param name : OpCode name (examples : DAT, ADD, SUB ...)
     * @param typeA : modifier of the first argument of the OpCode
     * @param A : first argument of the OpCode
     * @param typeB : modifier of the second argument of the OpCode
     * @param B : second argument of the OpCode
     * @param standard : standard to use : 88 (for ICWS-88 standard) or 94 (for ICWS-94 standard)
     */
    public Command (String name, char typeA, int A, char typeB, int B, String standard) {
        try {
            Class<?> clazz = Class.forName("commands.ICWS"+standard+"."+name);
            Constructor<?> constructor = clazz.getConstructor(Character.class, Integer.class, Character.class, Integer.class);
            this.command = (OPCode)constructor.newInstance(typeA, A, typeB, B);
        } catch (Exception e) {
            System.out.println(e);
            this.command = new DAT();
        }
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getTypeA() + this.getA() + " " + this.getTypeB() + this.getB();
    }

    /**
     * @return the command name
     */
    public String getName() {
        return this.command.getName();
    }

    /**
     * @return the type of the command A field (for ICWS88 standard : ' ', '#' or '@')
     */
    public char getTypeA() {
        return this.command.getTypeA();
    }

    /**
     * @return the value of the command A field
     */
    public int getA() {
        return this.command.getA();
    }

    /**
     * @return the type of the command B field (for ICWS88 standard : ' ', '#' or '@')
     */
    public char getTypeB() {
        return this.command.getTypeB();
    }

    /**
     * @return the value of the command B field
     */
    public int getB () {return this.command.getB();}

    // TODO : JAVADOC
    public int getEvalA (ProcessTracker players, Memory[] core) {
        int A = 0;
        if (this.getTypeA() == ' ') A = this.getA();
        else if (this.getTypeA() == '#') A = 0;
        else if (this.getTypeA() == '@') {
            int position = Operations.getPos(players.getPosition(), this.getA(), core.length);
            Memory space = core[position];
            Command com = space.getCommand();
            A = com.getA() + this.getA();
        }
        return A;
    }
    
    // TODO : JAVADOC
    public int getEvalB (ProcessTracker players, Memory[] core) {
        int B = 0;
        if (this.getTypeB() == ' ') B = this.getB();
        if (this.getTypeB() == '#') B = 0;
        if (this.getTypeB() == '@') {
            int position = Operations.getPos(players.getPosition(), this.getB(), core.length);
            Memory space = core[position];
            Command command = space.getCommand();
            B = command.getB() + this.getB();
        }
        return B;
    }
    
    /**
     * Sets A value of the command with the value given as argument.
     * @param value : the value that A should take
     */
    public void setA (int value) {
        this.command.setA(value);
    }
    
    /**
     * Sets B value of the command with the value given as argument.
     * @param value : the value that B should take
     */
    public void setB (int value) {
        this.command.setB(value);
    }

    /**
     * Executes a command for a given player and a given core.
     * @param players : the player who executes the command
     * @param core : the core in which the command is executed
     */
    public void execute (ProcessTracker players, Memory[] core) {
        int A = this.getEvalA(players, core);
        int B = this.getEvalB(players, core);
        this.command.execute(players, core, A, B);
    }
}