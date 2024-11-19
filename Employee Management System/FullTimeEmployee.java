// package in.ac.adit.pwj.miniproject.employees.models.src.in.ac.adit.pwj.miniproject.employees.models;

// public class FullTimeEmployee {
    
// }

package in.ac.adit.pwj.miniproject.employees.models;

public class FullTimeEmployee extends Employee {
    private double bonus;
    
    public FullTimeEmployee(String name, String id, double salary) {
        super(name, id, salary);
        this.bonus = 0;
    }
    
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    
    @Override
    public double calculateSalary() {
        return salary + bonus;
    }
}

