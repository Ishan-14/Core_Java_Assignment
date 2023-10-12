import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Employee {
    private String name;
    private String emailAddress;
    private int age;
    private Date dob;
    private static int nextId = 1;

    public Employee(String name, String emailAddress, int age, Date dob) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.age = age;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getAge() {
        return age;
    }

    public Date getDob() {
        return dob;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Employee ID: " + nextId++ + "\nName: " + name + "\nEmail Address: " + emailAddress + "\nAge: " + age
                + "\nDOB: " + sdf.format(dob) + "\n";
    }
}

public class EmployeeManagement {
    private static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployeesFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Options:");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Search Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    deleteEmployee(scanner);
                    break;
                case 3:
                    searchEmployees(scanner);
                    break;
                case 4:
                    saveEmployeesToFile();
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email Address: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter DOB (dd/MM/yyyy): ");
        String dobStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dob = sdf.parse(dobStr);
            Employee employee = new Employee(name, emailAddress, age, dob);
            employeeList.add(employee);
            System.out.println("Employee added successfully.");
            saveEmployeesToFile(); // Save updated employee data to the file
        } catch (ParseException e) {
            System.out.println("Invalid date format. Employee not added.");
        }
    }


    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeId > 0 && employeeId <= employeeList.size()) {
            employeeList.remove(employeeId - 1);
            saveEmployeesToFile(); // Save the updated list to the file
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Invalid Employee ID. Deletion failed.");
        }
    }


    private static void searchEmployees(Scanner scanner) {
        System.out.print("Enter query to search: ");
        String query = scanner.nextLine();
        System.out.print("Enter sorting field (Name, Age, DOB): ");
        String sortBy = scanner.nextLine();
        System.out.print("Enter sorting direction (Ascending/Descending): ");
        String sortDirection = scanner.nextLine();

        List<Employee> searchResults = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getName().contains(query)) {
                searchResults.add(employee);
            }
        }

        if ("Age".equalsIgnoreCase(sortBy)) {
            searchResults.sort(Comparator.comparingInt(Employee::getAge));
        } else if ("DOB".equalsIgnoreCase(sortBy)) {
            searchResults.sort(Comparator.comparing(Employee::getDob));
        } else {
            searchResults.sort(Comparator.comparing(Employee::getName));
        }

        if ("Descending".equalsIgnoreCase(sortDirection)) {
            Collections.reverse(searchResults);
        }

        if (searchResults.isEmpty()) {
            System.out.println("No matching records found.");
        } else {
            for (Employee employee : searchResults) {
                System.out.println(employee);
            }
        }
    }

    private static void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String emailAddress = data[1];
                int age = Integer.parseInt(data[2]);
                Date dob = sdf.parse(data[3]);
                employeeList.add(new Employee(name, emailAddress, age, dob));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Employee employee : employeeList) {
                writer.write(employee.getName() + "," + employee.getEmailAddress() + "," + employee.getAge() + ","
                        + sdf.format(employee.getDob()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
