package DBUtil;

import DAO.WorkLeave;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Extends DBUtil class
 * Contains methods used to control Admin class
 */
public class DBUtilAdmin extends DBUtil {

    private String URL;
    private String name;
    private String password;

    public DBUtilAdmin() {
    }

    public DBUtilAdmin(String URL) {
        this.URL = URL;
    }


    /**
     * Creates list of WorkLeaves meant to be deleted
     * @return List of WorkLeave objects (leaves meant to be deleted)
     * @throws Exception
     */
    public List<WorkLeave> getWorkLeavesToDelete() throws Exception {

        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM work_leaves WHERE leave_status='do usunięcia'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                workLeaves.add(new WorkLeave(id, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {
            close(conn, statement, resultSet);
        }


        return workLeaves;


    }
    /**
     * Creates list of WorkLeaves meant to be modified
     * @return List of WorkLeave objects (leaves meant to be modified)
     * @throws Exception
     */
    public List<WorkLeave> getWorkLeavesToModify() throws Exception {

        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(URL, name, password);

            String sql = "SELECT * FROM work_leaves WHERE leave_status='do modyfikacji'";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                workLeaves.add(new WorkLeave(id, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {
            close(conn, statement, resultSet);
        }


        return workLeaves;

    }

    /**
     * Deletes entry from work_leaves table where certain id is present
     * @param id id of leave
     * @throws Exception
     */
    public void deleteLeave(String id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            int leaveID = Integer.parseInt(id);

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
     * Changes leave_status of chosen entry to 'zaakceptowany'
     * @param id id of leave
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
            statement.setString(1, "zaakceptowany");
            statement.setInt(2, id);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }
    /**
     * Changes leave_status of chosen entry to 'odrzucono modyfikacje'
     * @param id id of leave
     * @throws Exception
     */
    public void updateLeaveCancel(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = DriverManager.getConnection(URL, name, password);

            String sql = "UPDATE work_leaves SET leave_status=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "odrzucono modyfikacje");
            statement.setInt(2, id);

            statement.execute();

        } finally {
            close(conn, statement, null);

        }

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
