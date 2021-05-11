package DBUtil;

import DAO.Employee;
import DBUtil.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Extends DBUtil class
 * Contains methods used to control MakeAccountServlet class
 */
public class DBUtilRegister extends DBUtil {

    private DataSource dataSource;

    public DBUtilRegister(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates new entry in employees table in company DB and creates new user with privileges
     * @param employee
     * @throws Exception
     */
    public void addEmployee(Employee employee) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = dataSource.getConnection();

            String sql = "INSERT INTO employees(login, pass, first_name, last_name, email, dep_id, eposition, leave_days, avaiable_days) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, employee.getLogin());
            statement.setString(2, employee.getPassword());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getLastName());
            statement.setString(5, employee.getEmail());
            statement.setInt(6, employee.getDepartmentId());
            statement.setString(7, employee.getPosition());
            statement.setInt(8, employee.getLeaveDays());
            statement.setInt(9, employee.getAvailableDays());

            statement.execute();

            Statement statement2 = conn.createStatement();
            String command = "create user '" + employee.getLogin() + "'@'localhost' identified by '" + employee.getPassword() + "'";
            statement2.execute(command);

            Statement statement3 = conn.createStatement();
            String query1 = "grant select, insert, update, trigger on companyDB.work_leaves to '" + employee.getLogin() + "'@'localhost'";
            String query2 = "grant select on companyDB.managers to '" + employee.getLogin() + "'@'localhost'";
            String query3 = "grant select on companyDB.employees to '" + employee.getLogin() + "'@'localhost'";
            String query4 = "grant select on companyDB.leaves_archive to '" + employee.getLogin() + "'@'localhost'";
            statement3.execute(query1);
            statement3.execute(query2);
            statement3.execute(query3);
            statement3.execute(query4);


        } finally {

            close(conn, statement, null);

        }

    }




}
