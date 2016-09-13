/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts;

/**
 *
 * @author Septem
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//This class is used to set max length of the JTextField
public class JTextFieldLimit extends PlainDocument {
 
    private int limit;
    
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }
    
    public void insertString(int offset, String  str,AttributeSet attr) throws BadLocationException {
        
        if (str == null)  //put your max length validation logic
          return;

        if ((getLength() + str.length()) <= limit) {
          super.insertString(offset, str, attr);
        }
    }
}
