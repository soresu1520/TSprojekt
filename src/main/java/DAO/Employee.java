package DAO;

/**
 * Maps employees table from companyDB
 */
public class Employee {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int departmentId;
    private String position;
    private int leaveDays;
    private int availableDays;

    public Employee(){}

    public Employee(int id, String login, String password, String firstName, String lastName, String email, int departmentId, String position, int leaveDays, int availableDays) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.position = position;
        this.leaveDays = leaveDays;
        this.availableDays = availableDays;
    }

    public Employee(String login, String password, String firstName, String lastName, String email, int departmentId, String position, int leaveDays, int availableDays) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.position = position;
        this.leaveDays = leaveDays;
        this.availableDays = availableDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }
}
