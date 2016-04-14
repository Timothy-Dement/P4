// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) MainWindow.java
package P4;

import java.io.*;
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
    
    String active_employee_name;
    
    public MainWindow(String active_employee_name) {
        
        this.active_employee_name = active_employee_name;
        
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
        sale_button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
        return_button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
        order_button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent we) {
                
                JDialog confirm_close_dialog = new JDialog();
                
                JPanel main_panel = new JPanel();
                main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
                main_panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                main_panel.setBackground(Color.WHITE);
                
                JPanel message_panel = new JPanel();
                message_panel.setLayout(new GridLayout(3, 1, 10, 10));
                message_panel.setBackground(Color.WHITE);
                
                JPanel button_panel = new JPanel();
                button_panel.setLayout(new BoxLayout(button_panel, BoxLayout.X_AXIS));
                button_panel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
                button_panel.setBackground(Color.WHITE);
                
                JLabel warning_label = new JLabel("<html><font color = #ff0000><b>WARNING</font></b>");
                JLabel quit_message_label = new JLabel("<html><font color = #ff0000>If you quit without saving, the results of any transactions completed during this session will be lost.</font></b>");
                JLabel save_message_label = new JLabel("<html><font color = #ff0000>Would you like to save before quitting?</font></b>");
                
                message_panel.add(warning_label);
                message_panel.add(quit_message_label);
                message_panel.add(save_message_label);
                
                JButton quit_button = new JButton("QUIT");
                JButton save_and_quit_button = new JButton("SAVE AND QUIT");
                
                quit_button.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        System.exit(0);
                    }
                });
                
                save_and_quit_button.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        try {
                            
                            Utility.global_inventory.save_to_file();
                            Utility.save_register_to_file();
                            System.exit(0);
                            
                        } catch (IOException a) {
                        }
                    }
                });
                
                button_panel.add(Box.createHorizontalGlue());
                button_panel.add(quit_button);
                button_panel.add(save_and_quit_button);
                
                main_panel.add(message_panel);
                main_panel.add(button_panel);
                
                confirm_close_dialog.getContentPane().add(main_panel);
                
                confirm_close_dialog.pack();
                confirm_close_dialog.setResizable(false);
                confirm_close_dialog.setLocationRelativeTo(null);
                confirm_close_dialog.setModal(true);
                confirm_close_dialog.setVisible(true);
                confirm_close_dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });
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
        
        private JDialog review_sale_dialog;
        
        private JPanel main_panel,
                title_panel,
                item_panel,
                total_panel,
                button_panel;
        
        private JButton cancel_sale_button,
                confirm_sale_button;
        
        private JLabel title_label,
                subtotal_name_label,
                tax_name_label,
                total_name_label,
                subtotal_amount_label,
                tax_amount_label,
                total_amount_label;
        
        private CancelSaleButtonHandler cancel_sale_button_handler;
        
        private ConfirmSaleButtonHandler confirm_sale_button_handler;
        
        private int[][] items_to_be_sold;
        
        private int number_of_distinct_items,
                subtotal_in_cents,
                tax_in_cents,
                total_in_cents;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            items_to_be_sold = sale_panel.get_transaction_spinner_values();
            
            number_of_distinct_items = 0;
            
            subtotal_in_cents = 0;
            tax_in_cents = 0;
            total_in_cents = 0;
            
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    if (items_to_be_sold[category_index][item_index] > 0) {
                        number_of_distinct_items++;
                    }
                }
            }
            
            if (number_of_distinct_items > 0) {
                
                review_sale_dialog = new JDialog();
                
                title_panel = new JPanel();
                title_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                title_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                title_label = new JLabel("<html><font color = #ffffff><b>SALE ITEMS</b></font>");
                
                title_panel.add(title_label);
                
                item_panel = new JPanel();
                
                item_panel.setLayout(new GridLayout(number_of_distinct_items, 2, 5, 5));
                item_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                for (int category_index = 0; category_index < 5; category_index++) {
                    for (int item_index = 0; item_index < 5; item_index++) {
                        if (items_to_be_sold[category_index][item_index] > 0) {
                            
                            JLabel quantity_and_name_label = new JLabel("("
                                    + items_to_be_sold[category_index][item_index]
                                    + ") "
                                    + Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_name());
                            
                            JLabel price_label = new JLabel(Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_price_for_display()
                                    + " (each)");
                            
                            price_label.setHorizontalAlignment(JLabel.RIGHT);
                            
                            item_panel.add(quantity_and_name_label);
                            item_panel.add(price_label);
                            
                            subtotal_in_cents += items_to_be_sold[category_index][item_index]
                                    * Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_price_in_cents();
                        }
                    }
                }
                
                tax_in_cents = (int) (subtotal_in_cents * 0.05);
                
                total_in_cents = subtotal_in_cents + tax_in_cents;
                
                total_panel = new JPanel();
                total_panel.setLayout(new GridLayout(3, 2, 5, 5));
                total_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                total_panel.setBackground(Utility.LIGHT_STEEL_BLUE);
                
                subtotal_name_label = new JLabel("<html><b>SALE SUBTOTAL</b>");
                tax_name_label = new JLabel("<html><b>SALE TAX</b>");
                total_name_label = new JLabel("<html><b>SALE TOTAL</b>");
                
                subtotal_amount_label = new JLabel(Utility.convert_cents_for_display(subtotal_in_cents));
                subtotal_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                tax_amount_label = new JLabel(Utility.convert_cents_for_display(tax_in_cents));
                tax_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_amount_label = new JLabel(Utility.convert_cents_for_display(total_in_cents));
                total_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_panel.add(subtotal_name_label);
                total_panel.add(subtotal_amount_label);
                total_panel.add(tax_name_label);
                total_panel.add(tax_amount_label);
                total_panel.add(total_name_label);
                total_panel.add(total_amount_label);
                
                button_panel = new JPanel();
                button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                button_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                cancel_sale_button = new JButton("CANCEL SALE");
                cancel_sale_button_handler = new CancelSaleButtonHandler();
                cancel_sale_button.addActionListener(cancel_sale_button_handler);
                
                confirm_sale_button = new JButton("CONFIRM SALE");
                confirm_sale_button_handler = new ConfirmSaleButtonHandler();
                confirm_sale_button.addActionListener(confirm_sale_button_handler);
                
                button_panel.add(cancel_sale_button);
                button_panel.add(confirm_sale_button);
                
                main_panel = new JPanel();
                main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
                
                main_panel.add(title_panel);
                main_panel.add(item_panel);
                main_panel.add(total_panel);
                main_panel.add(button_panel);
                
                review_sale_dialog.getContentPane().add(main_panel);
                
                review_sale_dialog.pack();
                review_sale_dialog.setResizable(false);
                review_sale_dialog.setLocationRelativeTo(null);
                review_sale_dialog.setModal(true);
                review_sale_dialog.setVisible(true);
                review_sale_dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
        
        public class CancelSaleButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                review_sale_dialog.dispose();
            }
        }
        
        public class ConfirmSaleButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                review_sale_dialog.dispose();
                
                Utility.global_funds_in_cents += total_in_cents;
                
                Utility.global_inventory.sell_items(items_to_be_sold);
                
                new MainWindow(active_employee_name);
                
            }
        }
        
    }
    
    public class ReviewReturnButtonHandler implements ActionListener {
        
        private JDialog review_return_dialog;
        
        private JPanel main_panel,
                title_panel,
                item_panel,
                total_panel,
                button_panel;
        
        private JButton cancel_return_button,
                confirm_return_button;
        
        private JLabel title_label,
                subtotal_name_label,
                tax_name_label,
                total_name_label,
                subtotal_amount_label,
                tax_amount_label,
                total_amount_label;
        
        private CancelReturnButtonHandler cancel_return_button_handler;
        
        private ConfirmReturnButtonHandler confirm_return_button_handler;
        
        private int[][] items_to_be_returned;
        
        private int number_of_distinct_items,
                subtotal_in_cents,
                tax_in_cents,
                total_in_cents;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            items_to_be_returned = return_panel.get_transaction_spinner_values();
            
            number_of_distinct_items = 0;
            
            subtotal_in_cents = 0;
            tax_in_cents = 0;
            total_in_cents = 0;
            
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    if (items_to_be_returned[category_index][item_index] > 0) {
                        number_of_distinct_items++;
                    }
                }
            }
            
            if (number_of_distinct_items > 0) {
                
                review_return_dialog = new JDialog();
                
                title_panel = new JPanel();
                title_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                title_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                title_label = new JLabel("<html><font color = #ffffff><b>RETURN ITEMS</b></font>");
                
                title_panel.add(title_label);
                
                item_panel = new JPanel();
                
                item_panel.setLayout(new GridLayout(number_of_distinct_items, 2, 5, 5));
                item_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                for (int category_index = 0; category_index < 5; category_index++) {
                    for (int item_index = 0; item_index < 5; item_index++) {
                        if (items_to_be_returned[category_index][item_index] > 0) {
                            
                            JLabel quantity_and_name_label = new JLabel("("
                                    + items_to_be_returned[category_index][item_index]
                                    + ") "
                                    + Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_name());
                            
                            JLabel price_label = new JLabel(Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_price_for_display()
                                    + " (each)");
                            
                            price_label.setHorizontalAlignment(JLabel.RIGHT);
                            
                            item_panel.add(quantity_and_name_label);
                            item_panel.add(price_label);
                            
                            subtotal_in_cents += items_to_be_returned[category_index][item_index]
                                    * Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_price_in_cents();
                        }
                    }
                }
                
                tax_in_cents = (int) (subtotal_in_cents * 0.05);
                
                total_in_cents = subtotal_in_cents + tax_in_cents;
                
                total_panel = new JPanel();               
                total_panel.setLayout(new GridLayout(3,2,5,5));
                total_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                total_panel.setBackground(Utility.LIGHT_STEEL_BLUE);
                
                subtotal_name_label = new JLabel("<html><b>RETURN SUBTOTAL</b>");
                tax_name_label = new JLabel("<html><b>RETURN TAX</b>");
                total_name_label = new JLabel("<html><b>RETURN TOTAL</b>");
                
                subtotal_amount_label = new JLabel(Utility.convert_cents_for_display(subtotal_in_cents));
                subtotal_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                tax_amount_label = new JLabel(Utility.convert_cents_for_display(tax_in_cents));
                tax_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_amount_label = new JLabel(Utility.convert_cents_for_display(total_in_cents));
                total_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_panel.add(subtotal_name_label);
                total_panel.add(subtotal_amount_label);
                total_panel.add(tax_name_label);
                total_panel.add(tax_amount_label);
                total_panel.add(total_name_label);
                total_panel.add(total_amount_label);
                
                button_panel = new JPanel();
                button_panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                button_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                cancel_return_button = new JButton("CANCEL RETURN");
                cancel_return_button_handler = new CancelReturnButtonHandler();
                cancel_return_button.addActionListener(cancel_return_button_handler);
                
                confirm_return_button = new JButton("CONFIRM RETURN");
                confirm_return_button_handler = new ConfirmReturnButtonHandler();
                confirm_return_button.addActionListener(confirm_return_button_handler);
                
                button_panel.add(cancel_return_button);
                button_panel.add(confirm_return_button);
                
                main_panel = new JPanel();
                main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
                
                main_panel.add(title_panel);
                main_panel.add(item_panel);
                main_panel.add(total_panel);
                main_panel.add(button_panel);
                
                review_return_dialog.getContentPane().add(main_panel);
                
                review_return_dialog.pack();
                review_return_dialog.setResizable(false);
                review_return_dialog.setLocationRelativeTo(null);
                review_return_dialog.setModal(true);
                review_return_dialog.setVisible(true);
                review_return_dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
            }
        }
        
        public class CancelReturnButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                review_return_dialog.dispose();
            }
        }
        
        public class ConfirmReturnButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                review_return_dialog.dispose();
                
                Utility.global_funds_in_cents -= total_in_cents;
                
                Utility.global_inventory.return_items(items_to_be_returned);
                
                new MainWindow(active_employee_name);
            }
        }
    }
    
    public class ReviewOrderButtonHandler implements ActionListener {
        
        private JDialog review_order_dialog;
        
        private JPanel main_panel,
                title_panel,
                item_panel,
                total_panel,
                button_panel;
        
        private JButton cancel_order_button,
                confirm_order_button;
        
        private JLabel title_label,
                subtotal_name_label,
                tax_name_label,
                total_name_label,
                subtotal_amount_label,
                tax_amount_label,
                total_amount_label;
        
        private CancelOrderButtonHandler cancel_order_button_handler;
        
        private ConfirmOrderButtonHandler confirm_order_button_handler;
        
        private int[][] items_to_be_ordered;
        
        private int number_of_distinct_items,
                subtotal_in_cents,
                tax_in_cents,
                total_in_cents;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            items_to_be_ordered = order_panel.get_transaction_spinner_values();
            
            number_of_distinct_items = 0;
            
            subtotal_in_cents = 0;
            tax_in_cents = 0;
            total_in_cents = 0;
            
            for (int category_index = 0; category_index < 5; category_index++) {
                for (int item_index = 0; item_index < 5; item_index++) {
                    if (items_to_be_ordered[category_index][item_index] > 0) {
                        number_of_distinct_items++;
                    }
                }
            }
            
            if (number_of_distinct_items > 0) {
                
                review_order_dialog = new JDialog();
                
                title_panel = new JPanel();
                title_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                title_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                title_label = new JLabel("<html><font color = #ffffff><b>ORDER ITEMS</b></font>");
                
                title_panel.add(title_label);
                
                item_panel = new JPanel();
                
                item_panel.setLayout(new GridLayout(number_of_distinct_items,2,5,5));
                item_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
                for (int category_index = 0; category_index < 5; category_index++) {
                    for (int item_index = 0; item_index < 5; item_index++) {
                        if (items_to_be_ordered[category_index][item_index] > 0) {
                            
                            JLabel quantity_and_name_label = new JLabel("("
                                    + items_to_be_ordered[category_index][item_index]
                                    + ") "
                                    + Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_name());
                            
                            JLabel cost_label = new JLabel(Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_cost_for_display()
                                    + " (each)");
                            
                            cost_label.setHorizontalAlignment(JLabel.RIGHT);
                            
                            item_panel.add(quantity_and_name_label);
                            item_panel.add(cost_label);
                            
                            subtotal_in_cents += items_to_be_ordered[category_index][item_index]
                                    * Utility.global_inventory.get_category_array()[category_index].get_item_array()[item_index].get_item_cost_in_cents();
                        }
                    }
                }
                
                tax_in_cents = (int) (subtotal_in_cents * 0.05);
                
                total_in_cents = subtotal_in_cents + tax_in_cents;
                
                total_panel = new JPanel();
                total_panel.setLayout(new GridLayout(3, 2, 5, 5));
                total_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                total_panel.setBackground(Utility.LIGHT_STEEL_BLUE);
                
                subtotal_name_label = new JLabel("<html><b>ORDER SUBTOTAL</b>");
                tax_name_label = new JLabel("<html><b>ORDER TAX</b>");
                total_name_label = new JLabel("<html><b>ORDER TOTAL</b>");
                
                subtotal_amount_label = new JLabel(Utility.convert_cents_for_display(subtotal_in_cents));
                subtotal_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                tax_amount_label = new JLabel(Utility.convert_cents_for_display(tax_in_cents));
                tax_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_amount_label = new JLabel(Utility.convert_cents_for_display(total_in_cents));
                total_amount_label.setHorizontalAlignment(JLabel.RIGHT);
                
                total_panel.add(subtotal_name_label);
                total_panel.add(subtotal_amount_label);
                total_panel.add(tax_name_label);
                total_panel.add(tax_amount_label);
                total_panel.add(total_name_label);
                total_panel.add(total_amount_label);
                
                button_panel = new JPanel();
                button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                button_panel.setBackground(Utility.DARK_SLATE_BLUE);
                
                cancel_order_button = new JButton("CANCEL ORDER");
                cancel_order_button_handler = new CancelOrderButtonHandler();
                cancel_order_button.addActionListener(cancel_order_button_handler);
                
                confirm_order_button = new JButton("CONFIRM ORDER");
                confirm_order_button_handler = new ConfirmOrderButtonHandler();
                confirm_order_button.addActionListener(confirm_order_button_handler);
                
                button_panel.add(cancel_order_button);
                button_panel.add(confirm_order_button);
                
                main_panel = new JPanel();
                main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
                
                main_panel.add(title_panel);
                main_panel.add(item_panel);
                main_panel.add(total_panel);
                main_panel.add(button_panel);
                
                review_order_dialog.getContentPane().add(main_panel);
                
                review_order_dialog.pack();
                review_order_dialog.setResizable(false);
                review_order_dialog.setLocationRelativeTo(null);
                review_order_dialog.setModal(true);
                review_order_dialog.setVisible(true);
                review_order_dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }
        
        public class CancelOrderButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                review_order_dialog.dispose();
            }
        }
        
        public class ConfirmOrderButtonHandler implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                review_order_dialog.dispose();
                
                Utility.global_funds_in_cents -= total_in_cents;
                
                Utility.global_inventory.order_items(items_to_be_ordered);
                
                new MainWindow(active_employee_name);
            }
        }
    }
}
