import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Player {

    private String name;
    private int health=150;
    private int mana=50;
    private int weightCapacity=40000;
    private List<Item> items = new ArrayList<>();
    private int weigthCarried=0;
    private Room currentRoom;
    private Deque<Room> itinerary = new ArrayDeque<>();

    public Player(String name) {
        this.name = name;
    }
    public boolean take(Item item) {
        if(item!=null && item.getWeight()+weigthCarried<=weightCapacity) {
            items.add(item);
            setWeigthCarried(getWeigthCarried()+item.getWeight());
            return true;
        } else {
            return false;
        }
    }
    public boolean eat(Item item) {
        if(item.isCanBeEaten()) {
            if(item.getHealthProvided()!=0) {
                setHealth(item.getHealthProvided());
            }
            setWeigthCarried(getWeigthCarried() - item.getWeight());
            items.removeIf(i -> i.getId()==item.getId());
            return true;
        }
        return false;
    }
    public void addItinirary(Room currentRoom) {
        itinerary.push(currentRoom);
    }
    //TODO: Finit l'implementation de take et drop dans player et dans game ainsi que mettre a jour les commandes
    // dans command et dans le parser.
    // le player ne peux prendre un objet que si il n' pas depasser sa capacité de transport
    public boolean drop(Item item){
        for (Item i: items) {
            if(i.getId()==item.getId()) {
                setWeigthCarried(getWeigthCarried()-i.getWeight());
                items.remove(i);
                return true;
            }
        }
        return false;
    }
    public String infoPlayer() {
        return "\n Player name: "+getName()
                +"\n Health: "+getHealth()
                +"\n Mana: "+getMana()
                +"\n Weight carried: "+getWeigthCarried()
                +"\n Weight limit: "+getWeightCapacity()
                +"\n Items in your inventory: "+getInventory();
    }
    public void removeInventory(Item item){
        setWeigthCarried(getWeigthCarried()-item.getWeight());
        items.removeIf(i-> i.getId()==item.getId());
    }
    public String getInventory() {
        String inventoryString="";
        int weightInventory = 0;
        for (Item item: items) {
            inventoryString+=item.getName()+", ";
            weightInventory+= item.getWeight();
        }
        return inventoryString+" \n Total inventory weight:"+weightInventory;
    }
    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    public String getName() {
        return name;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getWeigthCarried() {
        return weigthCarried;
    }

    public void setWeigthCarried(int weigthCarried) {
        this.weigthCarried = weigthCarried;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Deque<Room> getItinerary() {
        return itinerary;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = this.health + health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = this.mana + mana;
    }
}
