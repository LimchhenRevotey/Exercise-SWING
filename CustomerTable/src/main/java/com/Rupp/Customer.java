package com.Rupp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Customer extends JFrame implements

    ActionListener {
        private JTextField customerIdField, customerLastNameField, customerFirstNameField, customerPhoneField;
        private JButton previousButton, nextButton;
        private Connection dbConnection;
        private Statement dbStatement;
        private ResultSet customerResultSet;

        public Customer() {
            setTitle("Customer Information");
            setSize(400, 250);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(5, 2, 10, 10));

            JLabel idLabel = new JLabel("Customer ID:");
            JLabel lastNameLabel = new JLabel("Last Name:");
            JLabel firstNameLabel = new JLabel("First Name:");
            JLabel phoneLabel = new JLabel("Phone:");

            customerIdField = new JTextField();
            customerLastNameField = new JTextField();
            customerFirstNameField = new JTextField();
            customerPhoneField = new JTextField();

            customerIdField.setEditable(false);
            customerLastNameField.setEditable(false);
            customerFirstNameField.setEditable(false);
            customerPhoneField.setEditable(false);

            previousButton = new JButton("Previous");
            nextButton = new JButton("Next");

            previousButton.addActionListener(this);
            nextButton.addActionListener(this);

            add(idLabel);
            add(customerIdField);
            add(lastNameLabel);
            add(customerLastNameField);
            add(firstNameLabel);
            add(customerFirstNameField);
            add(phoneLabel);
            add(customerPhoneField);
            add(previousButton);
            add(nextButton);

            initializeDatabaseConnection();
            fetchAndDisplayFirstRecord();

            setVisible(true);
        }

        private void initializeDatabaseConnection () {
            try {
                String dbUrl = "jdbc:mysql://localhost:3303/Customer";
                String dbUser = "";
                String dbPassword = "";

                dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                dbStatement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                customerResultSet = dbStatement.executeQuery("SELECT * FROM Customer");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void fetchAndDisplayFirstRecord () {
            try {
                if (customerResultSet.first()) {
                    updateDisplayedRecord();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void updateDisplayedRecord () {
            try {
                customerIdField.setText(customerResultSet.getString("customer_id"));
                customerLastNameField.setText(customerResultSet.getString("customer_last_name"));
                customerFirstNameField.setText(customerResultSet.getString("customer_first_name"));
                customerPhoneField.setText(customerResultSet.getString("customer_phone"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void actionPerformed (ActionEvent event){
            try {
                if (event.getSource() == previousButton) {
                    if (customerResultSet.previous()) {
                        updateDisplayedRecord();
                    } else {
                        customerResultSet.first();
                        updateDisplayedRecord();
                    }
                } else if (event.getSource() == nextButton) {
                    if (customerResultSet.next()) {
                        updateDisplayedRecord();
                    } else {
                        customerResultSet.last();
                        updateDisplayedRecord();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
