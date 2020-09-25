package DBtest;

import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) throws SQLException {
        MyDB myDB = new MyDB();

        myDB.Connect();
        myDB.getSongs();
    }
}
