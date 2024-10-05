package DefiningClassesExercise.CompanyRoaster;

public class Employee {
    //name, salary, position, department, email, and age.
    public String name;
    public double salary;
    public String position;
    public String department;
    public String email;
    public int age;

    @Override
    public String toString() {
        return String.format("%.2f, %s, %d", this.salary, this.email, this.age);
    }

    //The name, salary, position, and department are mandatory, while the rest are optional.
    public Employee(String name, double salary, String position, String department) {
        this(name, salary, position, department, null, -1);
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
    }

    public Employee(String name, double salary, String position, String department, String email, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
        this.age = age;
    }

    public Employee(String name, double salary, String position, String department, String email) {
        this(name, salary, position, department, email, -1);
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.email = email;
    }

    public Employee(String name, double salary, String position, String department, int age) {
        this(name, salary, position, department, null, age);
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
