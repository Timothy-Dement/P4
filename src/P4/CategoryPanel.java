// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) CategoryPanel.java
package P4;

import java.awt.*;
import javax.swing.*;

public class CategoryPanel extends JPanel {

    private String transaction_name;

    private Category category;

    private JPanel main_panel,
            title_panel,
            item_list_panel,
            item_panel_one,
            item_panel_two,
            item_panel_three,
            item_panel_four,
            item_panel_five;

    private JLabel category_label,
            name_label_one,
            name_label_two,
            name_label_three,
            name_label_four,
            name_label_five,
            price_cost_label_one,
            price_cost_label_two,
            price_cost_label_three,
            price_cost_label_four,
            price_cost_label_five,
            quantity_label_one,
            quantity_label_two,
            quantity_label_three,
            quantity_label_four,
            quantity_label_five;
    
    private JSpinner spinner_one,
            spinner_two,
            spinner_three,
            spinner_four,
            spinner_five;

    public CategoryPanel(String transaction_name, Category category) {

        this.transaction_name = transaction_name;

        this.category = category;
        
        title_panel = new JPanel();
        title_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        title_panel.setBackground(Utility.DARK_SLATE_BLUE);
        
        category_label = new JLabel("<html><font color = #ffffff><b>" + this.category.get_category_name() + "</b></font>");
        
        title_panel.add(category_label);
        
        item_list_panel = new JPanel();
        item_list_panel.setLayout(new GridLayout(5,1,10,10));
        item_list_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        item_list_panel.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        item_panel_one = new JPanel();
        item_panel_one.setLayout(new BoxLayout(item_panel_one, BoxLayout.Y_AXIS));
        item_panel_one.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), BorderFactory.createEmptyBorder(10,10,10,10)));
        item_panel_one.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        item_panel_two = new JPanel();
        item_panel_two.setLayout(new BoxLayout(item_panel_two, BoxLayout.Y_AXIS));
        item_panel_two.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), BorderFactory.createEmptyBorder(10,10,10,10)));
        item_panel_two.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        item_panel_three = new JPanel();
        item_panel_three.setLayout(new BoxLayout(item_panel_three, BoxLayout.Y_AXIS));
        item_panel_three.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), BorderFactory.createEmptyBorder(10,10,10,10)));
        item_panel_three.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        item_panel_four = new JPanel();
        item_panel_four.setLayout(new BoxLayout(item_panel_four, BoxLayout.Y_AXIS));
        item_panel_four.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), BorderFactory.createEmptyBorder(10,10,10,10)));
        item_panel_four.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        item_panel_five = new JPanel();
        item_panel_five.setLayout(new BoxLayout(item_panel_five, BoxLayout.Y_AXIS));
        item_panel_five.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1), BorderFactory.createEmptyBorder(10,10,10,10)));
        item_panel_five.setBackground(Utility.LIGHT_STEEL_BLUE);
        
        name_label_one = new JLabel("<html><font color = #ffffff><b>NAME </b></font>" + this.category.get_item_array()[0].get_item_name());
        name_label_two = new JLabel("<html><font color = #ffffff><b>NAME </b></font>" + this.category.get_item_array()[1].get_item_name());
        name_label_three = new JLabel("<html><font color = #ffffff><b>NAME </b></font>" + this.category.get_item_array()[2].get_item_name());
        name_label_four = new JLabel("<html><font color = #ffffff><b>NAME </b></font>" + this.category.get_item_array()[3].get_item_name());
        name_label_five = new JLabel("<html><font color = #ffffff><b>NAME </b></font>" + this.category.get_item_array()[4].get_item_name());
        
        if (transaction_name.equals("ORDER")) {
            
            price_cost_label_one = new JLabel("<html><font color = #ffffff><b>COST </b></font>" + this.category.get_item_array()[0].get_item_cost_for_display());
            price_cost_label_two = new JLabel("<html><font color = #ffffff><b>COST </b></font>" + this.category.get_item_array()[1].get_item_cost_for_display());
            price_cost_label_three = new JLabel("<html><font color = #ffffff><b>COST </b></font>" + this.category.get_item_array()[2].get_item_cost_for_display());
            price_cost_label_four = new JLabel("<html><font color = #ffffff><b>COST </b></font>" + this.category.get_item_array()[3].get_item_cost_for_display());
            price_cost_label_five = new JLabel("<html><font color = #ffffff><b>COST </b></font>" + this.category.get_item_array()[4].get_item_cost_for_display());
            
        } else {
            
            price_cost_label_one = new JLabel("<html><font color = #ffffff><b>PRICE </b></font>" + this.category.get_item_array()[0].get_item_price_for_display());
            price_cost_label_two = new JLabel("<html><font color = #ffffff><b>PRICE </b></font>" + this.category.get_item_array()[1].get_item_price_for_display());
            price_cost_label_three = new JLabel("<html><font color = #ffffff><b>PRICE </b></font>" + this.category.get_item_array()[2].get_item_price_for_display());
            price_cost_label_four = new JLabel("<html><font color = #ffffff><b>PRICE </b></font>" + this.category.get_item_array()[3].get_item_price_for_display());
            price_cost_label_five = new JLabel("<html><font color = #ffffff><b>PRICE </b></font>" + this.category.get_item_array()[4].get_item_price_for_display());
        }
        
        quantity_label_one = new JLabel("<html><font color = #ffffff><b>STOCK </b></font>" + this.category.get_item_array()[0].get_item_quantity());
        quantity_label_two = new JLabel("<html><font color = #ffffff><b>STOCK </b></font>" + this.category.get_item_array()[1].get_item_quantity());
        quantity_label_three = new JLabel("<html><font color = #ffffff><b>STOCK </b></font>" + this.category.get_item_array()[2].get_item_quantity());
        quantity_label_four = new JLabel("<html><font color = #ffffff><b>STOCK </b></font>" + this.category.get_item_array()[3].get_item_quantity());
        quantity_label_five = new JLabel("<html><font color = #ffffff><b>STOCK </b></font>" + this.category.get_item_array()[4].get_item_quantity());
        
        if (transaction_name.equals("SALE")) {
            
            spinner_one = new JSpinner(new SpinnerNumberModel(0, 0, this.category.get_item_array()[0].get_item_quantity(), 1));
            spinner_two = new JSpinner(new SpinnerNumberModel(0, 0, this.category.get_item_array()[1].get_item_quantity(), 1));
            spinner_three = new JSpinner(new SpinnerNumberModel(0, 0, this.category.get_item_array()[2].get_item_quantity(), 1));
            spinner_four = new JSpinner(new SpinnerNumberModel(0, 0, this.category.get_item_array()[3].get_item_quantity(), 1));
            spinner_five = new JSpinner(new SpinnerNumberModel(0, 0, this.category.get_item_array()[4].get_item_quantity(), 1));
            
        } else {
            
            spinner_one = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            spinner_two = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            spinner_three = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            spinner_four = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            spinner_five = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            
        }
        
        name_label_one.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        price_cost_label_one.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        quantity_label_one.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        spinner_one.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        name_label_two.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        price_cost_label_two.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        quantity_label_two.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        spinner_two.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        name_label_three.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        price_cost_label_three.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        quantity_label_three.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        spinner_three.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        name_label_four.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        price_cost_label_four.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        quantity_label_four.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        spinner_four.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        name_label_five.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        price_cost_label_five.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        quantity_label_five.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        spinner_five.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        
        item_panel_one.add(name_label_one);
        item_panel_one.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_one.add(price_cost_label_one);
        item_panel_one.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_one.add(quantity_label_one);
        item_panel_one.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_one.add(spinner_one);
        
        item_panel_two.add(name_label_two);
        item_panel_two.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_two.add(price_cost_label_two);
        item_panel_two.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_two.add(quantity_label_two);
        item_panel_two.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_two.add(spinner_two);
        
        item_panel_three.add(name_label_three);
        item_panel_three.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_three.add(price_cost_label_three);
        item_panel_three.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_three.add(quantity_label_three);
        item_panel_three.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_three.add(spinner_three);
        
        item_panel_four.add(name_label_four);
        item_panel_four.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_four.add(price_cost_label_four);
        item_panel_four.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_four.add(quantity_label_four);
        item_panel_four.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_four.add(spinner_four);
        
        item_panel_five.add(name_label_five);
        item_panel_five.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_five.add(price_cost_label_five);
        item_panel_five.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_five.add(quantity_label_five);
        item_panel_five.add(Box.createRigidArea(new Dimension(0,5)));
        item_panel_five.add(spinner_five);
        
        item_list_panel.add(item_panel_one);
        item_list_panel.add(item_panel_two);
        item_list_panel.add(item_panel_three);
        item_list_panel.add(item_panel_four);
        item_list_panel.add(item_panel_five);
        
        main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        
        main_panel.add(title_panel);
        main_panel.add(item_list_panel);
    }
    
    public JPanel get_main_category_panel() {
        return this.main_panel;
    }
    
    public int[] get_category_spinner_values() {
        
        int[] category_spinner_values = new int[5];
        
        category_spinner_values[0] = (int) this.spinner_one.getValue();
        category_spinner_values[1] = (int) this.spinner_two.getValue();
        category_spinner_values[2] = (int) this.spinner_three.getValue();
        category_spinner_values[3] = (int) this.spinner_four.getValue();
        category_spinner_values[4] = (int) this.spinner_five.getValue();
        
        return category_spinner_values;
    }
    
    public JSpinner[] get_category_spinners() {
        
        JSpinner[] category_spinners = new JSpinner[5];
        
        category_spinners[0] = this.spinner_one;
        category_spinners[1] = this.spinner_two;
        category_spinners[2] = this.spinner_three;
        category_spinners[3] = this.spinner_four;
        category_spinners[4] = this.spinner_five;
        
        return category_spinners;
    }
}
