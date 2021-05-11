package DBUtil;

import DAO.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtilEmployee extends DBUtil{

    private String URL;
    private String name;
    private String password;

    public DBUtilEmployee(String URL) {
        this.URL = URL;
    }

    public DBUtilEmployee() {
    }




    // lista urlopów pracownika
    public List<WorkLeave> getWorkLeaves(int id) throws Exception {


        List<WorkLeave> workLeaves = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM work_leaves WHERE employee_id="+id;
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                String leaveStatus = resultSet.getString("leave_status");
                int employeeID = resultSet.getInt("employee_id");

                // dodanie do listy nowego obiektu
                workLeaves.add(new WorkLeave(ids, startDate, endDate, days, leaveType, leaveStatus, employeeID));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return workLeaves;

    }

    //widok menegera do zaakceptowania
    public List<ManagerView> getManagerView(int depId, String status) throws Exception {


        List<ManagerView> managerViews = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM manager_view WHERE leave_status = '" + status + "'AND dep_id="+depId;
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
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

                // dodanie do listy nowego obiektu
                managerViews.add(new ManagerView(ids, idLeave, name, position, days, availabledays, startDate,endDate, days2, leaveType, leaveStatus,idDep));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return managerViews;

    }


    //get do danych managera
    public List<Manager> getManagerList()  throws Exception{

        List<Manager> managers = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM managers";
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                String login= resultSet.getString("login");
                String pass= resultSet.getString("pass");
                String name= resultSet.getString("first_name");
                String lname= resultSet.getString("last_name");
                String email= resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");


                // dodanie do listy nowego obiektu
                managers.add(new Manager(ids, login,pass, name,lname,email,dep));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }

        return managers;
    }

    //weź dane managera o danym loginie
    public Manager getManagerData(String login)  throws Exception{

        Manager managers = new Manager();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM managers WHERE login='"+login +"'";
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                //String login= resultSet.getString("login");
                String pass= resultSet.getString("pass");
                String name= resultSet.getString("first_name");
                String lname= resultSet.getString("last_name");
                String email= resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");


                // dodanie do listy nowego obiektu
                managers=new Manager(ids, login, pass, name,lname,email,dep);

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }

        return managers;
    }

    //weź dane pracownika o danym loginie
    public Employee getEmployeeData(String login)  throws Exception{

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Employee employees = new Employee();
        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM employees WHERE login='"+login+"'";
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                //String login= resultSet.getString("login");
                String pass= resultSet.getString("pass");
                String name= resultSet.getString("first_name");
                String lname= resultSet.getString("last_name");
                String email= resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");
                String position = resultSet.getString("eposition");
                int leaveDays = resultSet.getInt("leave_days");
                int avDays = resultSet.getInt("avaiable_days");

                // dodanie do listy nowego obiektu
                employees = new Employee(ids, login,pass, name,lname,email,dep,position,leaveDays,avDays);

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }

        return employees;
    }

    public List<Employee> getEmployeeList(int id)  throws Exception{

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Employee>employees = new ArrayList<>();
        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM employees";
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String name = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int dep = resultSet.getInt("dep_id");
                String position = resultSet.getString("eposition");
                int leaveDays = resultSet.getInt("leave_days");
                int avDays = resultSet.getInt("avaiable_days");

                // dodanie do listy nowego obiektu
                employees.add(new Employee(ids, login,pass, name,lname,email,dep,position,leaveDays,avDays));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }

        return employees;
    }


    //update do pracownika - do usuniecia
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
            statement.setString(1, "do usunięcia");
            statement.setInt(2, id);


            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);

        }

    }

    // modyfikacja
    public void updateLeaveModify(int id,WorkLeave workLeave) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE work_leaves SET leave_status=?,start_date=?, days=?,end_date=?,leave_type=?"+
                    "where id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "do modyfikacji");
            statement.setInt(3,workLeave.getDays());
            statement.setString(2,workLeave.getStartDate());
            statement.setString(4,workLeave.getEndDate());
            statement.setString(5,workLeave.getLeaveType());
            statement.setInt(6, id);


            // wykonanie zapytania
            statement.execute();

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, null);

        }

    }

    public void updateLeaveAccept(int id) throws Exception {

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




    //update dla managera

    public void updateLeaveManager(int id,WorkLeave workLeave) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie UPDATE
            String sql = "UPDATE work_leaves SET leave_status=?"+
                    "where id =?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, "zaakceptowano");
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

            // konwersja id na liczbe
            int leaveID = id;

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

            //if(resultSet!=null)
            //    who=1;

            //if(resultSet2!=null)
            //    who=2;

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


    public List<LeavesArchive> getOldLeaves(int id) throws Exception{
        List <LeavesArchive> leavesArchives = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM leaves_archive WHERE employee_id="+id;
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int ids = resultSet.getInt("id");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                int days = resultSet.getInt("days");
                String leaveType = resultSet.getString("leave_type");
                // dodanie do listy nowego obiektu
                leavesArchives.add(new LeavesArchive(ids, startDate,endDate,days,leaveType,id));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }


        return leavesArchives;
    }


    public WorkLeave getWorkLeave(int id) throws Exception{
        WorkLeave workLeave = new WorkLeave();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = DriverManager.getConnection(URL, name, password);

            // zapytanie SELECT
            String sql = "SELECT * FROM work_leaves WHERE id="+id;
            statement = conn.createStatement();


            // wykonanie zapytania SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
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

            // zamkniecie obiektow JDBC
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
