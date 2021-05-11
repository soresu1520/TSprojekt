package DAO;

import java.time.LocalDate;

public class LeavesArchive {

    private int id;
    private String startDate;
    private String endDate;
    private int days;
    private String leaveType;
    private int employeeId;

    public LeavesArchive(int id, String startDate, String endDate, int days, String leaveType, int employeeId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.leaveType = leaveType;
        this.employeeId = employeeId;
    }

    public LeavesArchive( String startDate,  String endDate, int days, String leaveType, int employeeId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.leaveType = leaveType;
        this.employeeId = employeeId;
    }

    public int getId() {return id; }

    public void setId(int id) {
        this.id = id;
    }

    public  String getStartDate() {
        return startDate;
    }

    public void setStartDate( String startDate) {
        this.startDate = startDate;
    }

    public  String getEndDate() {
        return endDate;
    }

    public void setEndDate( String endDate) {
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
