package in.ac.adit.pwj.miniproject.employees.models;

import java.io.Serializable;

public abstract class Employee implements Serializable {
    private String name;
    private String id;
    protected double salary;
    
    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
    public abstract double calculateSalary();
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return "Employee[id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
}
