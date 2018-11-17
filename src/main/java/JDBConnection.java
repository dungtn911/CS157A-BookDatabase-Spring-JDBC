import java.sql.*;

public class JDBConnection {


    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://koko.c8cejdutv9zr.us-east-2.rds.amazonaws.com";

    static final String USER = "team";
    static final String PASS = "cs157a";

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            if (con != null) {
                System.out.println("Established");

            }
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

