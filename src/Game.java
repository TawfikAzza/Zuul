import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    //private Room currentRoom;
    private Player player = new Player("Grandpa Student");

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, hall1, janitorRoom1, lobby1, boss1,janitorbasement1;
        Room pcRoom2, puzzleRoom2, hiddenRoom2, lobby2, cafetariat2,allyRoom2, boss2;
        Room entry3, reception3, cafetariat3, teacherRoom3, bar3, studyRoom3, boss3;
        Item book1 = new Item(1000,"book", "A Book about gardening",true);
        Item book2 = new Item(1500,"JavaBook","A book titled: Java: How to program 10th Edition",true);
        Item book3 = new Item(3000,"ItoBook","A book titled: ITO for dummies",true);
        Item coffeeCan = new Item(500,"coffee","An empty cup of coffee",true);
        Item chest1 = new Item(100000,"MagicChest","A magic looking chest",false);
        Item chest2 = new Item(150000,"BigChest","A big chest",false);
        Item table1 = new Item(50000,"OfficeTable","A office table",false);
        Item cookie = new Item(200,"MagicCookie","A colorful cookie",true,25);
        cookie.setCanBeEaten(true);
        // create the rooms First floor
        outside = new Room("outside the main entrance of the university");
        outside.addItem(coffeeCan);
        hall1 = new Room("Inside the entry hall of the University");
        janitorRoom1 = new Room("In the janitor office");
        hall1.addItem(table1);
        hall1.addItem(book3);
        lobby1 = new Room("In the lobby");
        boss1= new Room("In the boss room");
        janitorbasement1 = new Room("In the janitor basement");
        janitorbasement1.addItem(book1);
        janitorbasement1.addItem(cookie);
        // create the rooms for the second floor
        pcRoom2 = new Room("In the Pc room of the second floor");
        puzzleRoom2 = new Room("In the Puzzle room of the second floor");
        hiddenRoom2 = new Room("In the hidden room of the second floor");
        lobby2 = new Room("Im the lobby room of the second floor");
        cafetariat2 = new Room("In the cafeteria of the second floor");
        allyRoom2 = new Room("In the ally room of the second floor");
        boss2 = new Room("In the second boss room");

        // create room for the third floor

        entry3 = new Room("Entry of the third floor");
        reception3 = new Room("In the reception of the third floor");
        cafetariat3 = new Room("In the cafeteria of the third floor");
        teacherRoom3 = new Room("In the Hidden teacher room on the third floor");
        bar3 = new Room("In the bar of the third floor");
        studyRoom3 = new Room("In the study room of the third floor");
        boss3 = new Room("In the last boss room");

        // initialise room exits first floor

        outside.setExits("south", hall1);

        hall1.setExits("north",outside);
        hall1.setExits("west",janitorRoom1);
        hall1.setExits("south",lobby1);

        janitorRoom1.setExits("east",hall1);
        janitorRoom1.setExits("down",janitorbasement1);
        janitorbasement1.setExits("up",janitorRoom1);
        lobby1.setExits("north",lobby1);
        lobby1.setExits("west",boss1);

        boss1.setExits("east",lobby1);
        boss1.setExits("west",pcRoom2);

        // initialize room exits for the second floor


        pcRoom2.setExits("north",puzzleRoom2);
        pcRoom2.setExits("east",boss1);
        pcRoom2.setExits("south",lobby2);
        pcRoom2.setExits("west",hiddenRoom2);

        hiddenRoom2.setExits("east",pcRoom2);

        puzzleRoom2.setExits("south",pcRoom2);

        lobby2.setExits("north", pcRoom2);
        lobby2.setExits("south", allyRoom2);
        lobby2.setExits("east",cafetariat2);

        allyRoom2.setExits("north", lobby2);

        cafetariat2.setExits("west",lobby1);
        cafetariat2.setExits("south",boss2);

        boss2.setExits("north",cafetariat2);
        boss2.setExits("east",entry3);


        // initialize room exits for the third floor

        entry3.setExits("west", boss2);
        entry3.setExits("south", reception3);

        reception3.setExits("north",entry3);
        reception3.setExits("south",teacherRoom3);
        reception3.setExits("west",cafetariat3);

        teacherRoom3.setExits("north",reception3);

        cafetariat3.setExits("east",reception3);
        cafetariat3.setExits("south",bar3);

        bar3.setExits("north",cafetariat3);
        bar3.setExits("east",studyRoom3);

        studyRoom3.setExits("west",bar3);
        studyRoom3.setExits("north", boss3);

        boss3.setExits("south",studyRoom3);

        player.setCurrentRoom(outside);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul "+player.getName()+"!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '"+CommandWord.HELP.toString()+"' if you need help.");
        System.out.println();
        printLocationInfo();
    }
    private void printLocationInfo() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {

            System.out.println("I don't know what you mean "+player.getName()+"....");
            return false;
        }
        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case BACK -> back(command);
            case LOOK -> look();
            case TAKE -> take(command);
            case DROP -> drop(command);
            case INVENTORY -> inventory();
            case INFO -> infoPlayer();
            case EAT -> eat(command);
            case UNKNOWN -> System.out.println("I don't know what you mean");
            case QUIT -> wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void inventory() {

        System.out.println(player.getItems().size()>0?player.getInventory():"Your inventory is empty");
    }
    private void infoPlayer() {
        System.out.println(player.infoPlayer());
    }
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where ?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {

            player.addItinirary(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
        }
    }
    /**
     * To look around in a room after having entered it
     *
     * */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    private Item getItemFromString(String itemName, List<Item> listItem){

        Item item=null;
        if(listItem.size()!=0) {
            for (Item i: listItem) {
                if(i.getName().equals(itemName)){
                    item=i;
                }
            }
        }
        return item;
    }

    private void take(Command command) {
        if(!command.hasSecondWord()){
            System.out.println("take what ? ");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = getItemFromString(itemName,player.getCurrentRoom().getItemList());
        if(!item.isCanBePickedUp()) {
            System.out.println("This item can't be picked up !");
            return;
        }
        if(player.take(item)) {
            System.out.println("Item: "+item.getName()+" taken");
            player.getCurrentRoom().removeItem(item);
        } else {
            System.out.println("This item doesn't exist or you exceeded you weight capacity");
        }
    }

    private void drop(Command command) {
        if(!command.hasSecondWord()){
                System.out.println("drop what ? ");
                return;
        }
        String itemName = command.getSecondWord();
        Item item = getItemFromString(itemName,player.getItems());
        if(player.drop(item)){
            System.out.println("Item: "+item.getName()+" dropped");
             player.getCurrentRoom().addItem(item);
        }else {
            System.out.println("You don't possess this item.");
        }
    }
    /**
    *
     * Send the player in the previous Room oocupied
     *
     * */
    private void back(Command command) {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go back where? Back to the future ?");
            return;
        }
        if(player.getItinerary().size()!=0) {
            player.setCurrentRoom(player.getItinerary().pop());
        }
        printLocationInfo();

    }
    /**
     * To eat something
     *
     * */
    //TODO: make it so that this method take an item as an argument like "bread" but more
    // importantly potions

    private void eat(Command command){
        if(!command.hasSecondWord()){
            System.out.println("eat what ? ");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = getItemFromString(itemName,player.getItems());
        if(player.eat(item)){
            System.out.println("Item: "+item.getName()+" eaten");
            player.removeInventory(item);
        }else {
            System.out.println("You don't possess this item.");
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
