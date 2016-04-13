// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) Item.java
package P4;

public class Item {

    private String item_name;
    private int item_price_in_cents;
    private int item_cost_in_cents;
    private int item_quantity;

    public Item() {
        this.item_name = "EMPTY";
        this.item_price_in_cents = 0;
        this.item_cost_in_cents = 0;
        this.item_quantity = 0;
    }

    public Item(String item_name, int item_price_in_cents, int item_cost_in_cents, int item_quantity) {
        this.item_name = item_name;
        this.item_price_in_cents = item_price_in_cents;
        this.item_cost_in_cents = item_cost_in_cents;
        this.item_quantity = item_quantity;
    }

    public String get_item_name() {
        return this.item_name;
    }

    public int get_item_price_in_cents() {
        return this.item_price_in_cents;
    }

    public String get_item_price_for_display() {
        return Utility.convert_cents_for_display(this.item_price_in_cents);
    }

    public int get_item_cost_in_cents() {
        return this.item_cost_in_cents;
    }

    public String get_item_cost_for_display() {
        return Utility.convert_cents_for_display(this.item_cost_in_cents);
    }

    public int get_item_quantity() {
        return this.item_quantity;
    }

    public void set_item_name(String item_name) {
        this.item_name = item_name;
    }

    public void set_item_price_in_cents(int item_price_in_cents) {
        this.item_price_in_cents = item_price_in_cents;
    }

    public void set_item_cost_in_cents(int item_cost_in_cents) {
        this.item_cost_in_cents = item_cost_in_cents;
    }

    public void set_item_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public void increment_item_quantity(int increment_amount) {
        this.item_quantity += increment_amount;
    }

    public void decrement_item_quantity(int decrement_amount) {
        this.item_quantity -= decrement_amount;
    }
}
