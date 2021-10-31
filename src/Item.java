public class Item {
    private static int idItem=0;
    private int id;
    private int weight;
    private String description;
    private String name;
    private int healthProvided;
    private boolean canBePickedUp;
    boolean canBeEaten =false;
    public Item(int weight, String name, String description, boolean canBePickedUp) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.canBePickedUp = canBePickedUp;
        this.id = idItem;
        idItem+=1;
    }
    public Item(int weight, String name, String description, boolean canBePickedUp, int healthProvided) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.canBePickedUp = canBePickedUp;
        this.healthProvided = healthProvided;
        this.id = idItem;
        idItem+=1;
    }

    public String getItemDescription() {
        return description+" ("+getName()+")";
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public boolean isCanBePickedUp() {
        return canBePickedUp;
    }

    public int getHealthProvided() {
        return healthProvided;
    }

    public boolean isCanBeEaten() {
        return canBeEaten;
    }

    public void setCanBeEaten(boolean canBeEaten) {
        this.canBeEaten = canBeEaten;
    }

    public static int getIdItem() {
        return idItem;
    }

    public int getId() {
        return id;
    }
}
