/*
 	Course name: CST8284-310
 	Student Name: Diep Pham
	Class name: TelephoneNumber
	Date: 27-10-2019
*/

package cst8284.asgmt4.scheduler;

import java.io.Serializable;


/** Class TelephoneNumber implements interface Serializable 
 * @author Diep Pham
 * @version 1.0
 */
public class TelephoneNumber implements Serializable {
	
	/** This field contains default serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	/** These variables are declared for this TelephonNumber object 
	 */
	private int areaCode, lineNumber, prefix;
	
	/** The constructor TelephoneNumber will split areaCode, prefix and lineNumber for the user to 
	 * input phone number of client in correct format
	 * Throw exception if condition is not correct format (e.g.613-55-1212, or 613555-1212, or 013-555-1212,...)
	 * @param phoneNumber This argument is the phone number of client
	 */
	public TelephoneNumber(String phoneNumber) {
		if(phoneNumber.isEmpty()) {
			throw new BadAppointmentDataException("Input can not be null, must enter a value","\nEmpty or null value entered.");
		}
		if (!phoneNumber.matches("([0-9]|-)+")){//https://www.javatpoint.com/java-regex
			throw new BadAppointmentDataException("\tTelephone numbers can only contain numbers or the character ‘-‘", "\n\t\tBad character(s) in input string");
		}
		if (phoneNumber.split("-").length != 3) {
			throw new BadAppointmentDataException("\tMissing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number", 
					"\n\t\tIncorrect format");	
		}
		int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
		int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
		int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
		setAreaCode(areaCode); setPrefix(prefix); setLineNumber(lineNumber);
			
		if (phoneNumber.split("-")[0].trim().length()!=3  || 
				(prefix/100 < 1 || prefix/100 >10)|| (lineNumber/100 < 10 || lineNumber/100>100)) {
			throw new BadAppointmentDataException("\tMissing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number", "\n \t\tIncorrect format");	
		}
			
		if ((String.valueOf(areaCode).charAt(0) == '0') 
				||(String.valueOf(areaCode).charAt(0) == '1')) {

			throw new BadAppointmentDataException("Area code can’t start with a '0' or a '1'", ". Invalid number");
		}
	}
	
	/** This getter method getAreaCode is to get area code
	 * @return areaCode Return the area code of phone number from client
	 */
	public int getAreaCode() {return areaCode;}
	
	/** This setter method setAreaCode is to set area code phone number, no return as void method
	 * @param areaCode This argument is to set the area code of phone number from client
	 */
	public void setAreaCode(int areaCode) {this.areaCode = areaCode;}
	
	/** This getter method getPrefix is to get prefix phone number
	 * @return prefix Return prefix of phone number from client
	 */
	public int getPrefix() { return prefix;}
	
	/** This setter method setPrefix to set prefix phone number, no return as void method
	 * @param prefix This argument is to set the prefix of phone number from client
	 */
	public void setPrefix(int prefix) {this.prefix = prefix;}
	
	/** This getter method getLineNumber is to get line phone number
	 * @return getLineNumber Return line number of phone number from client
	 */
	public int getLineNumber() {return lineNumber;}
	
	/** This setter method setLineNumber is to set line phone number, no return as void method
	 * @param lineNumber This argument is to set the line number of phone number from client
	 */
	public void setLineNumber(int lineNumber) {this.lineNumber = lineNumber;}
	
	/** This method toString() to print out the full phone number from user input
	*/
	public String toString() {return "(" + getAreaCode() +") "+ getPrefix() + "-" + getLineNumber();}
}
