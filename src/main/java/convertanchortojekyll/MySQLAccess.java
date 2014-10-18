package convertanchortojekyll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class MySQLAccess {

    ResultSet prepareStatement(String query) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/anchor", "anchor", "anchor");
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
