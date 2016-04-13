// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) TransactionPanel.java
package P4;

import java.awt.*;
import javax.swing.*;

public class TransactionPanel extends JPanel {
    
    private String transaction_name;
    
    private Inventory inventory;
    
    private JPanel main_panel;
    
    private CategoryPanel
            category_panel_one,
            category_panel_two,
            category_panel_three,
            category_panel_four,
            category_panel_five;
    
    public TransactionPanel(String transaction_name, Inventory inventory) {
        
        this.transaction_name = transaction_name;
        
        this.inventory = inventory;
        
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(1,5,1,1));
        main_panel.setBackground(Color.WHITE);
        
        category_panel_one = new CategoryPanel(this.transaction_name, this.inventory.get_category_array()[0]);
        category_panel_two = new CategoryPanel(this.transaction_name, this.inventory.get_category_array()[1]);
        category_panel_three = new CategoryPanel(this.transaction_name, this.inventory.get_category_array()[2]);
        category_panel_four = new CategoryPanel(this.transaction_name, this.inventory.get_category_array()[3]);
        category_panel_five = new CategoryPanel(this.transaction_name, this.inventory.get_category_array()[4]);
        
        main_panel.add(category_panel_one.get_main_category_panel());
        main_panel.add(category_panel_two.get_main_category_panel());
        main_panel.add(category_panel_three.get_main_category_panel());
        main_panel.add(category_panel_four.get_main_category_panel());
        main_panel.add(category_panel_five.get_main_category_panel());
    }
    
    public JPanel get_main_transaction_panel() {
        return this.main_panel;
    }
    
    // JSPINNER STUFF
    
}