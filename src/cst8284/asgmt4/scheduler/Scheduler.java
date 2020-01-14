/*
 Course name: CST8284-310
 Student Name: Diep Pham
 Class name: Scheduler
 Date: 27-10-2019
*/

package cst8284.asgmt4.scheduler;

import java.util.Scanner;
import cst8284.asgmt4.employee.Employee;
import cst8284.asgmt4.scheduler.Appointment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/** 
 * This class Scheduler helps user input scheduling appointment for clients
 * @author Diep Pham
 * @version 1.0
 */
public class Scheduler {
	
	/** Object Scanner helps user input data 
	 */
	private static Scanner scan = new Scanner(System.in);
	
	/** This is an ArrayList that stores all appointments
	 */
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	
	/** This variable is declared employee of the Dental office 
	 */
	private Employee employee;
	
	/** This is the option Save appointment for the Menu 
	 */
	private static final int SAVE_APPOINTMENT = 1;
	/** This is the option Delete appointment for the Menu 
	 */
	private static final int DELETE_APPOINTMENT = 2;
	/** This is the option Change appointment for the Menu  
	 */
	private static final int CHANGE_APPOINTMENT = 3;
	/** This is the option Display appointment for the Menu  
	 */
	private static final int DISPLAY_APPOINTMENT = 4;
	/** This is the option Display schedule for the Menu  
	 */
	private static final int DISPLAY_SCHEDULE = 5;
	/** This is the option Save appointment to file for the Menu  
	 */
	private static final int SAVE_APPOINTMENT_TO_FILE = 6;
	/** This is the option Load appointment from file for the Menu 
	 */
	private static final int LOAD_APPOINTMENT_FROM_FILE = 7;
	/** This is the option Exit the program
	*/
	private static final int EXIT = 0;
	
	/** This constructor for Scheduler()
	 * @param emp This argument contains the employee for this Scheduler object
	 */
	public Scheduler(Employee emp) {
		setEmployee(emp);
	}
	
	/** Launch program is to launch the program and catch exception if user inputs wrong format information, 
	 * then it will ask user to input data from client.
	*/
	public void launch() {
		int choice = 0;
		do {
		   choice = displayMenu();
		   try {
			   executeMenuItem(choice);
		   } catch (BadAppointmentDataException ex) {
			   
			   System.err.println("Description: " + ex.getMessage() + ex.getDescription());
//			   ex.printStackTrace();
		   }
		} while (choice != EXIT);		
	}
	
	/** This is setter method setEmployee() to set name of Employee
	 * @param emp This argument is to set name of employee 
	 */
	private void setEmployee(Employee emp) {
		this.employee = emp;
		System.out.println("Scheduling appointment for " + getEmployee());
	}
	/** This is getter method getEmployee() to get name of Employee
	 * @return This argument is to get name of employee 
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/** This is method displayMenu() to display menu of program
	 * @return Return option of the menu
	 */
	private int displayMenu() {
		System.out.println("Enter a selection from the following menu:");
		System.out.println(
			SAVE_APPOINTMENT + ". Save appointment\n" +
			DELETE_APPOINTMENT + ". Remove appointment\n" + 
			CHANGE_APPOINTMENT + ". Change appointment\n" +		
			DISPLAY_APPOINTMENT  + ". Get appointment\n" +
			DISPLAY_SCHEDULE + ". Display schedule\n" +
			SAVE_APPOINTMENT_TO_FILE + ". Backup appointment to file\n" +
			LOAD_APPOINTMENT_FROM_FILE + ". Load appointment from file\n" +
			EXIT + ". Exit program");
		int ch = scan.nextInt();
		scan.nextLine();  // 'eat' the next line in the buffer
		System.out.println();
		return ch;
	}
	
