package DBtest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDB {
    String user = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/myTunes";

    Connection connection;
    Statement statement;

    public void Connect() {
        System.out.println("Connecting to mytunes...");
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getSongs() throws SQLException {
        statement = connection.createStatement();

        if (statement.execute("SELECT * FROM Songs")) {
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString("SongID") + " " + rs.getString("Title") + " " + rs.getString("Artist"));
            }
        }
    }
}
