package commands.ICWS88;

import commands.OPCode;
import computer.*;
import operations.Operations;

public class MOV extends OPCode {
    
    public MOV (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "MOV";}

    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeB == '#') players.kill();
        else {
            int position = players.getPosition();
            int oldPos = Operations.getPos(position, A, core.length);
            int newPos = Operations.getPos(oldPos, B, core.length);
            core[newPos].setCommand(core[oldPos].getCommand());
            if (core[newPos].getCommand().getName() == "DAT") players.DATPlaced(newPos); 
            players.incrementPosition();
        }
        
    }
}
