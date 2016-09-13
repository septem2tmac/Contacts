package contacts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Septem
 */
public class ContactsJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Variables declaration
    private JLabel LastNameLabel;
    private JTextField LastNameText;
    private JButton addButton;
    private JLabel addressLine1Label;
    private JTextField addressLine1Text;
    private JLabel addressLine2Label;
    private JTextField addressLine2Text;
    private JLabel addressValidLabel;
    private JLabel cityLabel;
    private JTextField cityText;
    private JLabel cityValidLabel;
    private JButton clearButton;
    private JLabel dateLabel;
    private JTextField dateText;
    private JButton deleteButton;
    private JLabel emailLabel1;
    private JTextField emailText;
    private JLabel emailValidLabel;
    private JLabel firnameValidLabel;
    private JLabel firstNameLabel;
    private JTextField firstNameText;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JTable jTable;
    private JLabel lastnameValidLabel;
    private JLabel middeNameLabel;
    private JTextField middleNameText;
    private JButton modifyButton;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberText;
    private JLabel phonenumberValidLabel;
    private JComboBox<String> purchaseComboBox;
    private JLabel purchaseLabel;
    private JButton quitButton;
    private JLabel reminderLabel;
    private JComboBox<String> stateComboBox;
    private JLabel stateLabel;
    private JLabel zipcodeLabel;
    private JTextField zipcodeText;
    private JLabel zipcodeValidLabel;
    private List<String> allUsers;
    private int row;
    /**
     * Creates new form RebateJFrame
     */
    public ContactsJFrame() {
        initComponents();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	new ReadWriteFile().writeTofile(allUsers);
                e.getWindow().dispose();
            }
        });
    }
    // initialize the Frame and its every parts.
    private void initComponents() {

        jScrollPane = new JScrollPane();
        jTable = new JTable();
        jPanel = new JPanel();
        firstNameLabel = new JLabel();
        firstNameText = new JTextField();
        LastNameText = new JTextField();
        LastNameLabel = new JLabel();
        middeNameLabel = new JLabel();
        middleNameText = new JTextField();
        addressLine1Label = new JLabel();
        addressLine1Text = new JTextField();
        addressLine2Label = new JLabel();
        addressLine2Text = new JTextField();
        cityLabel = new JLabel();
        cityText = new JTextField();
        stateLabel = new JLabel();
        zipcodeLabel = new JLabel();
        zipcodeText = new JTextField();
        phoneNumberLabel = new JLabel();
        phoneNumberText = new JTextField();
        purchaseLabel = new JLabel();
        emailText = new JTextField();
        emailLabel1 = new JLabel();
        purchaseComboBox = new JComboBox<String>();
        dateLabel = new JLabel();
        dateText = new JTextField();
        addButton = new JButton();
        modifyButton = new JButton();
        clearButton = new JButton();
        deleteButton = new JButton();
        quitButton = new JButton();
        firnameValidLabel = new JLabel();
        lastnameValidLabel = new JLabel();
        reminderLabel = new JLabel();
        zipcodeValidLabel = new JLabel();
        addressValidLabel = new JLabel();
        phonenumberValidLabel = new JLabel();
        emailValidLabel = new JLabel();
        stateComboBox = new JComboBox<String>();
        cityValidLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rebates");
        
        // Set ScrollPane ---- left part in Frame
        setScrollPane();

        // Set Panel ---- right part in Frame
        setPanel();

        // Display the whole Frame
        displayFrame();
    }
    // Add new user to the file
    private void addButtonActionPerformed(ActionEvent evt) {
        
    	// If there is no fault, add new user to list, then update the table and clear the Panel
    	if (determineFromText()) {
    		allUsers.add(firstNameText.getText());
    		allUsers.add(middleNameText.getText());
            allUsers.add(LastNameText.getText());
            allUsers.add(addressLine1Text.getText());
            allUsers.add(addressLine2Text.getText());
            allUsers.add(cityText.getText());
            allUsers.add((String) stateComboBox.getSelectedItem());
            allUsers.add(zipcodeText.getText());
            allUsers.add(phoneNumberText.getText());
            allUsers.add(emailText.getText());
            allUsers.add((String) purchaseComboBox.getSelectedItem());
            allUsers.add(dateText.getText());

            reminderLabel.setForeground(Color.blue);
            reminderLabel.setText("New User has been added");
            reminderLabel.setVisible(true);
            panelTextClear();
            updateTable();
    	}
    }
    // Update the user's information in file 
    private void modifyButtonActionPerformed(ActionEvent evt) {
    	
    	// If there is no fault, change the current user, then update the table
    	if (determineFromText()) {
    		// Find the current row respective to index in list
    		int rowUpdate = row * 12;
            allUsers.set(rowUpdate++, firstNameText.getText());
            allUsers.set(rowUpdate++, middleNameText.getText());
            allUsers.set(rowUpdate++, LastNameText.getText());
            allUsers.set(rowUpdate++, addressLine1Text.getText());
            allUsers.set(rowUpdate++, addressLine2Text.getText());
            allUsers.set(rowUpdate++, cityText.getText());
            allUsers.set(rowUpdate++, (String)stateComboBox.getSelectedItem());
            allUsers.set(rowUpdate++, zipcodeText.getText());
            allUsers.set(rowUpdate++, phoneNumberText.getText());
            allUsers.set(rowUpdate++, emailText.getText());
            allUsers.set(rowUpdate++, (String)purchaseComboBox.getSelectedItem());
            allUsers.set(rowUpdate++, dateText.getText());
        	
            purchaseLabel.setForeground(null);
            reminderLabel.setForeground(Color.blue);
            reminderLabel.setText("This User has been updated");
            reminderLabel.setVisible(true);
            updateTable();
        }
    }
    
    // Determine if there is any kind of faults before adding or changing user
    private boolean determineFromText() {
    	
    	boolean isConflict = false;
        reminderLabel.setVisible(false);
        int n = allUsers.size() / 12;
        for(int i = 0; i < n; i++) {
        	// row would be -1 without clicking the row of table 
        	if (i == row) {
        		continue;
        	}
        	// Check first three text, i.e firstname, middlename and lastname
        	if (firstNameText.getText().equals(allUsers.get(i * 12)) && middleNameText.getText().equals(allUsers.get(i * 12 + 1)) && LastNameText.getText().equals(allUsers.get(i * 12 + 2))) {
        		isConflict = true;
        		System.out.println("conflict");
        		break;
            }
        }
        // Check if there is a empty text 
        if (firstNameText.getText().isEmpty() || 
        		LastNameText.getText().isEmpty() || 
            	addressLine1Text.getText().isEmpty() || 
            	cityText.getText().isEmpty() || 
            	zipcodeText.getText().isEmpty() || 
            	phoneNumberText.getText().isEmpty() || 
            	emailText.getText().isEmpty() || 
            	dateText.getText().isEmpty()) {
               
        	reminderLabel.setForeground(new Color(255, 153, 0));
            reminderLabel.setText("Please input all fields with *");
            reminderLabel.setVisible(true);
            return false;
        } 
        // Check if the input is invalid
        if (firnameValidLabel.getText().equals("WRONG") ||
     		lastnameValidLabel.getText().equals("WRONG") ||
     		zipcodeValidLabel.getText().equals("WRONG") ||
     		phonenumberValidLabel.getText().equals("WRONG") ||
     		emailValidLabel.getText().equals("WRONG")) {
        	
	        reminderLabel.setForeground(new Color(255, 153, 0));
	        reminderLabel.setText("Please enter the correct input.");
	        reminderLabel.setVisible(true);
	        return false;
        }
        // Check the status of purchased
        if (purchaseComboBox.getSelectedIndex() == 1) {
        	purchaseLabel.setForeground(new Color(255, 153, 0));
        	reminderLabel.setForeground(new Color(255, 153, 0));
        	reminderLabel.setText("\"Purchased\" should be \"Yes\"");
        	reminderLabel.setVisible(true);
        	return false;
        }
        // Check if there is a conflict to the user in file 
        if (isConflict){
        	reminderLabel.setForeground(new Color(255, 153, 0));
            reminderLabel.setText("Sorry! The User with same name has been added already.");
            reminderLabel.setVisible(true);
            return false;
        } 
        return true;
    }
    
    // Clear the whole texts in Panel
    private void clearButtonActionPerformed(ActionEvent evt) {                                              
    	panelTextClear();
    }
    // Delete specified user 
    private void deleteButtonActionPerformed(ActionEvent evt) {
    	
    	reminderLabel.setVisible(false);
    	// A dialog will be prompted
    	int response = JOptionPane.showConfirmDialog(null, "Do you want to delete this user?", "Confirm",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.YES_OPTION) {
    		for(int i = 0, rowDelete = row * 12 + 11; i < 12; i++) {
    			allUsers.remove(rowDelete--); // Delete relevant user
    		}
    		panelTextClear();
    		updateTable();
    	}
    }
    // Determine if input of lastname is valid
    private void LastNameTextKeyReleased(KeyEvent evt) {
        
        if (LastNameText.getText().length() > 2) {
        	lastnameValidLabel.setForeground(new Color(0, 180, 0));
        	lastnameValidLabel.setText("OK");
        } else {
        	lastnameValidLabel.setForeground(Color.red);
        	lastnameValidLabel.setText("WRONG");
        }
        lastnameValidLabel.setVisible(true);
    }
    // Determine if input of firstname is valid
    private void firstNameTextKeyReleased(KeyEvent evt) {
       
        if (firstNameText.getText().length() > 2) {
        	firnameValidLabel.setForeground(new Color(0, 180, 0));
        	firnameValidLabel.setText("OK");
        } else {
        	firnameValidLabel.setForeground(Color.red);
        	firnameValidLabel.setText("WRONG");
        }
        firnameValidLabel.setVisible(true);
    }
    // Determine if input of email is valid
    private void emailTextKeyReleased(KeyEvent evt) {
        
        for(int i = 0; i < emailText.getText().length(); i++) {
    		if(emailText.getText().charAt(i) == '@' && i != 0 && i != emailText.getText().length() - 1) {
    			emailValidLabel.setForeground(new Color(0, 180, 0));
    			emailValidLabel.setText("OK");
            	break;
    		} else {
    			emailValidLabel.setForeground(Color.red);
    			emailValidLabel.setText("WRONG");
            }
    		emailValidLabel.setVisible(true);
    	}
    }
    // Determine if input of phonenumber is valid

    private void phoneNumberTextKeyReleased(KeyEvent evt) {

        if (phoneNumberText.getText().length() != 10) {
        	phonenumberValidLabel.setForeground(Color.red);
        	phonenumberValidLabel.setText("WRONG");
        	phonenumberValidLabel.setVisible(true);
        	return;
        }
        for(int i = 0; i < phoneNumberText.getText().length(); i++) {
        	if (phoneNumberText.getText().charAt(i) > '9' || phoneNumberText.getText().charAt(i) < '0') {
        		phonenumberValidLabel.setForeground(Color.red);
            	phonenumberValidLabel.setText("WRONG");
            	phonenumberValidLabel.setVisible(true);
            	return;
        	}
        }
        phonenumberValidLabel.setForeground(new Color(0, 180, 0));
    	phonenumberValidLabel.setText("OK");
    	phonenumberValidLabel.setVisible(true);
        
    }

    // Determine if input of zipcode is valid
    private void zipcodeTextKeyReleased(KeyEvent evt) {
       
        if (zipcodeText.getText().length() > 4) {
        	zipcodeValidLabel.setForeground(new Color(0, 180, 0));
        	zipcodeValidLabel.setText("OK");
        } else {
        	zipcodeValidLabel.setForeground(Color.red);
        	zipcodeValidLabel.setText("WRONG");
        }
        zipcodeValidLabel.setVisible(true);
    }

    // Determine if input of addressline1 is valid
    private void addressLine1TextKeyReleased(KeyEvent evt) {
       
        if (!addressLine1Text.getText().isEmpty()) {
        	addressValidLabel.setForeground(new Color(0, 180, 0));
        	addressValidLabel.setText("OK");
        } else {
        	addressValidLabel.setForeground(Color.red);
        	addressValidLabel.setText("WRONG");
        }
        addressValidLabel.setVisible(true);
    }
    
    // Determine if input of city is valid
    private void cityTextKeyReleased(KeyEvent evt) {
    	
    	if (!cityText.getText().isEmpty()) {
        	cityValidLabel.setForeground(new Color(0, 180, 0));
        	cityValidLabel.setText("OK");
        } else {
        	cityValidLabel.setForeground(Color.red);
        	cityValidLabel.setText("WRONG");
        }
    	cityValidLabel.setVisible(true);
    }

    
    // Click one row of table to show the whole information of user
    private void jTableMouseClicked(MouseEvent evt) {                                    
        row = jTable.rowAtPoint(evt.getPoint());
        int i = row * 12;
        
        addButton.setEnabled(false);
        modifyButton.setEnabled(true);
        deleteButton.setEnabled(true);
        reminderLabel.setVisible(false);
        purchaseLabel.setForeground(null);
        
        firstNameText.setText(allUsers.get(i++));
    	middleNameText.setText(allUsers.get(i++));
	  	LastNameText.setText(allUsers.get(i++)); 
	  	addressLine1Text.setText(allUsers.get(i++));
	  	addressLine2Text.setText(allUsers.get(i++));
	  	cityText.setText(allUsers.get(i++));
	  	stateComboBox.setSelectedItem(allUsers.get(i++));
	  	zipcodeText.setText(allUsers.get(i++));
	  	phoneNumberText.setText(allUsers.get(i++));
	  	emailText.setText(allUsers.get(i++));
	  	purchaseComboBox.setSelectedItem(allUsers.get(i++));
	  	dateText.setText(allUsers.get(i++)); 
    }
    

    // Quit the Frame
    private void quitButtonActionPerformed(ActionEvent evt) {   
    	new ReadWriteFile().writeTofile(allUsers);
        System.exit(0);
    }
    // Method of setting ScrollPane of Frame
    private void setScrollPane() {
    	
    	jScrollPane.setPreferredSize(new Dimension(300, 402));

        allUsers = new ReadWriteFile().readFromfile();//readFromFile(); ////Read all users' information from Users.txt  
        updateTable(); //Refresh the names and phone numbers in table
        jTable.setFont(new Font("Serif", 0, 14)); 
        jTable.setRowHeight(20);
        jTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(jTable);
        getContentPane().add(jScrollPane, BorderLayout.CENTER);
    }
    
    // Method of setting Panel of Frame
    private void setPanel() {
    	
    	jPanel.setBorder(BorderFactory.createEtchedBorder());
        
        // Set firstName part.
        firstNameLabel.setText("First Name*");
        firstNameText.setFont(new Font("Times New Roman", 0, 12)); 
        firstNameText.setDocument(new JTextFieldLimit(20));// The valid length is 20
        firstNameText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                firstNameTextKeyReleased(evt);
            }
        });
        firnameValidLabel.setVisible(false);

        // Set lastname part.
        LastNameLabel.setText("Last Name*");
        LastNameText.setFont(new Font("Times New Roman", 0, 12)); 
        LastNameText.setDocument(new JTextFieldLimit(20));// The valid length is 20.
        LastNameText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                LastNameTextKeyReleased(evt);
            }
        });
        lastnameValidLabel.setVisible(false);
        
        // Set lastname part.
        middeNameLabel.setText("Middle");
        middleNameText.setFont(new Font("Times New Roman", 0, 12));
        middleNameText.setDocument(new JTextFieldLimit(1));

        // Set addressline1 part.
        addressLine1Label.setText("Address Line1*");
        addressLine1Text.setFont(new Font("Times New Roman", 0, 12));
        addressLine1Text.setDocument(new JTextFieldLimit(35));// The valid length is 35
        addressLine1Text.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                addressLine1TextKeyReleased(evt);
            }
        });
        addressValidLabel.setVisible(false);

        // Set addressline2 part.
        addressLine2Label.setText("Address Line2");
        addressLine2Text.setFont(new Font("Times New Roman", 0, 12));
        addressLine2Text.setDocument(new JTextFieldLimit(35));// The valid length is 35

        // Set city part
        cityLabel.setText("City*");
        cityText.setFont(new Font("Times New Roman", 0, 12));
        cityText.setDocument(new JTextFieldLimit(25));// The valid length is 25
        cityText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                cityTextKeyReleased(evt);
            }
        });
        
        // Set state part
        stateLabel.setText("State*");
        stateComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY" }));
        
        // Set zipcode part
        zipcodeLabel.setText("ZIP Code*");
        zipcodeText.setFont(new Font("Times New Roman", 0, 12)); 
        zipcodeText.setDocument(new JTextFieldLimit(9));// The valid length is 9
        zipcodeText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                zipcodeTextKeyReleased(evt);
            }
        });
        zipcodeValidLabel.setVisible(false);

        // Set phonenumber part
        phoneNumberLabel.setText("Phone Number*");
        phoneNumberText.setFont(new Font("Times New Roman", 0, 12)); 
        phoneNumberText.setDocument(new JTextFieldLimit(21));// The valid length is 21
        phoneNumberText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                phoneNumberTextKeyReleased(evt);
            }
        });

        // Set purchased part
        purchaseLabel.setText("Purchased*");
        purchaseComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Yes", "No" }));
        purchaseComboBox.setSelectedIndex(1);
        
        // Set email part
        emailLabel1.setText("Email*");
        emailText.setFont(new Font("Times New Roman", 0, 12)); 
        emailText.setDocument(new JTextFieldLimit(60));// The valid length is 60
        emailText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                emailTextKeyReleased(evt);
            }
        });
        emailValidLabel.setVisible(false);
  
        // Set date part
        dateLabel.setText("Date*");
        dateText.setFont(new Font("Times New Roman", 0, 12)); 
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        dateText.setText(dateFormat.format(date));
        
        // Set reminder part
        reminderLabel.setForeground(new Color(255, 153, 0));
        reminderLabel.setVisible(false);
        
        // Set ADD button
        addButton.setText("ADD");
        addButton.setEnabled(true);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        // Set MODIFY button
        modifyButton.setText("MODIFY");
        modifyButton.setEnabled(false);
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        // Set CLEAR button
        clearButton.setForeground(Color.blue);
        clearButton.setText("CLEAR");
        deleteButton.setEnabled(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        // Set DELETE button
        deleteButton.setForeground(Color.red);
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        // Set QUIT button
        quitButton.setText("QUIT");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
    }
    
    // Method of displaying the whole Frame
    private void displayFrame() {
    	
    	GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(emailText, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailValidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(LastNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firstNameLabel, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                    .addComponent(middeNameLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstNameText, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(LastNameText)
                                    .addComponent(middleNameText, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lastnameValidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firnameValidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(reminderLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(addressLine2Label, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                            .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(stateLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                                .addComponent(cityLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(135, 135, 135)
                                            .addComponent(cityValidLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(emailLabel1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                            .addComponent(addressLine1Label, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addressValidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                            .addComponent(purchaseLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(purchaseComboBox, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(phoneNumberLabel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                            .addGap(157, 157, 157)
                                            .addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dateText, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                            .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(clearButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(16, 16, 16)
                                            .addComponent(modifyButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(deleteButton, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                                .addComponent(quitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(addressLine2Text)
                                        .addComponent(addressLine1Text)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(zipcodeLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(stateComboBox, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cityText, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLayout.createSequentialGroup()
                                        .addComponent(zipcodeText, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zipcodeValidLabel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(phoneNumberText, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phonenumberValidLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(firstNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(firnameValidLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(middeNameLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(middleNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(LastNameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastnameValidLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLine1Label)
                    .addComponent(addressValidLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLine1Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addressLine2Label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressLine2Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(cityValidLabel)
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cityLabel, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(cityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(stateLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(zipcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipcodeLabel)
                    .addComponent(zipcodeValidLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneNumberLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(phonenumberValidLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailLabel1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailValidLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(purchaseLabel)
                        .addComponent(purchaseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(reminderLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton)
                    .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(modifyButton)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton)
                    .addComponent(clearButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelTextClear();
        getContentPane().add(jPanel, java.awt.BorderLayout.LINE_END);
        pack();
        setLocationRelativeTo(null);
    }
       
    //Refresh the names and phone numbers in table.
    // Method of Updating table
    private void updateTable() {
    	
        int n = allUsers.size() / 12;
        String[][] users = new String[n][2];
        for(int i = 0; i < n; i++) {
            int j = i * 12;
            if (allUsers.get(j + 1).equals("")) {
            	users[i][0] = allUsers.get(j) + ", " + allUsers.get(j + 2);
            } else {
            	users[i][0] = allUsers.get(j) + ", " + allUsers.get(j + 1) + ", " + allUsers.get(j + 2);
            }
            users[i][1] = allUsers.get(j + 8);
        }
        jTable.setModel(new DefaultTableModel(users, new String[] {"Name", "Phone Number"}) {
        	
			private static final long serialVersionUID = 1L;
            boolean[] canEdit = {false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    // Method of Clearing the whole texts in Panel
    private void panelTextClear() {
        
    	row = -1;
    	firstNameText.setText("");
    	middleNameText.setText("");
	  	LastNameText.setText(""); 
	  	addressLine1Text.setText("");
	  	addressLine2Text.setText("");
	  	cityText.setText("");
	  	stateComboBox.setSelectedIndex(0);
	  	zipcodeText.setText("");
	  	phoneNumberText.setText("");
	  	emailText.setText("");
	  	purchaseComboBox.setSelectedIndex(1);
	  	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
	  	dateText.setText(dateFormat.format(date)); 
	  	
	  	addButton.setEnabled(true);
	  	modifyButton.setEnabled(false);
	  	deleteButton.setEnabled(false);
	  	reminderLabel.setVisible(false);
	  	
	  	firnameValidLabel.setVisible(false);
        lastnameValidLabel.setVisible(false);
        reminderLabel.setForeground(new Color(255, 153, 0));
        reminderLabel.setVisible(false);
        zipcodeValidLabel.setVisible(false);
        cityValidLabel.setVisible(false);
        addressValidLabel.setVisible(false);
        phonenumberValidLabel.setVisible(false);
        emailValidLabel.setVisible(false);
        purchaseLabel.setForeground(null);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactsJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ContactsJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ContactsJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ContactsJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactsJFrame().setVisible(true);
            }
        });
    } 
}
