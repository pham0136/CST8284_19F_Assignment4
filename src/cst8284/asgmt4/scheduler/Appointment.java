/*
 	Course name: CST8284-310
 	Student Name: Diep Pham
	Class name: Appointment
	Date: 27-10-2019
*/

package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** This class Appointment helps user input the appointment for client
 * @author Diep Pham
 * @version 1.0 
 */
public class Appointment implements Serializable {
	
	/** This field contains default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** This variable is declared for appointment date of client
	 */
	private Calendar aptDate;
	
	/** This variable is declared for first name and last name of client
	 */
	private String firstName, lastName;
	
	/** This variable is declared for phone number of client
	 */
	private TelephoneNumber phone;
	
	/** This variable is declared for activity of client
	 */
	private Activity activity;


	/** The constructor for Appointment takes all the objects Calendar, TelephoneNumber and Activity to
	 * set them as fields for this object and full name including first name and last name of clients. 
	 * @param cal This parameter contains date and time for this appointment
	 * @param fullName Full name of client that user inputs for the appointment
	 * @param phone User inputs phone number of client for this appointment
	 * @param act This argument describes activity for client
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity act) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, act);
	}
	
	/** The constructor for Appointment with takes in a Calendar object
	 * and set it to the corresponding field of this object 
	 * @param cal This parameter contains date and time for this appointment
	 */
	public Appointment(Calendar cal) {
		setCalendar(cal);
	}

	/** The constructor for Appointment will take these objects Calendar, TelephoneNumber and Activity to set them
	 * to the corresponding fields of this object and set first name and last name of the client.
	 * Throw exception if the condition firstName and lastName are empty or have special characters or exceed 30 characters
	 * @param firstName First name of client to be input from user for this appointment
	 * @param lastName Last name of client to be input from user for this appointment
	 * @param cal This parameter contains date and time for this appointment
	 * @param phone Input phone number of client for this appointment 
	 * @param act This argument describes activity for client
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity act) {
		setFirstName(firstName.trim());
		setLastName(lastName.trim());
		
		if(firstName.isEmpty()||lastName.isEmpty()) {
        	throw new BadAppointmentDataException("Empty or null value entered",". Must enter a value");
		}
		if (!firstName.trim().matches("([A-Za-z]|-|\\.|')+") || !lastName.trim().matches("([A-Za-z]|-|\\.|')+")) {
			throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')", ". Illegal characters in name");	
		}
		if (firstName.trim().length() > 30 || lastName.trim().length() > 30) {
			throw new BadAppointmentDataException("\tName cannot exceed 30 character", "\t\tName exceeds maximum length");	
		}
		setCalendar(cal);
		setPhone(phone);
		setActivity(act);
	}

	/** This getter method is getCalendar() to get date and time of the appointment
	 * @return Return appointment date
	 */
	public Calendar getCalendar() {
		return aptDate;
	}

	/** This setter method is setCalendar (no return) to set date and time of the appointment
	 * @param aptDate This argument describes the date for this appointment
	 */
	public void setCalendar(Calendar aptDate) {
		this.aptDate = aptDate;
	}

	/** This getter method is getFirstName() to change first name of client
	 * @return first name of client
	 */
	public String getFirstName() {
		return firstName;
	}

	/** This setter method is setFirstName() to set first name (no return)
	 * @param firstName This argument is to set first name of client
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** This getter method is getLastName() to change last name of client
	 * @return lastName Return last name of client who books this appointment
	 */
	public String getLastName() {
		return lastName;
	}

	/** This setter method is setLastName() to set last name (no return)
	 * @param lastName This argument is to set last name of client
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** This getter method is getPhone() to change phone number of client
	 * @return phone Return phone number of client who books this appointment
	 */
	public TelephoneNumber getPhone() {
		return phone;
	}

	/** This setter method is setPhone() to set phone number of client (no return)
	 * @param phone This argument to set phone number of client
	 */
	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	/** This getter method is getActivity() to change activity of client
	 * @return activity Return activity of client that user inputs
	 */
	public Activity getActivity() {
		return activity;
	}

	/** This setter method is setActivity() to set activity of client (no return)
	 * @param activity This argument is to set activity of client 
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/** Return String representation of this object (Override superclass method)
	 */
	public String toString() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");	
			return 	dateFormat.format(getCalendar().getTime()) + "\n" + 
					getFirstName() + " " + getLastName() + "\n" + 
					getPhone().toString() + "\n" + getActivity().toString();
		}
//		return getCalendar().getTime().toString() + "\n" + getFirstName() + " " + getLastName() + "\n"
//				+ getPhone().toString() + "\n" + getActivity().toString();
	}

