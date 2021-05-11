package DBUtil;

import DAO.WorkLeave;
import DBUtil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtilAdmin extends DBUtil {

    private String URL;
    private String name;
    private String password;

    public DBUtilAdmin() {
    }

    public DBUtilAdmin(String URL) {
        this.URL = URL;
    }



    public List<WorkLeave> getWorkLeavesToDelete() throws Exception {

        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM work_leaves WHERE leave_status='do usunięcia'";
            statement = conn.createStatement();

            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                // dodanie do listy nowego obiektu
                workLeaves.add(new WorkLeave(id, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return workLeaves;


    }


    public List<WorkLeave> getWorkLeavesToModify() throws Exception {

        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM work_leaves WHERE leave_status='do modyfikacji'";
            statement = conn.createStatement();

            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                // dodanie do listy nowego obiektu
                workLeaves.add(new WorkLeave(id, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return workLeaves;

    }
    public void deleteLeave(String id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // konwersja id na liczbe
            int leaveID = Integer.parseInt(id);

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie DELETE
            String sql = "DELETE FROM work_leaves WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1, leaveID);

            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);

        }

    }



    public void updateLeave(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE work_leaves SET leave_status=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "zaakceptowany");
            statement.setInt(2, id);


            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);

        }

    }

    public void updateLeaveCancel(int id) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE work_leaves SET leave_status=?" +
                    "WHERE id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "odrzucono modyfikacje");
            statement.setInt(2, id);


            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
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
