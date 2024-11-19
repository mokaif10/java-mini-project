# java-mini-project
 Employee Management System with Java

# Employee Management System

This project implements an **Employee Management System** using Object-Oriented Programming (OOP) in Java. The system includes an abstract `Employee` class hierarchy with `FullTimeEmployee` and `PartTimeEmployee` as derived classes. It also demonstrates advanced features like inner classes, thread-safe operations using locks, file I/O, and exception handling.

## Features

- Add and manage different types of employees (full-time and part-time)
- Calculate salaries based on employee type
- Thread-safe operations for concurrent access
- Persistent storage using file I/O
- Handle invalid salary scenarios using custom exceptions
- Calculate individual and total payroll

## Class Structure

### 1. Employee Class (Abstract)

The base `Employee` class provides the foundation for different types of employees.

#### Members
- `String name`: Name of the employee
- `String id`: Unique identifier for the employee
- `double salary`: Base salary of the employee

#### Methods
- `abstract double calculateSalary()`: Calculate the total salary
- Getters and setters for all properties
- `toString()`: String representation of employee data

### 2. FullTimeEmployee Class

Extends `Employee` to handle full-time employees with fixed salary and bonus.

#### Members
- `double bonus`: Bonus amount for the employee

#### Methods
- `setBonus(double bonus)`: Set bonus amount
- `calculateSalary()`: Calculate total salary including bonus

### 3. PartTimeEmployee Class

Extends `Employee` to handle part-time employees with hourly wages.

#### Members
- `int hoursWorked`: Number of hours worked
- `double hourlyRate`: Pay rate per hour

#### Methods
- `setHoursWorked(int hours)`: Update worked hours
- `calculateSalary()`: Calculate salary based on hours and rate

### 4. EmployeeManager Class

Manages the collection of employees and handles operations.

#### Members
- `Map<String, Employee> employees`: Storage for employee records
- `Set<String> employeeIds`: Set of unique employee IDs
- `ReentrantLock lock`: Thread synchronization lock
- `String DATA_FILE`: File for persistent storage

#### Inner Class: PayrollCalculator
- `calculateTotalPayroll()`: Calculate total payroll for all employees
- `getIndividualPayrolls()`: Get individual salary details

#### Methods
- `addEmployee(Employee emp)`: Add new employee
- `updateEmployee(String id, String field, Object value)`: Update employee details
- `deleteEmployee(String id)`: Remove employee
- `getEmployee(String id)`: Retrieve specific employee
- `getAllEmployees()`: Get list of all employees
- File operations: `saveEmployees()` and `loadEmployees()`

### 5. InvalidSalaryException Class

Custom exception for handling invalid salary scenarios.

#### Methods
- `InvalidSalaryException(String message)`: Constructor with error message

## Application of OOP Principles

1. **Abstraction**:
   - Abstract `Employee` class defines common structure
   - Different employee types implement specific salary calculations

2. **Inheritance**:
   - `FullTimeEmployee` and `PartTimeEmployee` extend `Employee`
   - Common attributes and methods are inherited

3. **Encapsulation**:
   - Private fields with public getters/setters
   - Internal implementations hidden from external access

4. **Polymorphism**:
   - `calculateSalary()` method implemented differently for each employee type
   - Common interface for different employee types

## Technical Features

1. **Thread Safety**:
   - ReentrantLock ensures thread-safe operations
   - Synchronized access to shared resources

2. **File I/O**:
   - Persistent storage using object serialization
   - Automatic data loading and saving

3. **Exception Handling**:
   - Custom exceptions for invalid salaries
   - Proper error handling and reporting

## Usage Example

```java
EmployeeManager manager = new EmployeeManager();

// Add full-time employee
Employee john = new FullTimeEmployee("John Doe", "EMP001", 50000);
manager.addEmployee(john);

// Add part-time employee
PartTimeEmployee jane = new PartTimeEmployee("Jane Smith", "EMP002", 20);
jane.setHoursWorked(80);
manager.addEmployee(jane);

// Calculate payroll
EmployeeManager.PayrollCalculator calculator = manager.getPayrollCalculator();
System.out.println("Total Payroll: $" + calculator.calculateTotalPayroll());
```

## Future Enhancements

- Database integration for better data persistence
- GUI implementation for user-friendly interaction
- Additional employee types (contractors, interns)
- Tax calculation and payroll reporting
- Employee attendance tracking
- Leave management system
- Performance evaluation system
