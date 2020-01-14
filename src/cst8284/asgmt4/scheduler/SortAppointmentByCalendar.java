package cst8284.asgmt4.scheduler;

/*
	Course name	: CST8284-310
	Student Name: Diep Pham
	Class name	: SchedulerLauncher
	Date		: 25-11-2019
*/

import java.util.Comparator;

/** class SortAppointmentByCalendar implements interface Comparator for generic Appointment
 * @author Diep Pham 
 * @version 1.0
 */
public class SortAppointmentByCalendar implements Comparator<Appointment>  {

	/** the compare() method and it returns an integer value representing 
	 * the difference between the Calendar’s of the two Appointment’s input 
	 * as parameters to the compare() method
	 */
	public int compare(Appointment appt1, Appointment appt2) {
		return appt1.getCalendar().compareTo(appt2.getCalendar());
	} 
	
	
}
	
	
	

