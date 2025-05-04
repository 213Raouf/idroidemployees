package idroidemployees;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import idroidemployees.MinimumWageException;

//
//Assignment 2
//Written by:	Raouf Ouibrahim
//

/*
This program processes employee payroll data from a file and calculates the 
yearly gross salary, net salary, and tax deductions for every employee. The
invalid data is written in its own file and the valid data is structured in a report file.
*/


public class Driver {

	public static void main(String[] args) {
		
        System.out.println("Welcome to the idroidemployees tool written by Raouf Ouibrahim!");

					
		Scanner readFile=null;
		PrintWriter writeValidFile = null;
		PrintWriter writeInvalidFile = null;
		Scanner readValidFile=null;
		Scanner readInvalidFile=null;

		try {
			System.out.println(">Opening file payroll");
			readFile = new Scanner(new FileInputStream("payroll.txt"));

		}
		catch(FileNotFoundException e){
			System.out.println("The file couldn't be openned. The program is going to terminate.");
			System.exit(0);
		}
		
		int linesCount = 0;
		int invalidCount =0;
		
		while(readFile.hasNextLine()) {
			readFile.nextLine();
			linesCount++;
		}
		readFile.close();

		try {
		    readFile = new Scanner(new FileInputStream("payroll.txt"));
		} catch (FileNotFoundException e) {
		    System.out.println("The file couldn't be opened. The program is going to terminate.");
		    System.exit(0);
		}
		
		
		Employee[] validEmployees = new Employee[linesCount];
		int validEmployeesCount = 0;
		
		try {
			writeValidFile = new PrintWriter(new FileOutputStream("validEmployees.txt"));
			writeInvalidFile = new PrintWriter(new FileOutputStream("invalidEmployees.txt"));
		}
		
			catch(FileNotFoundException e){
				System.out.println("The file couldn't be openned. The program is going to terminate.");
				System.exit(0);
		}


		System.out.println(">Reading file payroll");
        for (int i = 0; i < linesCount; i++) {
            String line = readFile.nextLine();
            Scanner readLine = new Scanner(line);

            try {
                long employeeNumber = readLine.nextLong();
                String firstName = readLine.next();
                String lastName = readLine.next();
                double hoursWorked = readLine.nextDouble();
                double hourlyWage = readLine.nextDouble();

                Employee newEmployee = new Employee(employeeNumber, firstName, lastName, hourlyWage,hoursWorked);
                validEmployees[validEmployeesCount++] = newEmployee;
            } catch (InputMismatchException e) {
                writeInvalidFile.println(line); 
                invalidCount++;
            } catch (MinimumWageException e) {
                writeInvalidFile.println(line);
                invalidCount++;
            } finally {
                readLine.close();
            }
        }

        writeInvalidFile.close();
        readFile.close();
		
		try {
			readInvalidFile = new Scanner(new FileInputStream("invalidEmployees.txt"));
		}
			catch(FileNotFoundException e){
				System.out.println("The file couldn't be openned. The program is going to terminate.");
				System.exit(0);
		}
		System.out.println(">Error lines found in file payroll\n");
		for(int i=0;i<invalidCount;i++) {
			System.out.println(readInvalidFile.nextLine());
			
		}
		System.out.println();

		System.out.println(">"+ linesCount +" lines read from file payroll");
		System.out.println(">"+ invalidCount +"  lines written to error file");
		System.out.println(">Calculating deductions");
		System.out.println(">Writing report file");
		
		try {
			readValidFile = new Scanner(new FileInputStream("validEmployees.txt"));
		}
			catch(FileNotFoundException e){
				System.out.println("The file couldn't be openned. The program is going to terminate.");
				System.exit(0);
		}
		System.out.printf("\n\t                 iDroid Solutions                 \n" +
    			"\t                 ----------------                  \n" +
    			"%-15s %-12s %-13s %-13s %-11s %-10s\n" +
    			"---------------------------------------------------------------------------------\n",
    			"Employee Number", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary"); 
         
        writeValidFile.printf("\n\t                 iDroid Solutions                 \n" +
    			"\t                 ----------------                  \n" +
    			"%-15s %-12s %-13s %-13s %-11s %-10s\n" +
    			"--------------------------------------------------------------------------------\n",
    			"Employee Number", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary"); 
            
        for (int i = 0; i < linesCount - invalidCount - 1; i++) {
            System.out.printf(
                "%-15s %-12s %-13s $%-13.2f $%-11.2f $%-10.2f\n"
            		+"---------------------------------------------------------------------------------\n",
                validEmployees[i].getEmployeeNumber(),
                validEmployees[i].getFirstName(),
                validEmployees[i].getLastName(),
                validEmployees[i].getYearlyWage(),
                validEmployees[i].getDeductions(),
                validEmployees[i].getNetSalary()
            );
        }
        for (int i = 0; i < linesCount - invalidCount - 1; i++) {
            writeValidFile.printf(
                "%-15s %-12s %-13s $%-13.2f $%-11.2f $%-10.2f\n"
            		+"--------------------------------------------------------------------------------\n",
                validEmployees[i].getEmployeeNumber(),
                validEmployees[i].getFirstName(),
                validEmployees[i].getLastName(),
                validEmployees[i].getYearlyWage(),
                validEmployees[i].getDeductions(),
                validEmployees[i].getNetSalary()
            );
        }	
        writeValidFile.close();
        
        System.out.println("Goodbye, thank you for using idroidemployees tool!");
        
    
	}

}
