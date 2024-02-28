package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class DAT extends OPCode {
    
    public DAT () {
        this(' ', 0, ' ', 0);
    }

    public DAT (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "DAT";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        players.kill();
    }
}
