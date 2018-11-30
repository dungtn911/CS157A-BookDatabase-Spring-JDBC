import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class BooksTables {

	private Statement stmt;
	private Connection conn;
	/**
	 * @param stmt2
	 * @param con
	 */
	public BooksTables(Statement stmt, Connection conn){
		this.stmt = stmt;
		this.conn = conn;
	}
	public void createTable(Statement stmt, Connection conn){
	
		String publishers = "CREATE TABLE publishers "
                + " (publisherID INTEGER NOT NULL AUTO_INCREMENT, "
                + " publisherName CHAR(100) NOT NULL, "
                + " PRIMARY KEY ( publisherID ))";
		
        String authors = "CREATE TABLE authors "
                + " (authorID integer(10) NOT NULL AUTO_INCREMENT, " 
        		+ " firstname varchar(20) NOT NULL, "
                + " lastname varchar(20) NOT NULL, "
                + " primary key( authorID )) ";
        
        String authorISBN = "CREATE TABLE authorISBN " 
        		+ " (isbn char(10) NOT NULL,"
        		+ " authorID int(10) NOT NULL, "
                + "foreign key (authorID) references authors(authorID)) ";
        
        String titles = "CREATE TABLE titles " 
        		+ " (isbn char(10) NOT NULL, "
                + " title varchar(500) NOT NULL, " 
        		+ " editionNumber int NOT NULL, "
                + " year char(4) NOT NULL, " 
        		+ " publisherID int(10) NOT NULL, "
                + " price numeric(8,2)NOT NULL, "
                + " foreign key(publisherID) references publishers(publisherID), "
    			+ " primary key(isbn)) ";

        try {
        	stmt.executeUpdate(authors);
			stmt.executeUpdate(publishers);
			stmt.executeUpdate(authorISBN);
			stmt.executeUpdate(titles);
			System.out.println("TABLES CREATED");
		}  catch(Exception ex){
			ex.printStackTrace();
		}
        
	}

}
