package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class JMP extends OPCode {
    
    public JMP (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "JMP";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeB == '#') players.kill();
        else super.jump(players, true, A, B);
    }
}
