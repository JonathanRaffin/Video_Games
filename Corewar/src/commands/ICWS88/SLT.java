package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class SLT extends OPCode {
    
    public SLT (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "SLT";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeB == '#') players.kill();
        else super.skip(players, A < B, A, B);
    }
}