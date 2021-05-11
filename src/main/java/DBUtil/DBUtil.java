package DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Contains utilities, that help connecting to db
 */
public abstract class DBUtil {

    /**
     * Closes connection to database
     * @param conn
     * @param statement
     * @param resultSet
     */
    protected static void close(Connection conn, Statement statement, ResultSet resultSet) {

        try {

            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (conn != null)
                conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}