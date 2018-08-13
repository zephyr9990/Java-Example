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

/**
 *
 * @author Rodel Aragon
 */
public class Customer {
    private String email;
    private String firstName;
    private String lastName;
    
    /**
     * Parameterized Constructor.
     * @param ID The ID of the customer.
     * @param email The email of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     */
    public Customer(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the customer's email.
     * @return Returns the email of the customer.
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * Gets the customer's first name.
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Gets the customer's last name.
     * @return The customer's last name.
     */
    public String getLastName() {
        return this.lastName;
    }
}
