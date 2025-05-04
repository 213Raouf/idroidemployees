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

public class Employee {
	private long employeeNumber;
	private String firstName;
	private String lastName;
	private double hourlyWage;
	private double hoursWorked;
	private double yearlyWage;
	
	private ProvincialIncomeTax provincialTax;
    private FederalIncomeTax federalTax;
    private QPP qpp;
    private EI ei;
    private QPIP qpip;
	
	public Employee(long employeeNumber,String firstName,String lastName,double hourlyWage, double hoursWorked)
			throws MinimumWageException{
		this.firstName=firstName;
		this.lastName = lastName;
		this.hoursWorked= hoursWorked;
		this.employeeNumber = employeeNumber;
		this.setHourlyWage(hourlyWage);
		
		this.provincialTax = new ProvincialIncomeTax();
	    this.federalTax = new FederalIncomeTax();
	    this.qpp = new QPP();
	    this.ei = new EI();
	    this.qpip = new QPIP();
	}

	
	public Employee(Employee otherEmployee) {
		this.firstName= otherEmployee.firstName;
		this.lastName = otherEmployee.lastName;
		this.hourlyWage = otherEmployee.hourlyWage;
		this.hoursWorked= otherEmployee.hoursWorked;
		this.employeeNumber = otherEmployee.employeeNumber;
	}
	
    // Getters
    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHourlyWage(double hourlyWage) throws MinimumWageException {
    	if(hourlyWage>=15.75) {
            this.hourlyWage = hourlyWage;
    		this.setYearlyWage();
    	}
    	else
    		throw new MinimumWageException();
        
    }

    public void setHoursWorked(double hoursWorked) {
            this.hoursWorked = hoursWorked;
            this.setYearlyWage();
        
    }
	
	public void setYearlyWage() {
		this.yearlyWage= hourlyWage*hoursWorked*52;
	}
	public double getYearlyWage() {
		return this.yearlyWage;
	}
	   

	public double getDeductions() {
    return provincialTax.calculateTax(yearlyWage) +
           federalTax.calculateTax(yearlyWage) +
           qpp.calculateTax(yearlyWage) +
           ei.calculateTax(yearlyWage) +
           qpip.calculateTax(yearlyWage);
	}
	public double getNetSalary() {
		return yearlyWage - getDeductions();
	}
	
}