	/** Void method executeMenuItem() is to execute the menu (no return)
	 * @param choice This parameter is for the option that the user can choose from the menu
	 */
	private void executeMenuItem(int choice) {
		switch (choice) {
//			case SAVE_APPOINTMENT: 
//				saveAppointment(makeAppointmentFromUserInput());//change from saveAppointmentToArray to saveAppointment
//				break;
//			case DELETE_APPOINTMENT: 
//				deleteAppointment(makeCalendarFromUserInput(false));
//				break;	
//			case CHANGE_APPOINTMENT: 
//				changeAppointment(makeCalendarFromUserInput(false));
//				break;
//			case DISPLAY_APPOINTMENT: 
//				displayAppointment(makeCalendarFromUserInput(false));
//				break;
//			case DISPLAY_SCHEDULE: 
//				displayDaySchedule(makeCalendarFromUserInput(true)); 
//				break;
			case SAVE_APPOINTMENT_TO_FILE: 
				String savePath="CurrentAppointments.apts";
				saveAppointmentsToFile(getAppointments(),savePath);//save Appointment To file
				break;	
			case LOAD_APPOINTMENT_FROM_FILE: 
				String loadPath="CurrentAppointments.apts";
 				loadAppointmentsFromFile(loadPath,getAppointments());//load Appointment from file
				break;	
			case EXIT: 
				System.out.println("Exiting Scheduler\n\n"); 
				break;
			default: System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();  // add blank line after each output
	}
	
    /** This is getter method getResponseTo() to get input from user
     * @param s prompt introduction
     * @return Return one empty line
     */
    private static String getResponseTo(String s) {
    	System.out.print(s);
		return(scan.nextLine());
    }
	
    /** This method makeAppointmentFromUserInput() is to make appointment from user input
     * It will throw exception from user input about name of client if full name is not correct format
     * @return Return information of client (name, phone, date and activity)
     */
    public Appointment makeAppointmentFromUserInput(String fullName, String phoneNumber, String date, String time, String category, String activityType) {
//    	String fullName = getResponseTo("Enter Client Name (as FirstName LastName): ");
    	
    	if(fullName==null ||fullName.isEmpty() || fullName.trim().split(" ").length < 2) {
        	throw new BadAppointmentDataException("Empty or null value entered",". Must enter a value");
		}
  
		if (!fullName.trim().split(" ")[0].matches("([A-Za-z]|-|\\.|')+") || 
				!fullName.trim().split(" ")[1].matches("([A-Za-z]|-|\\.|')+")) {//https://www.javatpoint.com/java-regex
			throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')", ". Illegal characters in name");	
		}
			
		        
		if (fullName.trim().length() > 30 ) {
			throw new BadAppointmentDataException("\tName cannot exceed 30 character", "\n\t\tName exceeds maximum length");	
		}
    	
//		String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");
		TelephoneNumber phone = new TelephoneNumber(phoneNumber);
		Calendar cal = makeCalendarFromUserInput(false, date, time);
//		String activity = getResponseTo("Enter Activity: ");
		Activity act = new Activity(category, activityType);
//		Activity act = new Activity(activity, "Dentist");
		return (new Appointment(cal, fullName, phone, act));
    }
    
    /** Method makeCalendarFromUserInput that user input to make an appointment for client
     * Throw BadAppointmentDataException if date format is not correct (e.g 31042019,20132019, 22-13-2019,...) or
     *  date is not entered (or null) by user
     * @param suppressHour This argument describes the date that user input
     * @return cal Return date that contains DD MM YYYY from user input
     */
    /**
     * @param suppressHour
     * @param date
     * @param time
     * @return
     */
    public static Calendar makeCalendarFromUserInput(boolean suppressHour, String date, String time) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
    	Calendar cal = Calendar.getInstance();
    	int hour = 0;
    	   	
    	cal.clear();
//		String date = getResponseTo("Appointment Date (entered as DDMMYYYY): ");
		
		if (date.isEmpty() || date == null) {
			throw new BadAppointmentDataException("Must enter a value", ". Empty or null value entered");
		}
		
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
		} catch(ParseException e) {
//			e.printStackTrace();
			 System.out.println("Description: " + e.getMessage());
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYY", ". Bad calendar format");
		}
				
		int day = Integer.parseInt(date.substring(0,2));
		int month = Integer.parseInt(date.substring(2,4))-1;  // offset by one to account for zero-based month in Calendar
		int year = Integer.parseInt(date.substring(4,8));
		
