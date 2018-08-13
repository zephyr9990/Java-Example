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
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Rodel Aragon
 */
public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}
    
    /**
     * Gets a connection to the database.
     * @return The connection to the database.
     */
    public static synchronized Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://localhost:3306/mma";
                String username = "mma_user";
                String password = "sesame";
            
                //get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
                return connection;
            } catch (SQLException e) {
                for (Throwable t : e) {
                    System.out.println(t);
                }
            }
        }
        return connection;
    }
    
    /**
     * Closes the connection to the database.
     */
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                for (Throwable t : e) {
                    System.out.println(t);
                }
            } finally {
                connection = null;
            }
        }
    }
}
