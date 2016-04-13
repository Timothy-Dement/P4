// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) Category.java
package P4;

public class Category {

    private String category_name;

    private Item[] item_array;

    public Category() {
        this.category_name = "EMPTY";
        this.item_array = new Item[5];
        this.item_array[0] = new Item();
        this.item_array[1] = new Item();
        this.item_array[2] = new Item();
        this.item_array[3] = new Item();
        this.item_array[4] = new Item();
    }

    public Category(String category_name, Item[] item_array) {
        this.category_name = category_name;
        this.item_array = item_array;
    }

    public String get_category_name() {
        return this.category_name;
    }

    public Item[] get_item_array() {
        return this.item_array;
    }

    public void set_category_name(String category_name) {
        this.category_name = category_name;
    }

    public void set_item_array(Item[] item_array) {
        this.item_array = item_array;
    }
}
