package electricity.billing.system;

import java.sql.*;

public class Database implements AutoCloseable {  // Add AutoCloseable
    public Connection connection;
    public Statement statement;

    public Database() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bill_system",
                "root",
                "Ugwuaneke@123"
        );
        statement = connection.createStatement();
    }
    // Add this method
    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    @Override
    public void close() throws SQLException {  // Implement close()
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
