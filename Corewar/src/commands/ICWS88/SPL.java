package commands.ICWS88;

import commands.OPCode;
import computer.*;

public class SPL extends OPCode {
    
    public SPL (Character typeA, Integer A, Character typeB, Integer B) {
        super(typeA, A, typeB, B);
    }

    public String getName() {return "SPL";}

    /* !!! La méthode execute() de SPL n'est pas totalement implémentée !!!
     * Lorsque la commande SPL est appelée, elle ne devrait pas tuer le 
     * processus lorsque typeA est différent du #.
     */
    public void execute (ProcessTracker players, Memory[] core, int A, int B) {
        if (super.typeA == '#') players.kill();
        else players.kill();
    }
}