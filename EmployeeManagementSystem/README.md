# Employee Management System (Java)

A beginner-friendly Core Java console project suitable for demonstrating in a fresher interview.

## Features
- Add, view, search, update and delete employees (CRUD)
- Sort employees by salary
- Prevent duplicate employee IDs
- Validate user input and handle invalid numbers
- Save records in `employees.txt` so data remains after restarting

## Technologies
- Java 17 or later
- OOP: classes, objects, encapsulation
- Collections: ArrayList, List
- Comparator and sorting
- File handling (BufferedReader, FileWriter, PrintWriter)
- Exception handling

## Run in VS Code / terminal
Open a terminal in the project folder and run:

```bash
javac -d out src/com/interview/ems/*.java
java -cp out com.interview.ems.Main
```

In Eclipse: File > Import > Existing Projects into Workspace (or create a Java project and copy the `src` package). Run `Main.java`.

The `employees.txt` file is created in the working directory after the first successful add/update/delete.

## Interview explanation
"I built an Employee Management System using Core Java. It supports CRUD operations, searching and sorting employees. I used encapsulation in the Employee class, ArrayList to manage records, Comparator to sort salaries, exception handling to validate input, and file handling to persist records between runs."

## Suggested demo
1. Add employee: 101, Yogesh, IT, 35000
2. Add employee: 102, Priya, HR, 42000
3. View all employees
4. Search for `yo`
5. Update employee 101
6. Sort by salary
7. Delete employee 102
8. Exit and run again to show saved data
