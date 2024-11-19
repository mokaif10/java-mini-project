package in.ac.adit.pwj.miniproject.employees.models;

public class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;
    
    public PartTimeEmployee(String name, String id, double hourlyRate) {
        super(name, id, 0);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = 0;
    }
    
    public void setHoursWorked(int hours) {
        this.hoursWorked = hours;
        this.salary = calculateSalary();
    }
    
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}
