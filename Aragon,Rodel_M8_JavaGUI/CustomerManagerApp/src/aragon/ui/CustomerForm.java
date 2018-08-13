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
import aragon.db.DBException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Rodel Aragon
 */
public class CustomerForm extends JDialog {
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton confirmButton;
    private JButton cancelButton;
    
    private Customer customer;
    private int customerID;
    
    /**
     * Creates the customer form dialog
     * @param parent The parent of the dialog
     * @param title The title of the dialog
     * @param modal Specifies whether to set as modal or not
     */
    public CustomerForm(java.awt.Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        initializeComponents();
    }
    
    /**
     * Creates a customer dialog with customer information
     * @param parent The parent of the dialog
     * @param title The title of the dialog
     * @param modal Specifies whether to set as modal or not
     * @param customer The customer to be displayed
     */
    public CustomerForm(java.awt.Frame parent, String title,
            boolean modal, Customer customer) {
        this(parent, title, modal);
        confirmButton.setText("Save");
        emailField.setEditable(false);
        emailField.setText(customer.getEmail());
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
    }
    
    /**
     * Initializes the components of the dialog.
     */
    public void initializeComponents() {
        emailField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        confirmButton = new JButton();
        cancelButton = new JButton();
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        Dimension dimension = new Dimension(150, 20);
        emailField.setPreferredSize(dimension);
        firstNameField.setPreferredSize(dimension);
        lastNameField.setPreferredSize(dimension);
        emailField.setMinimumSize(dimension);
        firstNameField.setMinimumSize(dimension);
        lastNameField.setMinimumSize(dimension);
        
        cancelButton.setText("Cancel");
        cancelButton.addActionListener((ActionEvent) ->{
            cancelButtonClicked();
        });
        
        confirmButton.setText("Add");
        confirmButton.addActionListener((ActionEvent) -> {
            confirmButtonClicked();
        });
        
        //JLabel and JTextField Panel
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.add(new JLabel("Email:"),
                getConstraints(0, 0, GridBagConstraints.LINE_END));
        dataPanel.add(emailField,
                getConstraints(1, 0, GridBagConstraints.LINE_START));
        dataPanel.add(new JLabel("First Name:"),
                getConstraints(0, 1, GridBagConstraints.LINE_END));
        dataPanel.add(firstNameField,
                getConstraints(1, 1, GridBagConstraints.LINE_START));
        dataPanel.add(new JLabel("Last Name:"),
                getConstraints(0, 2, GridBagConstraints.LINE_END));
        dataPanel.add(lastNameField,
                getConstraints(1, 2, GridBagConstraints.LINE_START));
        
        //JButton panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        // add panels to main panel
        setLayout(new BorderLayout());
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    
    /**
     * Specifies the placement of data.
     * @param x The x-coordinate for the data.
     * @param y The y-coordinate for the data.
     * @param anchor The alignment of the data.
     * @return A GridBagConstraints class.
     */
    private GridBagConstraints getConstraints(int x, int y, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,0,5);
        c.gridx = x;
        c.gridy = y;
        c.anchor = anchor;
        return c;
    }
    
    /**
     * Action to be performed when cancel is clicked.
     */
    private void cancelButtonClicked() {
        dispose();
    }
    
    /**
     * Action to be performed when the confirmation button is clicked.
     */
    private void confirmButtonClicked() {
        //if fields are not empty
        if(SwingValidator.isNotEmpty(emailField, "Email") && 
                SwingValidator.isEmail(emailField, "Email") &&
                SwingValidator.isNotEmpty(firstNameField, "First Name") &&
                SwingValidator.isNotEmpty(lastNameField, "Last Name")) {
            setData();
            if(confirmButton.getText().equals("Add")) {
                doAdd();
            } else {
                doEdit();
            }
        }
    }
    
    /**
     * Sets the data for the customer.
     */
    private void setData() {
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        
        customer = new Customer(email, firstName, lastName);
    }
    
    /**
     * Edits a customer's information in the database. 
     */
    private void doEdit() {
        String message = "Updated customer name to " +
                customer.getFirstName() + " " +
                customer.getLastName() +".";
        try {
            CustomerDB.updateCustomer(customer);
            JOptionPane.showMessageDialog(this, message, "Customer Updated",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            fireDatabaseUpdatedEvent();
        } catch (DBException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Adds a customer to the database.
     */
    private void doAdd() {
        String message = customer.getFirstName() + " " +
                customer.getLastName() + " has been added.";
        try {
            CustomerDB.addCustomer(customer);
            JOptionPane.showMessageDialog(this, message, "Customer Added",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            fireDatabaseUpdatedEvent();
        } catch(DBException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Updates the GUI in response to the database being updated.
     */
    private void fireDatabaseUpdatedEvent() {
        CustomerManagerFrame mainWindow = (CustomerManagerFrame) getOwner();
        mainWindow.fireDatabaseUpdatedEvent();
    }
}
