import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class BooksTables {

    private Statement stmt;
    private Connection conn;

    /**
     * @param stmt;
     * @param conn;
     */
    public BooksTables(Statement stmt, Connection conn) {
        this.stmt = stmt;
        this.conn = conn;
    }

    public void populateTables(Statement stmt, Connection conn) {

        String publishers = "CREATE TABLE IF NOT EXISTS publishers "
                + " (publisherID INTEGER NOT NULL AUTO_INCREMENT, "
                + " publisherName CHAR(100) NOT NULL, "
                + " PRIMARY KEY ( publisherID ))";

        String authors = "CREATE TABLE IF NOT EXISTS authors "
                + " (authorID integer(10) NOT NULL AUTO_INCREMENT, "
                + " firstname varchar(20) NOT NULL, "
                + " lastname varchar(20) NOT NULL, "
                + " primary key( authorID )) ";

        String authorISBN = "CREATE TABLE IF NOT EXISTS authorISBN "
                + " (isbn char(10) NOT NULL,"
                + " authorID int(10) NOT NULL, "
                + "foreign key (authorID) references authors(authorID),"
                + "foreign key (isbn) references titles(isbn)) ";

        String titles = "CREATE TABLE IF NOT EXISTS  titles "
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
            stmt.executeUpdate(titles);
            stmt.executeUpdate(authorISBN);
            System.out.println("TABLES CREATED");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//    //populate 15 entries for each table
//    public void populateEntries(Statement stmt){
//
//        //1. 15 entries for publishers
//        String publisher1 = "insert into " + Books + ".publishers" + "values ("
//        stmt.executeUpdate()
//
//
//
//    }

}