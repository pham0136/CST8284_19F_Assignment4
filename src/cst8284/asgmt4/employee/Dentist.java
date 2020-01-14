/*
	Course name: CST8284-310
	Student Name: Diep Pham
	Class name: Dentist
	Date: 27-10-2019
*/

package cst8284.asgmt4.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** This is class Dentist which is inherited from Employee class
 * @author Diep Pham
 * Version 1.0
 */
public class Dentist extends Employee {

	/** This is one arg constructor Dentist
	 * @param fullName This is full name of this dentist
	 */
	public Dentist(String fullName) {
		this.setName(fullName);
	}

	/** This method is to override superclass method to return String of activity type
	*/
	@Override
	public ArrayList<String> getActivityType() {
		ArrayList<String> res = new ArrayList<>();
		res.add("Assessment");
		res.add("Filling");
		res.add("Crown");
		res.add("Cosmetic Repair");
		return res;
	}
}
