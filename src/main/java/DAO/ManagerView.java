package DAO;

/**
 * Maps manager view from companyDB
 */
public class ManagerView {

    private int employeeId;
    private int leaveId;
    private String employeeName;
    private String position;
    private int leaveDays;
    private int availableDays;
    private String startDate;
    private String endDate;
    private int days;
    private String leaveType;
    private String leaveStatus;
    private int depid;



    public ManagerView(int employeeId, int leaveId, String employeeName, String position, int leaveDays, int availableDays, String startDate, String endDate, int days, String leaveType, String leaveStatus, int depid) {
        this.employeeId = employeeId;
        this.leaveId = leaveId;
        this.employeeName = employeeName;
        this.position = position;
        this.leaveDays = leaveDays;
        this.availableDays = availableDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
        this.depid = depid;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }


    public int getDepid() { return depid; }

    public void setDepid(int depid) { this.depid = depid; }
}
