package cst8284.asgmt4.scheduler;

import java.awt.Container;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

//import cst8284.asgmt4.employee.Dentist;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

public class AppointmentDialog {
	
	private static final GridBagConstraints textConstants = new GridBagConstraints(
    	0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	private static final GridBagConstraints labelConstants = new GridBagConstraints(
		1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);	
	public static Scheduler scheduler;
	
	public static void showAppointmentDialog(Scheduler scheduler){
		AppointmentDialog.scheduler = scheduler;
		
	    JFrame f = new JFrame("Get, set, change or delete an appointment");  
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    JTextField clientNameInput = setRow("Enter Client Name (as FirstName LastName):", 'n');
	    JTextField clientPhoneInput = setRow("Phone Number (e.g. 613-555-1212):", 'p');
	    JTextField clientDateInput = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	    JTextField clientTimeInput = setRow("Appointment Time:", 't');
	    JTextField clientActivityInput = setRow("Activity Description", 'a');
	   
	    JPanel panel = new JPanel();
	    
	    GridBagConstraints grig = new GridBagConstraints(1, GridBagConstraints.RELATIVE, 2,2,2,2,
	    		GridBagConstraints.SOUTHEAST,2, new Insets(2,2,2,2),1,1);
	    cp.add(panel, grig);
	    
	    JButton saveButton = new JButton("Save");
	    JButton findButton = new JButton("Find");
	    JButton changeButton = new JButton("Change");
	    JButton deleteButton = new JButton("Delete");
	    JButton exitButton = new JButton("Exit");
	    
	    panel.add(saveButton);
	    panel.add(findButton);
	    panel.add(changeButton);
	    panel.add(deleteButton);
	    panel.add(exitButton);
	    
	    saveButton.addActionListener(e -> {
	    	String fullName = clientNameInput.getText();
	    	String phoneNumber = clientPhoneInput.getText();
	    	String date = clientDateInput.getText();
	    	String time = clientTimeInput.getText();
	    	String category = clientActivityInput.getText();
	    	
	    	int reply = JOptionPane.showConfirmDialog(null, "Would you like to save this appointment?", "Please confirm", 
	    			JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	            String activityType = (String) JOptionPane.showInputDialog(null, "Choose now...",
	                    "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, 
	                    scheduler.getEmployee().getActivityType().toArray(), // Array of choices
	                    scheduler.getEmployee().getActivityType().get(0)); // Initial choice
	        	if(scheduler.saveAppointment(scheduler.makeAppointmentFromUserInput(fullName, phoneNumber, date, time, category, activityType))) {
	        		JOptionPane.showMessageDialog(null, "Your appointment has been saved succesfully");
	        	} else {
	        		JOptionPane.showMessageDialog(null, "An appointment with this time has already existed");
	        	}
	        } 	        
	    });	
	    findButton.addActionListener(e -> {
	    	Calendar cal = Scheduler.makeCalendarFromUserInput(false, clientDateInput.getText(), clientTimeInput.getText());	
	    	Appointment apt = scheduler.findAppointment(cal);
	    	if (apt != null) {
	    		   JOptionPane.showMessageDialog(null, apt.toString());
	        } else {
	        	JOptionPane.showMessageDialog(null, "Cannot find appointment");
	        } 
	    	
	    });
	    	
	    changeButton.addActionListener(e -> {
	    	Calendar cal = Scheduler.makeCalendarFromUserInput(false, clientDateInput.getText(), clientTimeInput.getText());	
	    	Appointment apt = scheduler.findAppointment(cal);
	    	if (apt != null) {
	    		   
	    		   int change = JOptionPane.showConfirmDialog(null, "Would you like to change this appointment?", "Please confirm", 
	   	    			JOptionPane.YES_NO_OPTION);
	   	        if (change == JOptionPane.YES_OPTION) {
	   	        	ChangeAppointmentDialog.showChangeAppointmentDialog(scheduler, apt);
	   	        } 
	   	        
	        } else {
	        	JOptionPane.showMessageDialog(null, "Cannot find appointment");
	        }

	    });
	    	
	    deleteButton.addActionListener(e -> {
	    	Calendar cal = Scheduler.makeCalendarFromUserInput(false, clientDateInput.getText(), clientTimeInput.getText());	
	    	Appointment apt = scheduler.findAppointment(cal);
	    	if (apt != null) {
	    		   
	    		   int delete = JOptionPane.showConfirmDialog(null, "Would you like to delete this appointment?", "Please confirm", 
	   	    			JOptionPane.YES_NO_OPTION);
	   	        if (delete == JOptionPane.YES_OPTION) {
//	   	        	DeleteAppointmentDialog.showChangeAppointmentDialog(scheduler, apt);
	   	        	scheduler.deleteAppointment(cal);
	   	        	JOptionPane.showMessageDialog(null, "Your appointment has been deleted");
	   				f.dispose();
	   	        } 

	        }

	    });
	    	
	    exitButton.addActionListener(e -> {
	    	f.dispose();
    	});
	    	
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}
//	private static JComboBox<String> createTypeCombobox() {
//		
//		return null;
//	}

	  
}
