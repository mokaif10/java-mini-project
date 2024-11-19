package in.ac.adit.pwj.miniproject.employees.management;

import in.ac.adit.pwj.miniproject.employees.models.*;
import in.ac.adit.pwj.miniproject.employees.exceptions.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class EmployeeManager {
    private Map<String, Employee> employees;
    private Set<String> employeeIds;
    private final ReentrantLock lock;
    private static final String DATA_FILE = "employees.dat";
    
    // Inner class for payroll calculations
    public class PayrollCalculator {
        public double calculateTotalPayroll() {
            double total = 0;
            for (Employee emp : employees.values()) {
                total += emp.calculateSalary();
            }
            return total;
        }
        
        public Map<String, Double> getIndividualPayrolls() {
            Map<String, Double> payrolls = new HashMap<>();
            for (Employee emp : employees.values()) {
                payrolls.put(emp.getId(), emp.calculateSalary());
            }
            return payrolls;
        }
    }
    
    public EmployeeManager() {
        employees = new HashMap<>();
        employeeIds = new HashSet<>();
        lock = new ReentrantLock();
        loadEmployees();
    }
    
    public void addEmployee(Employee emp) throws InvalidSalaryException {
        if (emp.getSalary() < 0) {
            throw new InvalidSalaryException("Salary cannot be negative");
        }
        
        lock.lock();
        try {
            if (!employeeIds.contains(emp.getId())) {
                employees.put(emp.getId(), emp);
                employeeIds.add(emp.getId());
                saveEmployees();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void updateEmployee(String id, String field, Object value) throws InvalidSalaryException {
        lock.lock();
        try {
            Employee emp = employees.get(id);
            if (emp != null) {
                switch (field.toLowerCase()) {
                    case "name":
                        emp.setName((String) value);
                        break;
                    case "salary":
                        double newSalary = (Double) value;
                        if (newSalary < 0) {
                            throw new InvalidSalaryException("Salary cannot be negative");
                        }
                        emp.setSalary(newSalary);
                        break;
                }
                saveEmployees();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void deleteEmployee(String id) {
        lock.lock();
        try {
            employees.remove(id);
            employeeIds.remove(id);
            saveEmployees();
        } finally {
            lock.unlock();
        }
    }
    
    public Employee getEmployee(String id) {
        return employees.get(id);
    }
    
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
    
    private void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(new ArrayList<>(employees.values()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void loadEmployees() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            List<Employee> empList = (List<Employee>) ois.readObject();
            for (Employee emp : empList) {
                employees.put(emp.getId(), emp);
                employeeIds.add(emp.getId());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public PayrollCalculator getPayrollCalculator() {
        return new PayrollCalculator();
    }
}

