// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) Inventory.java
package P4;

import java.io.*;

public class Inventory {

    private Category[] category_array;

    public Inventory() {
        this.category_array = new Category[5];
        this.category_array[0] = new Category();
        this.category_array[1] = new Category();
        this.category_array[2] = new Category();
        this.category_array[3] = new Category();
        this.category_array[4] = new Category();
    }

    public Inventory(Category[] category_array) {
        this.category_array = category_array;
    }

    public Category[] get_category_array() {
        return this.category_array;
    }

    public void set_category_array(Category[] category_array) {
        this.category_array = category_array;
    }

    public void initialize_from_file() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(new File("inventory.txt")));

        for (int category_index = 0; category_index < 5; category_index++) {

            this.category_array[category_index].set_category_name(in.readLine());

            for (int item_index = 0; item_index < 5; item_index++) {

                this.category_array[category_index].get_item_array()[item_index].set_item_name(in.readLine());

                this.category_array[category_index].get_item_array()[item_index].set_item_price_in_cents(Integer.parseInt(in.readLine()));

                this.category_array[category_index].get_item_array()[item_index].set_item_cost_in_cents(Integer.parseInt(in.readLine()));

                this.category_array[category_index].get_item_array()[item_index].set_item_quantity(Integer.parseInt(in.readLine()));
            }
        }

        in.close();
    }

    public void save_to_file() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter(new File("inventory.txt")));

        for (int category_index = 0; category_index < 5; category_index++) {

            out.write(this.category_array[category_index].get_category_name() + "\n");

            for (int item_index = 0; item_index < 5; item_index++) {

                out.write(this.category_array[category_index].get_item_array()[item_index].get_item_name() + "\n");

                out.write(this.category_array[category_index].get_item_array()[item_index].get_item_price_in_cents() + "\n");

                out.write(this.category_array[category_index].get_item_array()[item_index].get_item_cost_in_cents() + "\n");

                out.write(this.category_array[category_index].get_item_array()[item_index].get_item_quantity() + "\n");
            }
        }

        out.close();
    }

    public int sell_items(int[][] items_to_sell) {

        int total_sale_amount = 0;

        for (int category_index = 0; category_index < 5; category_index++) {

            for (int item_index = 0; item_index < 5; item_index++) {

                if (items_to_sell[category_index][item_index] > 0) {

                    this.category_array[category_index].get_item_array()[item_index].decrement_item_quantity(items_to_sell[category_index][item_index]);

                    total_sale_amount += this.category_array[category_index].get_item_array()[item_index].get_item_price_in_cents();
                }
            }
        }

        return total_sale_amount;
    }

    public int return_items(int[][] items_to_return) {

        int total_return_amount = 0;

        for (int category_index = 0; category_index < 5; category_index++) {

            for (int item_index = 0; item_index < 5; item_index++) {

                if (items_to_return[category_index][item_index] > 0) {

                    this.category_array[category_index].get_item_array()[item_index].increment_item_quantity(items_to_return[category_index][item_index]);

                    total_return_amount += this.category_array[category_index].get_item_array()[item_index].get_item_price_in_cents();
                }
            }
        }

        return total_return_amount;
    }

    public int order_items(int[][] items_to_order) {

        int total_order_amount = 0;

        for (int category_index = 0; category_index < 5; category_index++) {

            for (int item_index = 0; item_index < 5; item_index++) {

                if (items_to_order[category_index][item_index] > 0) {

                    this.category_array[category_index].get_item_array()[item_index].increment_item_quantity(items_to_order[category_index][item_index]);

                    total_order_amount += this.category_array[category_index].get_item_array()[item_index].get_item_cost_in_cents();
                }
            }
        }

        return total_order_amount;
    }
}
