package idroidemployees;

//
//Assignment 2
//Written by:	Raouf Ouibrahim
//

/*
This program processes employee payroll data from a file and calculates the 
yearly gross salary, net salary, and tax deductions for every employee. The
invalid data is written in its own file and the valid data is structured in a report file.
*/

public class MinimumWageException extends Exception {
	 
		public MinimumWageException() {
	        super("The entered hourly wage is below the minimum wage."); 
	    }

	    public MinimumWageException(String message) {
	        super(message); 
	    }
}
