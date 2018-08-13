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

package aragon.db;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodel Aragon
 */
public class CustomerTableModel extends AbstractTableModel {
    private List<Customer> customers;
    private final String[] COLUMN_NAMES = {"Email", "First Name", "Last Name"};
    
    public CustomerTableModel() {
        try {
            customers = CustomerDB.getCustomers();
        } catch (DBException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Gets the number of rows (customers) within the database.
     * @return The number of rows (customers) inside the database.
     */
    @Override
    public int getRowCount() {
        return customers.size();
    }
    
    /**
     * Gets the number of columns (fields) within the database.
     * @return The number of columns (fields) within the database.
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    /**
     * Gets the name of the field.
     * @param columnIndex The index of the desired column.
     * @return A <code>String</code> containing the name of the column.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
    
    /**
     * Returns a given field of a customer.
     * @param rowIndex The row of the customer.
     * @param columnIndex The desired field.
     * @return An <code>Object</code> containing the customer's field data.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return customers.get(rowIndex).getEmail();
            case 1:
                return customers.get(rowIndex).getFirstName();
            case 2:
                return customers.get(rowIndex).getLastName();
            default:
                return null;
        }
    }
    
    /**
     * Gets a <code>Customer</code> from the database.
     * @param rowIndex The row of the desired customer.
     * @return A <code>Customer</code> from the database.
     */
    public Customer getCustomer(int rowIndex) {
        return customers.get(rowIndex);
    }
    
    /**
     * Updates the table in response to the database being updated.
     */
    public void databaseUpdated() {
        try {
            customers = CustomerDB.getCustomers();
            fireTableDataChanged();
        } catch (DBException e) {
            System.out.println(e);
        }
    }
}
