// Timothy Dement
// CSC 340-01: Software Engineering
// Andrew Holman
// (P4) LoginWindow.java
package P4;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginWindow extends JFrame {

    private JPanel main_panel,
            welcome_panel,
            signin_panel,
            button_panel;

    private JLabel welcome_label,
            message_label,
            username_label,
            password_label;

    private JTextField username_field;

    private JPasswordField password_field;

    private JButton signin_button;

    private SigninButtonHandler signin_button_handler;

    public LoginWindow() {

        welcome_panel = new JPanel();
        welcome_panel.setLayout(new GridLayout(2, 1, 5, 5));
        welcome_panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        welcome_panel.setBackground(Utility.DARK_SLATE_BLUE);

        welcome_label = new JLabel("<html><font color = #ffffff><b>WELCOME</b></font>");
        welcome_label.setHorizontalAlignment(JLabel.CENTER);

        message_label = new JLabel("<html><font color = #ffffff><b>PLEASE SIGN IN BELOW</b></font>");
        message_label.setHorizontalAlignment(JLabel.CENTER);

        welcome_panel.add(welcome_label);
        welcome_panel.add(message_label);

        signin_panel = new JPanel();
        signin_panel.setLayout(new GridLayout(2, 2, 5, 5));
        signin_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        signin_panel.setBackground(Utility.LIGHT_STEEL_BLUE);

        username_label = new JLabel("Username:");

        password_label = new JLabel("Password:");

        username_field = new JTextField();
        password_field = new JPasswordField();

        signin_panel.add(username_label);
        signin_panel.add(username_field);
        signin_panel.add(password_label);
        signin_panel.add(password_field);

        button_panel = new JPanel();
        button_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button_panel.setBackground(Utility.DARK_SLATE_BLUE);

        signin_button = new JButton("SIGN IN");
        signin_button_handler = new SigninButtonHandler();
        signin_button.addActionListener(signin_button_handler);

        button_panel.add(signin_button);

        main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));

        main_panel.add(welcome_panel);
        main_panel.add(signin_panel);
        main_panel.add(button_panel);

        this.getContentPane().add(main_panel);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public class SigninButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (Utility.global_employee_array[0].get_employee_username().equals(username_field.getText())
                    && Utility.global_employee_array[0].get_employee_password().equals(new String(password_field.getPassword()))) {

                new MainWindow(Utility.global_employee_array[0].get_employee_name());

                dispose();

            } else if (Utility.global_employee_array[1].get_employee_username().equals(username_field.getText())
                    && Utility.global_employee_array[1].get_employee_password().equals(new String(password_field.getPassword()))) {

                new MainWindow(Utility.global_employee_array[1].get_employee_name());

                dispose();

            } else if (Utility.global_employee_array[2].get_employee_username().equals(username_field.getText())
                    && Utility.global_employee_array[2].get_employee_password().equals(new String(password_field.getPassword()))) {

                new MainWindow(Utility.global_employee_array[2].get_employee_name());

                dispose();

            } else if (Utility.global_employee_array[3].get_employee_username().equals(username_field.getText())
                    && Utility.global_employee_array[3].get_employee_password().equals(new String(password_field.getPassword()))) {

                new MainWindow(Utility.global_employee_array[3].get_employee_name());

                dispose();

            } else {

                JDialog failed_signin_dialog = new JDialog();

                JPanel main_panel = new JPanel();
                main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
                main_panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                main_panel.setBackground(Color.WHITE);

                JLabel warning_label = new JLabel("<html><font color = #ff0000><b>WARNING</font></b>");

                JLabel failure_message_label = new JLabel("<html><font color = #ff0000>Please enter a valid username and password</font></b>");

                main_panel.add(warning_label);
                main_panel.add(Box.createRigidArea(new Dimension(0, 10)));
                main_panel.add(failure_message_label);

                failed_signin_dialog.getContentPane().add(main_panel);

                failed_signin_dialog.pack();
                failed_signin_dialog.setResizable(false);
                failed_signin_dialog.setLocationRelativeTo(null);
                failed_signin_dialog.setModal(true);
                failed_signin_dialog.setVisible(true);
                failed_signin_dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }

        }
    }
}
