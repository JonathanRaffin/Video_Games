package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class SUB extends OPCode {

    public SUB (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "SUB";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeB == '#') players.kill();
        else {
            int position = players.getPosition();
            super.sum(position, players, core, -1, A, B);
        }
    }
}
