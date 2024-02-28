package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class JMN extends OPCode {

    public JMN (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "JMN";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeB == '#') players.kill();
        else super.jump(players, B != 0, A, B);
    }
}
