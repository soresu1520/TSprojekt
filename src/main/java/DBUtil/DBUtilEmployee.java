package DBUtil;

import DAO.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Extends DBUtil class
 * Contains method used to control Login Servlet
 */
public class DBUtilEmployee extends DBUtil{

    private String URL;
    private String name;
    private String password;

    public DBUtilEmployee(String URL) {
        this.URL = URL;
    }

    public DBUtilEmployee() {
    }


    /**
     * Creates list of WorkLeaves belonging to certain user
     * @param id
     * @return List of workLeaves
     * @throws Exception
     */
    public List<WorkLeave> getWorkLeaves(int id) throws Exception {


        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM work_leaves WHERE employee_id="+id;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int ids = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                workLeaves.add(new WorkLeave(ids, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {
            close(conn, statement, resultSet);
        }


        return workLeaves;

    }


    /**
     * Connects to database to create list of objects, that fulfill set conditions
     * @param depId
     * @param status
     * @return List of ManagerView objects
     * @throws Exception
     */

    public List<ManagerView> getManagerView(int depId, String status) throws Exception {


        List<ManagerView> managerViews = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {


            conn = DriverManager.getConnection(URL, name, password);
            String sql = "SELECT * FROM manager_view WHERE leave_status = '" + status + "'AND dep_id="+depId;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {


                int ids = resultSet.getInt("employee_id");
                int idLeave = resultSet.getInt("leave_id");
                String name= resultSet.getString("employee_name");
                String position = resultSet.getString("eposition");
                int days = resultSet.getInt("leave_days");
                int availabledays = resultSet.getInt("avaiable_days");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days2 = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int idDep = resultSet.getInt("dep_id");


                managerViews.add(new ManagerView(ids, idLeave, name, position, days, availabledays, startDate,endDate, days2, leaveType, leaveStatus,idDep));

            }

        } finally {
            close(conn, statement, resultSet);
        }


        return managerViews;

    }


    /**
     * Creates Manager class object containing information on chosen user, based on data from database e
     * @param login
     * @return Manager manager
     * @throws Exception
     */
    public Manager getManagerData(String login)  throws Exception{

        Manager managers = new Manager();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM managers WHERE login='"+login +"'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {

                int ids = resultSet.getInt("id");
                String pass= resultSet.getString("pass");
                String name= resultSet.getString("first_name");
                String lname= resultSet.getString("last_name");
                String email= resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");



                managers=new Manager(ids, login, pass, name,lname,email,dep);

            }

        } finally {
            close(conn, statement, resultSet);
        }

        return managers;
    }


    /**
     * Creates Employee class object containing information on chosen user, based on data from database
     * @param login
     * @return
     * @throws Exception
     */
    public Employee getEmployeeData(String login)  throws Exception{

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Employee employees = new Employee();
        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM employees WHERE login='"+login+"'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {


                int ids = resultSet.getInt("id");
                String pass= resultSet.getString("pass");
                String name= resultSet.getString("first_name");
                String lname= resultSet.getString("last_name");
                String email= resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");
                String position = resultSet.getString("eposition");
                int leaveDays = resultSet.getInt("leave_days");
                int avDays = resultSet.getInt("avaiable_days");


                employees = new Employee(ids, login,pass, name,lname,email,dep,position,leaveDays,avDays);

            }

        } finally {
            close(conn, statement, resultSet);
        }

        return employees;
    }


    /**
     * Updates leave_status in work_leaves table in database.
     * Changes it to 'do usunięcia'
     * @param id
     * @throws Exception
     */
    public void updateLeave(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "UPDATE work_leaves SET leave_status=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "do usunięcia");
            statement.setInt(2, id);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }


    /**
     * Modifies entry with given in work_leave table
     * @param id
     * @param workLeave
     * @throws Exception
     */
    public void updateLeaveModify(int id,WorkLeave workLeave) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "UPDATE work_leaves SET leave_status=?,start_date=?, days=?,end_date=?,leave_type=?"+
                    "where id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "do modyfikacji");
            statement.setInt(3,workLeave.getDays());
            statement.setString(2,workLeave.getStartDate());
            statement.setString(4,workLeave.getEndDate());
            statement.setString(5,workLeave.getLeaveType());
            statement.setInt(6, id);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }

    /**
     * Updates leave_status in work_leave table to 'zaakceptowano'
     * @param id
     * @throws Exception
     */

    public void updateLeaveAccept(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "UPDATE work_leaves SET leave_status=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "zaakceptowany");
            statement.setInt(2, id);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }

    /**
     * Deletes entry from work_leaves table were certain id is present
     * @param id
     * @throws Exception
     */
    public void updateLeaveCancel(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            int leaveID = id;

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "DELETE FROM work_leaves WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, leaveID);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }

    /**
     * Creates new entry into work_leaves table
     * @param workLeave
     * @throws Exception
     */
    public void addLeave(WorkLeave workLeave) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "INSERT INTO work_leaves(start_date, end_date, days, leave_type, leave_status, employee_id)" +
                    " VALUES(?, ?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, workLeave.getStartDate());
            statement.setString(2, workLeave.getEndDate());
            statement.setInt(3, workLeave.getDays());
            statement.setString(4, workLeave.getLeaveType());
            statement.setString(5, workLeave.getLeaveStatus());
            statement.setInt(6, workLeave.getEmployeeId());

            statement.execute();


        } finally {

            close(conn, statement, null);

        }

    }

    /**
     * Check whether manager or employee are login into site
     * @return
     * @throws Exception
     */
    public int checkWho() throws Exception {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement2;
        ResultSet resultSet2;
        int who=0;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM managers WHERE login = '" + name + "' LIMIT 1;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            String sql2 = "SELECT * FROM employees WHERE login = '" + name + "' LIMIT 1;";
            statement2 = conn.createStatement();
            resultSet2 = statement2.executeQuery(sql2);

            while (resultSet.next()) {
                who=1;
            }
            while (resultSet2.next()){
                who=2;
            }

        }
        finally {

            close(conn, statement, resultSet);
        }

        return who;

    }

    /**
     * Create list of entries from leaves_archive table
     * @param id
     * @return
     * @throws Exception
     */
    public List<LeavesArchive> getOldLeaves(int id) throws Exception{
        List <LeavesArchive> leavesArchives = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM leaves_archive WHERE employee_id="+id;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {

                int ids = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                // dodanie do listy nowego obiektu
                leavesArchives.add(new LeavesArchive(ids, startDate,endDate,days,leaveType,id));

            }

        } finally {
            close(conn, statement, resultSet);
        }

        return leavesArchives;
    }

    /**
     * Creates WorkLeave class object containing information on chosen user, based on data from database
     * @param id
     * @return Chosen work leave
     * @throws Exception
     */
    public WorkLeave getWorkLeave(int id) throws Exception{
        WorkLeave workLeave = new WorkLeave();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM work_leaves WHERE id="+id;
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int ids = resultSet.getInt("id");
                String startDate= resultSet.getString("start_date");
                String end_date= resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String type = resultSet.getString("leave_type");
                String status = resultSet.getString("leave_status");
                int emplId = resultSet.getInt("employee_id");
                // dodanie do listy nowego obiektu
                workLeave=new WorkLeave(ids,startDate, end_date, days, type, status,emplId );

            }

        } finally {
            close(conn, statement, resultSet);
        }
        return workLeave;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() { return name;}
}