		if (!suppressHour) {				
//		   String time = getResponseTo("Appointment Time: ");
		   hour = processTimeString(time);
		}
		cal.set(year, month, day, hour, 0);
		return (cal);
    }
    
	/** This method processTimeString() is to process time input from user
	 * @param t This is original time string
	 * @return Return time input
	 */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains (" ")) hour = Integer.parseInt(t.split(" ")[0]);
		else hour = Integer.parseInt(t);
		return ((hour < 8) ? hour+12 : hour);
	}
	
    /** To organize the Appointments by date, we first need to implement a Comparator, 
     * so begin by creating a new class SortAppointmentByCalendar 
     * that implements the Comparator interface
     * @param cal This argument contains date and time for an appointment to be looked for
     * @return Appointment that matches calendar inside parameter. 
     */
    public Appointment findAppointment(Calendar cal) {

    	Collections.sort(getAppointments(), new SortAppointmentByCalendar());
    	int idx = Collections.binarySearch(getAppointments(), new Appointment(cal), new SortAppointmentByCalendar());
    	if(idx >= 0) {
    		return getAppointments().get(idx);    		
    	}
    	return null;
    }
  
	/** This method saveAppointment() saves appointment for client from user 
	 * @param apt This argument describes the appointment for this appointment to be saved
	 * @return Return true or false (yes or no if user needs to save appointment)
	 */
	public boolean saveAppointment(Appointment apt) {
		Calendar cal = apt.getCalendar();  // Check that the appointment does not already exist
		if (findAppointment(cal)==null) {  // Time slot available, okay to add appointment
			getAppointments().add(apt);
			Collections.sort(getAppointments(), new SortAppointmentByCalendar());
			System.out.println("\nAppointment saved.");  
			return true;
		}  // else time slot taken, need to make another choice
		System.out.println("Cannot save; an appointment at that time already exists");
		return false;
	}
	
	/** This method deleteAppointment() deletes appointment for client from user 
	 * @param cal This argument contains date and time for an appointment to be deleted
	 * @return Return true or false (yes or no if user needs to delete appointment)
	 */
	public boolean deleteAppointment(Calendar cal) {
		if (findAppointment(cal)!=null){
//			System.out.print(findAppointment(cal).toString());
//			System.out.print("\nEnter 'Yes' to delete this appointment ");
//			String yes = scan.nextLine();
//			if(yes.contains(new String("Yes")))
			getAppointments().remove(findAppointment(cal));
//			System.out.println("Appointment deleted.");
			return true;
		}
		return false;
	}
	/** This method changeAppointment() changes appointment for client from user
	 * @param cal This argument contains date and time for an appointment to be changed
	 * @return true or false (yes or no if user needs to change appointment)
	 */
	private boolean changeAppointment(Calendar cal) {
//		if (findAppointment(cal)!=null){
//			System.out.print(findAppointment(cal).toString());
//			System.out.print("\nEnter 'Yes' to change the date and time of this appointment ");
//			String yes = scan.nextLine();
//			if(yes.contains(new String("Yes")))
//				System.out.println("Enter new date and time");	
//				findAppointment(cal).setCalendar(makeCalendarFromUserInput(false));
//			System.out.println("Appointment re-booked.");
//			return true;
//		}
		return false;
	}

	/** This method displayAppointment() displays no appointment time for client from time to time, 
	 * no return as void method
	 * @param cal This argument displays date and time for an appointment
	 */
	private String displayAppointment(Calendar cal) {		
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		return (apt!=null) ?
		   "\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
  	       "No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00";
	}
	
	/** This method displayDaySchedule() displays appointment time for client from user, 
	 * no return as void method
	 * @param cal This argument displays day schedule about date and time for an appointment 
	 */
	public String displayDaySchedule(Calendar cal) {
		String str = new String();
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			str += displayAppointment(cal) + "\n";		
		}
		
		return str;
	}
	
	/** This method saveAppointmentsToFile() saves appointment for client to file from user, 
	 * @param apts This argument describes the appointments that need to be saved
	 * @param saveFile This is the file to save all the appointments
	 * @return Return true or false (appointment is saved successfully)
	 */
	public static boolean saveAppointmentsToFile (ArrayList<Appointment> apts, String saveFile ){
		try {
		File file = new File(saveFile);
		FileOutputStream save1 = new FileOutputStream(file); //from ArrayList platform
		ObjectOutputStream oos = new ObjectOutputStream(save1);
		oos.writeObject(apts);
		oos.close();
//		System.out.println("Appointment data saved to CurrentAppointments.apts"); 
		return true;

		}catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Appointment saved");
		}
		return false;
	}
	
	/** This method loadAppointmentsFromFile() loads appointment for client from file from user
	 * @param sourceFile This is the source file that all appointments are loaded from 
	 * @param apts This argument describes the appointment that needs to be loaded
	 * @return true or false (yes or no if the appointment loaded successfully)
	 */
	@SuppressWarnings("unchecked")
	public boolean loadAppointmentsFromFile (String sourceFile, ArrayList<Appointment> apts) {//from ArrayList platform
		try {
		File file = new File(sourceFile);
		FileInputStream save1 = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(save1);
		apts.clear();
		apts.addAll((ArrayList<Appointment>) ois.readObject());
		ois.close();
		System.out.println("Appointment successfully loaded from " + sourceFile); 

		}catch (IOException ex) {
			System.out.println("Appointment saved");
			ex.printStackTrace();
		}catch (ClassNotFoundException ex) {
			System.out.println("File not correct");
		}
		return false;
	}
		 
	/** ArrayList of appointment is to add appointments into ArrayList
	 * @return appointments Return appointments from the this Scheduler object
	 */
	public ArrayList<Appointment> getAppointments() {return appointments;}
}
