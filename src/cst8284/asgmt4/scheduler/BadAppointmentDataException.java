/*
 	Course name	: CST8284-310
 	Student Name: Diep Pham
	Class name	: SchedulerLauncher
	Date		: 25-11-2019
	*/

package cst8284.asgmt4.scheduler;

/** This class BadAppointmentDataException will help the user avoid to get wrong data input
 * @author Diep Pham
 * @version 1.0
 */
public class BadAppointmentDataException extends java.lang.RuntimeException {

	/** This field contains default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** This variable is declared description for BadAppointmentDataException
	*/
	private String description;
	
	/** The default constructor BadAppointmentDataException will take the String "Please try again. Bad data entered"
	 * to pass it to super class constructor.
	 */
	public BadAppointmentDataException() {
		super("Please try again. Bad data entered");
	}
		
	/** This constructor BadAppointmentDataException with a 2 String construction
	 * @param s Use super() to pass the first String to the superclass
	 * @param description The second String is stored to a private String description 
	 */
	public BadAppointmentDataException(String s, String description) {
		super(s);
		setDescription(description);
	}
	
	/** Setter method for description of BadAppointmentDataException method and no return.
	 * @param description This argument is to describe the description of BadAppointmentDataException
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/** This is getter method for description of BadAppointmentDataException method
	 * @return description Return description of BadAppointmentDataException method
	 */
	public String getDescription() {
		return description;
	}
	
}
