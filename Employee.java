/**
 * Represents an employee in the store system.
 * Inherits common user information from the User class.
 */
public class Employee extends User {
    private double salary;

    /**
     * Creates a new employee with user information and salary.
     *
     * @param id the employee ID
     * @param name the employee name
     * @param email the employee email
     * @param salary the employee salary
     */
    public Employee(int id, String name, String email, double salary) {
        super(id, name, email);
        this.salary = salary;
    }

    /**
     * Returns the role of this user.
     *
     * @return the role name as Employee
     */
    public String getRole() {
        return "Employee";
    }
}
