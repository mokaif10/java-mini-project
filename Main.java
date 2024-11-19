package in.ac.adit.pwj.miniproject.employees;

import in.ac.adit.pwj.miniproject.employees.models.*;
import in.ac.adit.pwj.miniproject.employees.management.*;
import in.ac.adit.pwj.miniproject.employees.exceptions.*;

public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        
        try {
            // Add full-time employee
            Employee john = new FullTimeEmployee("John Doe", "EMP001", 50000);
            manager.addEmployee(john);
            
            // Add part-time employee
            PartTimeEmployee jane = new PartTimeEmployee("Jane Smith", "EMP002", 20);
            jane.setHoursWorked(80);
            manager.addEmployee(jane);
            
            // Update employee
            manager.updateEmployee("EMP001", "salary", 55000.0);
            
            // Calculate payroll
            EmployeeManager.PayrollCalculator calculator = manager.getPayrollCalculator();
            System.out.println("Total Payroll: $" + calculator.calculateTotalPayroll());
            
            // Print all employees
            System.out.println("\nAll Employees:");
            for (Employee emp : manager.getAllEmployees()) {
                System.out.println(emp);
            }
            
        } catch (InvalidSalaryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
