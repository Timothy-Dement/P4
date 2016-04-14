// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) MainWindow.java
package P4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel main_panel,
            info_panel,
            full_sale_panel,
            full_return_panel,
            full_order_panel,
            sale_button_panel,
            return_button_panel,
            order_button_panel;
    
    private TransactionPanel sale_panel,
            return_panel,
            order_panel;
    
    private JTabbedPane transaction_panes;

    private JButton signout_button;

    private SignoutButtonHandler signout_button_handler;
    
    private ClearSaleButtonHandler clear_sale_button_handler;
    
    private ClearReturnButtonHandler clear_return_button_handler;
    
    private ClearOrderButtonHandler clear_order_button_handler;
    
    private ReviewSaleButtonHandler review_sale_button_handler;
    
    private ReviewReturnButtonHandler review_return_button_handler;
    
    private ReviewOrderButtonHandler review_order_button_handler;

    private JLabel employee_name_label,
            available_funds_label;

    private JButton clear_sale_button,
            review_sale_button,
            clear_return_button,
            review_return_button,
            clear_order_button,
            review_order_button;

    public MainWindow(String active_employee_name) {

        info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.X_AXIS));
        info_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        signout_button = new JButton("SIGN OUT");
        signout_button.addActionListener(new SignoutButtonHandler());

        employee_name_label = new JLabel(active_employee_name);

        if (Utility.global_funds_in_cents < 0) {
            available_funds_label = new JLabel("<html><font color = #ff0000><b>-" + Utility.convert_cents_for_display(Utility.global_funds_in_cents * -1) + "</b></font>");
        } else {
            available_funds_label = new JLabel("<html><font color = #00ff00><b>" + Utility.convert_cents_for_display(Utility.global_funds_in_cents) + "</b></font>");
        }
        
        available_funds_label.setHorizontalAlignment(JLabel.RIGHT);
        
        info_panel.add(signout_button);
        info_panel.add(Box.createRigidArea(new Dimension(10, 0)));
        info_panel.add(employee_name_label);
        info_panel.add(available_funds_label);
        
        sale_panel = new TransactionPanel("SALE", Utility.global_inventory);
        
        sale_button_panel = new JPanel();
        sale_button_panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        sale_button_panel.setBackground(Utility.DARK_SLATE_BLUE);
        
        clear_sale_button = new JButton("CLEAR SALE");
        clear_sale_button_handler = new ClearSaleButtonHandler();
        clear_sale_button.addActionListener(clear_sale_button_handler);
        
        review_sale_button = new JButton("REVIEW SALE");
        review_sale_button_handler = new ReviewSaleButtonHandler();
        review_sale_button.addActionListener(review_sale_button_handler);
        
        sale_button_panel.add(clear_sale_button);
        sale_button_panel.add(review_sale_button);
        
        full_sale_panel = new JPanel();
        full_sale_panel.setLayout(new BoxLayout(full_sale_panel, BoxLayout.Y_AXIS));
        full_sale_panel.add(sale_panel.get_main_transaction_panel());
        full_sale_panel.add(sale_button_panel);
        
        return_panel = new TransactionPanel("RETURN", Utility.global_inventory);
        
        return_button_panel = new JPanel();
        return_button_panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return_button_panel.setBackground(Utility.DARK_SLATE_BLUE);
        
        clear_return_button = new JButton("CLEAR RETURN");
        clear_return_button_handler = new ClearReturnButtonHandler();
        clear_return_button.addActionListener(clear_return_button_handler);
        
        review_return_button = new JButton("REVIEW RETURN");
        review_return_button_handler = new ReviewReturnButtonHandler();
        review_return_button.addActionListener(review_return_button_handler);
        
        return_button_panel.add(clear_return_button);
        return_button_panel.add(review_return_button);
        
        full_return_panel = new JPanel();
        full_return_panel.setLayout(new BoxLayout(full_return_panel, BoxLayout.Y_AXIS));
        full_return_panel.add(return_panel.get_main_transaction_panel());
        full_return_panel.add(return_button_panel);
        
        order_panel = new TransactionPanel("ORDER", Utility.global_inventory);
        
        order_button_panel = new JPanel();
        order_button_panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        order_button_panel.setBackground(Utility.DARK_SLATE_BLUE);
        
        clear_order_button = new JButton("CLEAR ORDER");
        clear_order_button_handler = new ClearOrderButtonHandler();
        clear_order_button.addActionListener(clear_order_button_handler);
        
        review_order_button = new JButton("REVIEW ORDER");
        review_order_button_handler = new ReviewOrderButtonHandler();
        review_order_button.addActionListener(review_order_button_handler);
        
        order_button_panel.add(clear_order_button);
        order_button_panel.add(review_order_button);
        
        full_order_panel = new JPanel();
        full_order_panel.setLayout(new BoxLayout(full_order_panel, BoxLayout.Y_AXIS));
        full_order_panel.add(order_panel.get_main_transaction_panel());
        full_order_panel.add(order_button_panel);
        
        transaction_panes = new JTabbedPane();
        
        transaction_panes.addTab("SALE", full_sale_panel);
        transaction_panes.addTab("RETURN", full_return_panel);
        transaction_panes.addTab("ORDER", full_order_panel);
        
        main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
        
        main_panel.add(info_panel);
        main_panel.add(transaction_panes);
        
        this.getContentPane().add(main_panel);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public class SignoutButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginWindow();
        }
    }
    
    public class ClearSaleButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    sale_panel.get_transaction_spinners()[category_index][item_index].setValue(0);
                }
            }
        }
    }
    
    public class ClearReturnButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    return_panel.get_transaction_spinners()[category_index][item_index].setValue(0);
                }
            }
        }
    }
    
    public class ClearOrderButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    order_panel.get_transaction_spinners()[category_index][item_index].setValue(0);
                }
            }
        }
    }
    
    public class ReviewSaleButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            JDialog review_sale_dialog = new JDialog();
            
            // TRY WRITING A METHOD TO RETURN THE PARENT FRAME, THEN DISPOSE OF THIS ONE AND CREATE A NEW ONE!
            
            review_sale_dialog.pack();
            review_sale_dialog.setResizable(false);
            review_sale_dialog.setLocationRelativeTo(null);
            review_sale_dialog.setModal(true);
        }
    }
    
    public class ReviewReturnButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
    public class ReviewOrderButtonHandler implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
