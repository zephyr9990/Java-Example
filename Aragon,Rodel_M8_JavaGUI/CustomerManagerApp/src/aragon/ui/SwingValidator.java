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

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Rodel Aragon
 */
public class SwingValidator {
    
    /**
     * Checks whether the field is empty.
     * @param field The field to check
     * @param fieldName The name of the field
     * @return true if the field is not empty, otherwise false.
     */
    public static boolean isNotEmpty(JTextField field, String fieldName) {
        if (field.getText().length() == 0) {
            JOptionPane.showMessageDialog(field,
                    fieldName + " is a required field.",
                    "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                    field.requestFocusInWindow();
            return false;
        } else {// field is not empty
            return true;
        }
    }
    
    /**
     * Checks whether the given text within a field is an integer.
     * @param field The field to check
     * @param fieldName The name of the field.
     * @return True if the field contains an integer, false if not.
     */
    public static boolean isInteger(JTextField field, String fieldName) {
        try {
            Integer.parseInt(field.getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(field,
                    fieldName + " contains an invalid integer.",
                    "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Checks whether the given text within a field is a double value.
     * @param field The field to check.
     * @param fieldName The name of the field.
     * @return True if the value is a valid double value, otherwise false.
     */
    public static boolean isDouble(JTextField field, String fieldName) {
        try {
            Double.parseDouble(field.getText());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(field,
                    fieldName + " contains an invalid decimal.",
                    "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Checks whether the given text within a field is an email.
     * @param field The field to check.
     * @param fieldName The name of the field.
     * @return True if the value is a valid email, otherwise false.
     */
    public static boolean isEmail(JTextField field, String fieldName) {
        String[] emailText = field.getText().split("@");
        String[] domainText = null;
        if (emailText.length == 2) {
            String domain = emailText[1];
            domainText = domain.split("\\.");
        }
        if ((emailText == null || emailText.length != 2 ||
                emailText[0].equals("")) || 
                (domainText == null || domainText.length != 2 ||
                domainText[0].equals("") )) {
            JOptionPane.showMessageDialog(field,
                    fieldName + " contains an invalid email.",
                    "Invalid Email", JOptionPane.ERROR_MESSAGE);
            field.requestFocusInWindow();
            return false;
        }
        return true;
    }
}
