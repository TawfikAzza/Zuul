import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    //private static final String[] validCommands = {
   //     "go", "look", "eat","take","drop", "inventory","info","help" ,"back" ,"quit"
   // };
    HashMap<String,CommandWord> validCommands = new HashMap<>();
    /**
     * Representations for all the valid command words for the game.

     *

     * @author Michael Kölling and David J. Barnes

     * @version 2011.08.09

     */


    /**
     * Print all valid commands to System.out.

     */

    public String getCommandList(){
        String commandText="";

        Set<String> keys = validCommands.keySet();
        for (String command: keys) {
            if(!command.equals("unknown"))
                commandText += command +" ";
        }
        return commandText;
    }
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        /*validCommands.put("aller",CommandWord.GO);
        validCommands.put("quitter",CommandWord.QUIT);
        validCommands.put("go",CommandWord.GO);
        validCommands.put("back",CommandWord.BACK);
        validCommands.put("look",CommandWord.LOOK);
        validCommands.put("eat",CommandWord.EAT);
        validCommands.put("take",CommandWord.TAKE);
        validCommands.put("drop",CommandWord.DROP);
        validCommands.put("inventory",CommandWord.INVENTORY);
        validCommands.put("info",CommandWord.INFO);
        validCommands.put("aide",CommandWord.HELP);
        validCommands.put("quit",CommandWord.QUIT);
        validCommands.put("unknown",CommandWord.UNKNOWN);*/
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }
    /**
     * This part getCommandWord has been copied from Peter Stegger
     * GitHub account.
     * I couldn't figure out how to do it, and I couldn't find the right file of Zuul
     * ***/
    public CommandWord getCommandWord(String command){
        CommandWord commandWord = validCommands.get(command);
        if(commandWord!=null){
            return commandWord;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        Set<String> keys = validCommands.keySet();
        for (String command: keys) {
            if(command.equals(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
