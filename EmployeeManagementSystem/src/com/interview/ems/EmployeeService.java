package com.interview.ems;

import java.io.*;
import java.util.*;

public class EmployeeService {
    private static final String FILE_NAME = "employees.txt";
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        loadFromFile();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public boolean addEmployee(Employee employee) {
        if (findById(employee.getId()) != null) return false;
        employees.add(employee);
        saveToFile();
        return true;
    }

    public Employee findById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) return employee;
        }
        return null;
    }

    public boolean updateEmployee(int id, String name, String department, double salary) {
        Employee employee = findById(id);
        if (employee == null) return false;
        employee.setName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        saveToFile();
        return true;
    }

    public boolean deleteEmployee(int id) {
        boolean removed = employees.removeIf(employee -> employee.getId() == id);
        if (removed) saveToFile();
        return removed;
    }

    public List<Employee> searchByName(String keyword) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> sortBySalary() {
        List<Employee> result = getAllEmployees();
        result.sort(Comparator.comparingDouble(Employee::getSalary));
        return result;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                writer.println(e.getId() + "|" + e.getName().replace("|", " ")
                        + "|" + e.getDepartment().replace("|", " ") + "|" + e.getSalary());
            }
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|", -1);
                if (data.length == 4) {
                    employees.add(new Employee(
                            Integer.parseInt(data[0]), data[1], data[2],
                            Double.parseDouble(data[3])));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }
    }
}
