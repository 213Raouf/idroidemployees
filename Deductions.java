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

abstract public class Deductions {
	abstract public double calculateTax(double salary);
}

class ProvincialIncomeTax extends Deductions{
	public double calculateTax(double salary) {
		double deductions =0;
		if (salary > 129590) {
            deductions += (salary - 129590) * 0.2575;
            salary = 129590;
        }
        if (salary > 106495) {
            deductions += (salary - 106495) * 0.24;
            salary = 106495;
        }
        if (salary > 53255) {
            deductions += (salary - 53255) * 0.19;
            salary = 53255;
        }
        if (salary > 18571) {
            deductions += (salary - 18571) * 0.14;
        }
		return deductions;
	}
}
class FederalIncomeTax extends Deductions{
	public double calculateTax(double salary) {
		double deductions =0;
		if (salary > 253414) {
            deductions += (salary - 253414) * 0.33;
            salary = 253414;
        }
        if (salary > 177882) {
            deductions += (salary - 177882) * 0.29;
            salary = 177882;
        }
        if (salary > 114750) {
            deductions += (salary - 114750) * 0.26;
            salary = 114750;
        }
        if (salary > 57375) {
            deductions += (salary - 57375) * 0.205;
            salary = 57375;
        }
        if (salary > 16129) {
            deductions += (salary - 16129) * 0.15;
		}
		return deductions;
	}
}
class QPP extends Deductions{
	public double calculateTax(double salary) {
		if(salary<71300)
			return salary*0.108;
		else
			return 7700.40;
	}
}
class EI extends Deductions{
	public double calculateTax(double salary) {
		if(salary<65700)
			return salary*0.0164;
		else
			return 1077.48;
	}
}
class QPIP extends Deductions{
	public double calculateTax(double salary) {
		if(salary<98000)
			return salary*0.00494;
		else
			return 484.12;
	}
}