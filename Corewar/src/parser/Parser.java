package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.Command;

public class Parser {
    
    private ArrayList<Command> commands;

    /**
     * Creates a list containing the commands of the file given in argument, 
     * and respecting the ICWS-88 standard.
     * @param file : the file containing the redcode
     */
    public Parser (String file) {
        this(file, "88");
    }

    /**
     * Creates a list containing the commands of the file given in argument, 
     * and respecting the standard also given in argument.
     * @param file : the file containing the redcode
     * @param standard : the standard to use : 88 (for ICWS-88 standard) or 94 (for ICWS-94 standard)
     */
    public Parser (String file, String standard) throws IllegalArgumentException {
        ArrayList<String> commandList = Parser.readLines(file);
        this.commands = new ArrayList<Command>();
        if (standard == "94" || standard == "88")
            for (String command : commandList)
                commands.add(Parser.toCommand(command, standard));
        else throw new IllegalArgumentException("unknown standard given in argument");
    }

    /**
     * Retrieves the list of commands contained in the ArrayList commands.
     * @return the list of commands contained in the file
     */
    public Command[] getCommands () {
        Command[] tmp = new Command[this.commands.size()];
        for (int i = 0; i < this.commands.size(); i++)
            tmp[i] = this.commands.get(i);
        return tmp;
    }

    /**
     * Creates an ArrayList containing the lines of the file.
     * @param file : a file with redcode
     * @return the lines of the file as an ArrayList
     */
    private static ArrayList<String> readLines (String file) {
        ArrayList<String> commandList = new ArrayList<String>();
        String stringPath = "redcode/" + file;
        Path absolutePath = Paths.get(stringPath).toAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(stringPath));
            String line = br.readLine();
            while (line != null) {
                if (line != null && !line.isBlank()) commandList.add(line);
                line = br.readLine();                
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file located at the following address does not exist : " + absolutePath);
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        return commandList;
    }

    /**
     * Converts a line with operator code and arguments into an ICWS-88 or ICWS-94 command (depends of standard given as argument).
     * @param line : a line with operator code and arguments
     * @param standard : the standard to use : 88 (for ICWS-88 standard) or 94 (for ICWS-94 standard)
     * @return an ICWS-88 or ICWS-94 command
     */
    private static Command toCommand (String line, String standard) {
        String[] valid88OpCode = {"MOV", "DAT", "ADD", "SUB", "JMP", "JMZ", "JMN", "CMP", "SLT", "DJN", "SPL"};
        String[] valid94OpCode = {"ADD", "CMP", "DAT", "DIV", "DJN", "END", "EQU", "JMN", "JMP", "JMZ", "MOD", "MOV", "MUL", "ORG", "SLT", "SPL", "SUB"};

        String[] parts = line.split(" ", 2);
        String command = parts[0];
        String[] arg = parts[1].split(", ");
        Character[] typeArg = {' ', ' '};

        if (arg.length != 2) return new Command("DAT", ' ', 0, ' ', 0);

        if (standard == "88" || standard == "94") {
            for (int i = 0; i < 2; i++) {
                if (checkMode(arg[i], standard) && !checkMode(arg[i].substring(1), standard) && !checkForbiddenSymbols(arg[i], standard)) {
                    typeArg[i] = arg[i].charAt(0);
                    arg[i] = arg[i].substring(1);
                }
                else if (!checkMode(arg[i], standard) && !checkForbiddenSymbols(arg[i], standard)) typeArg[i] = ' ';
                else return new Command("DAT", ' ', 0, ' ', 0);
            };
            String[] valid = null;
            if (standard == "88") valid = valid88OpCode;
            else valid = valid94OpCode;
            for (String com : valid)
                if (command.equals(com))
                    return new Command(com, typeArg[0], Integer.parseInt(arg[0]), typeArg[1], Integer.parseInt(arg[1]));
        }

        return new Command("DAT", ' ', 0, ' ', 0);
    }

    /**
     * Tests if a mode is present in the string given as argument.
     * @param s : the string to test
     * @param standard : the standard to use : 88 (for ICWS-88 standard) or 94 (for ICWS-94 standard)
     * @return true if a mode is present, otherwise false
     */
    private static boolean checkMode (String s, String standard) {
        String mode88 = "[#@<]";
        String mode94 = "[#@$*{}<>]";
        Pattern pattern = null;
        if (standard == "88") pattern = Pattern.compile(mode88);
        else pattern = Pattern.compile(mode94);
        Matcher matcher = pattern.matcher(s);
        boolean check = matcher.find();
        return check;
    }

    /**
     * Tests if a forbidden symbol for the standard given as argument is present in the string given as argument.
     * @param s : the string to test
     * @param standard : the standard to use : 88 (for ICWS-88 standard) or 94 (for ICWS-94 standard)
     * @return true if a forbidden symbol is present, otherwise false
     */
    private static boolean checkForbiddenSymbols (String s, String standard) {
        String mode88 = "[^-#@<0-9]";
        String mode94 = "[^-#@$*{}<>0-9]";
        Pattern pattern = null;
        if (standard == "88") pattern = Pattern.compile(mode88);
        else pattern = Pattern.compile(mode94);
        Matcher matcher = pattern.matcher(s);
        boolean check = matcher.find();
        return check;
    }
}