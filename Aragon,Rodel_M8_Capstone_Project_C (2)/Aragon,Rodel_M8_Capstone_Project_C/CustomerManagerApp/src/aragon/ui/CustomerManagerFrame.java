/***********************************************************
*                                                          *
*  Author:        Rodel Aragon                             *
*  Course:        CS2420 Advanced Java Programming         *
*  Assignment:    Module 8, CapStoneProject                *
*  Program:       CustomerManagerApp                       *
*  Description:   Application that uses a GUI to update    *
*                 a database of customers.                 *
*  Input:         User-input data for email and names.     *
*  Output:        Displays the user's information.         *
*  Created:       4/29/2018                                *
*                                                          *
***********************************************************/

package aragon.ui;

import aragon.db.Customer;
import aragon.db.CustomerDB;
import aragon.db.CustomerTableModel;
import aragon.db.DBException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Rodel Aragon
 */
public class CustomerManagerFrame extends JFrame{
    
    private JTable customerTable;
    public CustomerTableModel customerTableModel;
    
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton exitButton;
    
    public CustomerManagerFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        initializeComponents();
        setTitle("CustomerManager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
    }
    
    /**
     * Initializes the components of the frame.
     */
    private void initializeComponents() {
        customerTableModel = new CustomerTableModel();
        customerTable = new JTable(customerTableModel);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.setBorder(null);
        
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        exitButton = new JButton("Exit");
        
        addButton.setToolTipText("Adds a customer to the table.");
        addButton.addActionListener((ActionEvent) -> {
            addButtonClicked();
        });
        editButton.setToolTipText("Edit a customer's information.");
        editButton.addActionListener((ActionEvent) -> {
            editButtonClicked();
        });
        deleteButton.setToolTipText("Delete a customer from the table");
        deleteButton.addActionListener((ActionEvent) ->{
            deleteButtonClicked();
        });
        exitButton.setToolTipText("Exit the program.");
        exitButton.addActionListener((ActionEvent) -> {
            exitButtonClicked();
        });
        
        //JScrollPane 
        JScrollPane tableScrollPane = new JScrollPane(customerTable);
        add(tableScrollPane, BorderLayout.CENTER);
        
        //JButton Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
    }
    
    private void addButtonClicked() {
        CustomerForm customerForm = new CustomerForm(
                this, "Add Customer", false);
        customerForm.setLocationRelativeTo(this);
        customerForm.setVisible(true);
    }
    
    private void editButtonClicked() {
        if (customerTable.getSelectedRow() != -1)
        {
            Customer customerToEdit = customerTableModel.getCustomer(
                            customerTable.getSelectedRow());
            CustomerForm customerForm = new CustomerForm(
                    this, "Add Customer", false, customerToEdit);
            customerForm.setLocationRelativeTo(this);
            customerForm.setVisible(true);
        } else {
            String message = "Please select a row to update.";
            JOptionPane.showMessageDialog(this, message, "No Row Selected",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteButtonClicked() {
        if (customerTable.getSelectedRow() != -1)
        {
            Customer customerToDelete = customerTableModel.getCustomer(
                    customerTable.getSelectedRow());
            String confirmationMessage = "Are you sure you want to delete "
                    + customerToDelete.getFirstName() + " "
                    + customerToDelete.getLastName() + ".";
                    
            int option = JOptionPane.showConfirmDialog(this,
                    confirmationMessage ,
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            switch (option)
            {
                case JOptionPane.YES_OPTION:
                    
                    String message = customerToDelete.getFirstName() + " " + 
                    customerToDelete.getLastName() + " has been deleted.";
                    try {
                        CustomerDB.deleteCustomer(customerToDelete.getEmail());
                        customerTableModel.databaseUpdated();
                        JOptionPane.showMessageDialog(this, message,
                                "Customer Deleted",
                        JOptionPane.INFORMATION_MESSAGE);
                    } catch (DBException e) {
                    System.out.println(e);
                    }
                    break;
                default:
                    break;
            }
        } else {
            String message = "Please select a row to update.";
            JOptionPane.showMessageDialog(this, message, "No Row Selected",
                    JOptionPane.ERROR_MESSAGE);    
        }
    }
    
    private void exitButtonClicked() {
        System.exit(0);
    }
    
    public void fireDatabaseUpdatedEvent() {
        customerTableModel.databaseUpdated();
    }
}
