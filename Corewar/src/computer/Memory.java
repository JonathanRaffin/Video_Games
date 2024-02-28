package computer;

import commands.Command;

public class Memory {
    
    private Command command;

    public Memory () {
        this.command = new Command ("DAT", ' ', 0, ' ', 0);
    }

    public Command getCommand () {return this.command;}

    public void setCommand (Command command) {
        this.command = command;
    }
}
