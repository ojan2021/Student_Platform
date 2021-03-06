package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtils {

    static DataSource dataSource;

    static {
        System.out.println("Entered to the block...");
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String sql, String... args) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i  = 0; i < args.length; i++) {
                statement.setString(i+1, args[i]);
                System.out.println(args[i]);
            }
            boolean result = statement.executeQuery().next();
            if (result) {
                resultSet = statement.getResultSet();
                return resultSet;
            } else {
                System.out.println("There is no result set for this query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return null;
    }

    public static ResultSet query(String sql) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);


            resultSet = statement.getResultSet();

            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }

        return null;
    }



}
