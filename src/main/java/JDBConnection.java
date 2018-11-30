import java.io.File;
import java.sql.*;

public class MySQLConnection {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://koko.c8cejdutv9zr.us-east-2.rds.amazonaws.com/Books";
	static final String USER = "team";
	static final String PASS = "cs157a";

	public static void main(String[] args) throws SQLException {
		Connection conn = connect(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		  // This is how I import data into tables
		  //RUN THESE 3 below lines ONCE ONCE ONCE 
		  //if you guys are curious how to set the values into 4 tables.
		  //Since i already set them up, so if you guys wanna do this, need to drop all tables in Books.
			
		  //BooksTables books = new BooksTables(stmt, conn);
		  //books.createTable(stmt, conn); 
		  //autoFill(stmt);
		 
}
/**
	 * connect to database by DriverManager
	 * 
	 * @param DB_URL
	 * @param USER
	 * @param PASS
	 * @return
	 */
	public static Connection connect(String DB_URL, String USER, String PASS) {
		Connection con = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			if (con != null) {
				System.out.println("Established");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}// end connect
	/**
	 * auto fill data into file from external file MUST PLACE Files into dir:
	 * "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/filename.csv"
	 * 
	 * @param stmt
	 */
	public static void autoFill(Statement stmt) {
		String author = "LOAD DATA LOCAL INFILE '" + "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/author.csv"
				+ "' INTO TABLE Books.authors FIELDS TERMINATED BY ','"
				+ " LINES TERMINATED BY '\r\n' ignore 1 rows(authorID, firstname, lastname) ";
		String publishers = "LOAD DATA LOCAL INFILE '" + "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/publisher.csv"
				+ "' INTO TABLE Books.publishers FIELDS TERMINATED BY ','"
				+ " LINES TERMINATED BY '\r\n' ignore 1 rows (publisherID, publisherName) ";
		String authorISBN = "LOAD DATA LOCAL INFILE '" + "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/authorISBN.csv"
				+ "' INTO TABLE Books.authorISBN FIELDS TERMINATED BY ','"
				+ " LINES TERMINATED BY '\r\n' ignore 1 rows (isbn, authorID) ";
		String titles = "LOAD DATA LOCAL INFILE '" + "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/title.csv"
				+ "' INTO TABLE Books.titles FIELDS TERMINATED BY ','"
				+ " LINES TERMINATED BY '\r\n' ignore 1 rows (isbn,title, editionNumber, year, publisherID, price) ";
		try {
			stmt.executeUpdate(author);
			stmt.executeUpdate(publishers);
			stmt.executeUpdate(authorISBN);
			stmt.executeUpdate(titles);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}