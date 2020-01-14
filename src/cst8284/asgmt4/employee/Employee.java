/*
	Course name: CST8284-310
	Student Name: Diep Pham
	Class name: Employee
	Date: 27-10-2019
*/
package cst8284.asgmt4.employee;

import java.util.ArrayList;

/** This is abstract class Employee that has name and activity of employee
 * @author Diep Pham
 * Version 1.0
 */
public abstract class Employee {
	
/** This field contains full name of employee
 */
private String fullName;

/** This is no arg constructor Employee()
 */
protected Employee() {this("unknown");}

/** This is one arg constructor Employee() that will set full name of employee
 * @param fullName This is full name of employee 
 */
protected Employee(String fullName) {setName(fullName);}


/** This is void method setName to set full name of employee, no return
 * @param fullName This is full name of employee 
 */
public void setName(String fullName) {this.fullName = fullName;}

/** This is get method getName() to get full name of employess
 * @return Return full name of employee
 */
public String getName() {return fullName;}

/** This is abstract method getActivityType() to get the activity of employee
 * @return Return activity type of this employee
 */
public abstract ArrayList<String> getActivityType();

/** Return String representation of this object (Override superclass method)
 */
@Override
public String toString() {return getName();}

}