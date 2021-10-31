import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room upExit;
    private Room downExit;
    private int floor;
    private List<Item> itemList = new ArrayList<>();

    /**
     * Create a room described "description "Initially, it
     * has no exits. "description" is something like "a
     * kitchen" or "an open courtyard".
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param //north The north exit.
     * @param //east The east east.
     * @param //south The south exit.
     * @param //west The west exit.
     */

    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    /**
     * Return a description of the room’s exits,

     * for example, "Exits: north west".

     * @return A description of the available exits.

     */

    public String getExitString() {
        String exitText = "Exits: " ;

        Set<String> keys = exits.keySet();
        //return an array of the first object in the object exits
        for (String exit: keys) {
            exitText += " " + exit;
        }

        return exitText;
    }
    /**
     * Return the room that is reached if we go from this

     * room in direction "direction "If there is no room in

     * that direction, return null.

     */
    public Room getExit(String direction) {
       return exits.get(direction);
    }
    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are : "+ getDescription() +"\n"+ getExitString() + (getItem()!=""? "\n"+ "Item in the room : "+getItem():"");
    }
    public String getItem() {
        String itemInRoom="";
        for (Item item: itemList) {
            itemInRoom+=item.getItemDescription()+", ";
        }
        return itemInRoom;
    }
    public void removeItem(Item item) {
        itemList.removeIf(i -> i.getName().equals(item.getName()));
    }
    public void addItem(Item item) {
        itemList.add(item);
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
