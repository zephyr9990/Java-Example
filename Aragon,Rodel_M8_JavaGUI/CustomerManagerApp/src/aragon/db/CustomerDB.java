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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rodel Aragon
 */
public class CustomerDB {
    
    /**
     * Gets the list of customers from the database.
     * @return An ArrayList of <code>Customer</code> objects.
     * @throws DBException The exception is thrown when database cannot be
     *  accessed.
     */
    public static ArrayList<Customer> getCustomers() throws DBException {
        String sql = "SELECT * FROM Customer "
                + "ORDER BY Customer_Email";
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String email = rs.getString("Customer_Email");
                String firstName = rs.getString("Customer_FName");
                String lastName = rs.getString("Customer_LName");
                
                customers.add(new Customer(email, firstName, lastName));
            }
        } catch (SQLException e) {
            throw new DBException(
                    "Could not retrieve customers from database.");
        }
        return customers;
    }
    
    /**
     * Adds a customer to the database.
     * @param customer The customer to add
     * @return True if the addition was successful, or false if not.
     * @throws DBException The exception is thrown when database cannot be
     *  accessed.
     */
    public static boolean addCustomer(Customer customer) throws DBException {
        String sql = "INSERT INTO Customer (Customer_Email, Customer_FName, "
                + "Customer_LName) "
                + "Values (?, ?, ?)";
        boolean customerAdded = false;
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.executeUpdate();
            customerAdded = true;
        } catch (SQLException e) {
            throw new DBException("Could not add customer to database.");
            }
        return customerAdded;
    }
    
    /**
     * Deletes a customer from the database.
     * @param email The email of the customer to delete.
     * @return True if deletion was successful, otherwise false.
     * @throws DBException The exception is thrown when database cannot be
     *  accessed.
     */
    public static boolean deleteCustomer(String email) throws DBException {
        String sql = "DELETE FROM Customer "
                + "WHERE Customer_Email = ?";
        boolean customerDeleted = false;
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
            customerDeleted = true;
        } catch (SQLException e) {
            throw new DBException("Could not delete customer from database.");
        }
        return customerDeleted;
    }
    
    /**
<<<<<<< HEAD:Aragon,Rodel_M8_JavaGUI/CustomerManagerApp/src/aragon/db/CustomerDB.java
     * Updates a customer's information inside the database.
     * @param customer The customer to be updated.
     * @return True if update was successful, otherwise false.
     * @throws DBException The exception is thrown when database cannot be
     *  accessed.
=======
     * Updates a customer's information from the database.
     * @param customer The customer to update.
     * @return True if customer was updated, false if not.
>>>>>>> 2fbd9ede8306312fcb954fb4be76b19f236e3b68:Aragon,Rodel_M8_Capstone_Project_C (2)/Aragon,Rodel_M8_Capstone_Project_C/CustomerManagerApp/src/aragon/db/CustomerDB.java
     */
    public static boolean updateCustomer(Customer customer) throws DBException
    {
        String sql = "UPDATE Customer "
                + "SET Customer_FName = ?, "
                + "Customer_LName = ? "
                + "WHERE Customer_Email = ?";
        boolean customerUpdated = false;
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.executeUpdate();
            customerUpdated = true;
        } catch (SQLException e) {
            throw new DBException("Could not update customer.");
        }
        return customerUpdated;
    }
}
