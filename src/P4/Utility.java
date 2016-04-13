// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) Utility.java
package P4;

import java.io.*;
import java.awt.*;

public class Utility {

    public static final Color LIGHT_STEEL_BLUE = new Color(176, 196, 222);
    public static final Color DARK_SLATE_BLUE = new Color(72, 61, 139);

    public static Employee[] global_employee_array = new Employee[4];

    public static Inventory global_inventory = new Inventory();

    public static int global_funds_in_cents = 0;

    public static String convert_cents_for_display(int cents) {

        String cents_string;
        String display_string;

        cents_string = new Integer(cents).toString();

        if (cents < 10) {

            display_string = "0.0" + cents_string;

        } else if (cents < 100) {

            display_string = "0." + cents_string;

        } else {

            display_string = "$"
                    + cents_string.substring(0, cents_string.length() - 2)
                    + "."
                    + cents_string.substring(cents_string.length() - 2);
        }

        return display_string;
    }

    public static void initialize_valid_employees() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(new File("users.txt")));

        for (int employee_index = 0; employee_index < 4; employee_index++) {

            String[] scanned_user_values = in.readLine().split(":", 3);

            global_employee_array[employee_index] = new Employee(scanned_user_values[0], scanned_user_values[1], scanned_user_values[2]);
        }
        in.close();
    }

    public static void initialize_register_from_file() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(new File("funds.txt")));

        global_funds_in_cents = new Integer(in.readLine());

        in.close();
    }

    public static void save_register_to_file() throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter(new File("funds.txt")));

        out.write(global_funds_in_cents);

        out.close();
    }
}
