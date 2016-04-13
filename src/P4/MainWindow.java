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
        return_panel = new TransactionPanel("RETURN", Utility.global_inventory);
        order_panel = new TransactionPanel("ORDER", Utility.global_inventory);
        
        sale_button_panel = new JPanel();
        sale_button_panel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
        sale_button_panel.setBackground(Utility.DARK_SLATE_BLUE);
        
        clear_sale_button = new JButton("CLEAR SALE");
        review_sale_button = new JButton("REVIEW SALE");
        
        sale_button_panel.add(clear_sale_button);
        sale_button_panel.add(Box.createRigidArea(new Dimension(10,0)));
        sale_button_panel.add(review_sale_button);
        
        full_sale_panel = new JPanel();
        full_sale_panel.setLayout(new BoxLayout(full_sale_panel, BoxLayout.Y_AXIS));
        full_sale_panel.add(sale_panel.get_main_transaction_panel());
        full_sale_panel.add(sale_button_panel);
        
        return_button_panel = new JPanel();
        
        order_button_panel = new JPanel();
        
        transaction_panes = new JTabbedPane();
        
        transaction_panes.addTab("SALE", full_sale_panel);
        transaction_panes.addTab("RETURN", return_panel.get_main_transaction_panel());
        transaction_panes.addTab("ORDER", order_panel.get_main_transaction_panel());
        
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
            LoginWindow login_window = new LoginWindow();
        }
    }
}
