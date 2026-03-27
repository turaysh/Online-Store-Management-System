public class Employee extends User {
    private double salary;
    public Employee(int id, String name, String email, double salary) {
        super(id, name, email);
        this.salary = salary;
    }
    public String getRole() {
        return "Employee";
    }
}
