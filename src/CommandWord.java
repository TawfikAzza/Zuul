
    /**
     * Representations for all the valid command words for the game

     * along with a string in a particular language.

     *

     * @author Michael Kölling and David J. Barnes
     * @version 2011.08.10
     */
    public enum CommandWord
    {
        // A value for each command word along with its
        // corresponding user interface string.
        GO("go"), BACK("back"),LOOK("look"),
        EAT("eat"), TAKE("take"), DROP("drop"),
        INVENTORY("inventory"), INFO("info"), HELP("help"),
        QUIT("quit"), UNKNOWN("?");

        // The command string.
        private String commandString;
        /**

         * Initialize with the corresponding command string.

         * @param commandString The command string.

         */
        CommandWord(String commandString)
        {
            this.commandString = commandString;
        }
        /**
         * @return The command word as a string.

         */
        public String toString()
        {
            return commandString;
        }
    }

