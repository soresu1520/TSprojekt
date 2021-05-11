package DBUtil;

import DAO.Employee;
import DBUtil.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBUtilRegister extends DBUtil {

    private DataSource dataSource;

    public DBUtilRegister(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
            String query = "GRANT ALL PRIVILEGES ON *.* TO '" + employee.getLogin() + "'@'localhost'";
            statement3.execute(query);


        } finally {

            close(conn, statement, null);

        }

    }




}
