package com.interview.ems;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService service = new EmployeeService();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("     EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("====================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees(service.getAllEmployees());
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> viewEmployees(service.sortBySalary());
                case 0 -> {
                    running = false;
                    System.out.println("Thank you for using the system!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\\n1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by Name");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Sort Employees by Salary");
        System.out.println("0. Exit");
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Department: ");
        String department = scanner.nextLine().trim();
        double salary = readDouble("Salary: ");

        if (name.isEmpty() || department.isEmpty() || salary < 0) {
            System.out.println("Please enter valid employee details.");
            return;
        }

        boolean added = service.addEmployee(new Employee(id, name, department, salary));
        System.out.println(added ? "Employee added successfully." : "Employee ID already exists.");
    }

    private static void searchEmployee() {
        System.out.print("Enter name to search: ");
        String keyword = scanner.nextLine().trim();
        viewEmployees(service.searchByName(keyword));
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        System.out.print("New name: ");
        String name = scanner.nextLine().trim();
        System.out.print("New department: ");
        String department = scanner.nextLine().trim();
        double salary = readDouble("New salary: ");

        if (name.isEmpty() || department.isEmpty() || salary < 0) {
            System.out.println("Please enter valid details.");
            return;
        }
        System.out.println(service.updateEmployee(id, name, department, salary)
                ? "Employee updated successfully." : "Employee not found.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");
        System.out.println(service.deleteEmployee(id)
                ? "Employee deleted successfully." : "Employee not found.");
    }

    private static void viewEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.printf("%-6s %-22s %-18s %s%n", "ID", "NAME", "DEPARTMENT", "SALARY");
        System.out.println("---------------------------------------------------------------");
        for (Employee employee : employees) System.out.println(employee);
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.println("Enter a number greater than zero.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(value)) return value;
                System.out.println("Enter a valid salary.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
