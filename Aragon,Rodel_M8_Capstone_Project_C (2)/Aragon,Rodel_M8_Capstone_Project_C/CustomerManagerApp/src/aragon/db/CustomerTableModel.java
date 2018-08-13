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
    
    @Override
    public int getRowCount() {
        return customers.size();
    }
    
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
    
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
    
    public Customer getCustomer(int rowIndex) {
        return customers.get(rowIndex);
    }
    
    public void databaseUpdated() {
        try {
            customers = CustomerDB.getCustomers();
            fireTableDataChanged();
        } catch (DBException e) {
            System.out.println(e);
        }
    }
}
