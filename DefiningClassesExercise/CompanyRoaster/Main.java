package DefiningClassesExercise.CompanyRoaster;

import DefiningClassesExercise.OpinionPoll.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String SIZE_NOT_VALID = "You need to submit a valid form!";

    private static final String AVERAGE_SALARY_DEPARTMENT_DISPLAY = "Highest Average Salary: %s\n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Employee> employees = addEmployeesToDataBase(n, scanner);

        //grouping employees by department and getting their average salary:
        Map<String, Double> averageSalaryPerDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        //finding the department name with the highest salary:
        String highestSalaryDepartment =
                Collections.max(averageSalaryPerDepartment.entrySet(), Map.Entry.comparingByValue()).getKey();

        //accessing the salary itself utilizing the key
        Double highestSalary = averageSalaryPerDepartment.get(highestSalaryDepartment);

        //getting all the employees in this particular department using stream api;
        List<Employee> employeesInHighestPaidDepartmentList
                = employees.stream().filter(entry -> entry.getDepartment()
                .equals(highestSalaryDepartment)).collect(Collectors.toCollection(ArrayList::new));

        System.out.printf(AVERAGE_SALARY_DEPARTMENT_DISPLAY, highestSalaryDepartment);
        employeesInHighestPaidDepartmentList.forEach(System.out::println);

        //        employeesPerDepartment.entrySet().stream().sorted(entry -> {
//            String department = entry.getKey();
//            List<Employee> employeesInDepartment = entry.getValue();
//
//            OptionalDouble average = employeesInDepartment.stream().mapToDouble(Employee::getSalary).average();
//        })

    }

    private static Map<String, List<Employee>> arrangeEmployeesPerDepartment(List<Employee> employees) {
        Map<String, List<Employee>> employeesPerDepartment = new LinkedHashMap<>();
        for (Employee currentEmployee : employees) {
            String department = currentEmployee.getDepartment();

            employeesPerDepartment.putIfAbsent(department, new ArrayList<>());
            employeesPerDepartment.get(department).add(currentEmployee);
        }
        return employeesPerDepartment;
    }

    private static List<Employee> addEmployeesToDataBase(int n, Scanner scanner) {
        List<Employee> employees = new LinkedList<>();

        Employee employee = null;
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (sizeIsValid(tokens)) {
                // //name, salary, position, department, email, and age.
                if (tokens.length == 4) {
                    employee = handleOnlyMandatoryFields(tokens);
                }

                if (tokens.length == 5) {
                    employee = handleOnlyOneNotMandatoryFieldPresent(tokens);
                }

                if (tokens.length == 6) {
                    employee = handleAllFieldsPresent(tokens);
                }

            } else {
                System.out.printf(SIZE_NOT_VALID);
                break;
            }

            employees.add(employee);

        }
        return employees;
    }

    private static boolean sizeIsValid(String[] tokens) {
        return tokens.length >= 4 && tokens.length <= 6;
    }

    private static Employee handleAllFieldsPresent(String[] tokens) {
        Employee employee;
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        String position = tokens[2];
        String department = tokens[3];
        String email = tokens[4];
        int age = Integer.parseInt(tokens[5]);

        employee = new Employee(name, salary, position, department, email, age);
        return employee;
    }

    private static Employee handleOnlyOneNotMandatoryFieldPresent(String[] tokens) {
        Employee employee;

        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        String position = tokens[2];
        String department = tokens[3];

        char character = tokens[4].charAt(0);

        boolean isDigit = false;

        int age = 0;
        String email = " ";
        if (Character.isDigit(character)) {
            age = Integer.parseInt(tokens[4]);
            isDigit = true;
        } else {
            email = tokens[4];
        }

        if (isDigit) {
            employee = new Employee(name, salary, position, department, age);
        } else {
            employee = new Employee(name, salary, position, department, email);
        }
        return employee;
    }

    private static Employee handleOnlyMandatoryFields(String[] tokens) {
        Employee employee;

        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        String position = tokens[2];
        String department = tokens[3];

        employee = new Employee(name, salary, position, department);

        return employee;
    }
}

//separate employees per department
// Map<String, List<Employee>> employeesPerDepartment = arrangeEmployeesPerDepartment(employees);
