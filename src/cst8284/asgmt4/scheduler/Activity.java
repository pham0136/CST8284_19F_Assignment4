/*
 	Course name: CST8284-310
 	Student Name: Diep Pham
	Class name: Activity
	Date: 27-10-2019
*/
package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/** This class Activity for user input activity for clients
 * @author Diep Pham
 * @version 1.0
 */
public class Activity implements Serializable {
			

	/** This field contains default serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	/** This variable is for description of Activity
	 */
	private String descriptionOfWork;
	
	/** This field is for category of Activity  
	 */
	private String category;

	/** This is constructor for Activity
	 * @param description The description is for this activity
	 * @param category: These activities which categories belong to
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}

	/**
	 * This is getter method for description of work
	 * 
	 * @return Return the activity description
	 */
	public String getDescription() {
		return descriptionOfWork;
	}
	
	/**
	 * This is setter method for description of work
	 * void doesn't need to return anything
	 * @param description This argument is to describe the description of work 
	 */
	public void setDescription(String description) {
		this.descriptionOfWork = description;
	}

	/**
	 * This is getter method for category
	 * @return Return the category of the activity type
	 */
	public String getCategory() {
		return category;
	}

	/** This is Setter method for category
	 * @param category This argument is to set category of activity
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/** Return String representation of this object (Override superclass method)
	 */
	public String toString() {
		return getCategory() + "\n" + getDescription();
	}
}
