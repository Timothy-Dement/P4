// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) Driver.java
package P4;

import java.io.*;

public class Driver {

    public static void main(String[] args) throws IOException {

        Utility.initialize_valid_employees();

        Utility.global_inventory.initialize_from_file();

        Utility.initialize_register_from_file();

        LoginWindow login_window = new LoginWindow();

    }
}
