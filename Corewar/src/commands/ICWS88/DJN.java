package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class DJN extends OPCode {
    
    public DJN (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "DJN";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeA == '#') players.kill();
        else {
            super.B--;
            if (B != 0) players.updatePosition(A);
        }
    }
}
