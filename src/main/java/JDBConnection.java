import java.sql.*;
import java.sql.ResultSet;
import java.util.Scanner;


public class JDBConnection {


    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://koko.c8cejdutv9zr.us-east-2.rds.amazonaws.com/Books";

    static final String USER = "team";
    static final String PASS = "cs157a";


    public static void main(String[] args) throws SQLException {
        Connection con = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();

            BooksTables books = new BooksTables(stmt, con);
            books.populateTables(stmt, con);
            //  autoFill(stmt);
            //    selectQueryOnAuthor(stmt);
            // selectQueryOnPublihers(stmt);

            // selectBooksFromSpecificPublisher(stmt);

            // addNewAuthorQuery(stmt);
            //   addNewPublisherQuery(stmt);
           // addNewTitleForAuthor(stmt, con);

            // updatePublisherInfo(stmt);
            //  updateAuthorInfo(stmt);


//            stmt.executeUpdate("CREATE TRIGGER trg_test \n " +
//
//                    "  AFTER UPDATE ON authors \n" +
//
//                    "  FOR EACH ROW \n" +
//
//                    "  WHEN (new.avalue IS NULL)\n" +
//
//                    "  BEGIN\n" +
//
//                    "    SELECT seq_test.nextval INTO :new.avalue FROM dual;\n" +
//
//                    "  END");


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
            if (con != null) {
                try {
                    stmt.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void autoFill(Statement stmt) {
        String author = "LOAD DATA LOCAL INFILE '" + "/Users/ruiyang/Documents/SJSU/Fall2018/CS 157A/projectJDBC/CS157A-BookDatabase-Spring-JDBC/src/main/resources/author.csv"
                + "' INTO TABLE codeman.authors FIELDS TERMINATED BY ','"
                + " LINES TERMINATED BY '\r\n' ignore 1 rows(authorID, firstname, lastname) ";

        String publishers = "LOAD DATA LOCAL INFILE '" + "/Users/ruiyang/Documents/SJSU/Fall2018/CS 157A/projectJDBC/CS157A-BookDatabase-Spring-JDBC/src/main/resources/publisher.csv"
                + "' INTO TABLE codeman.publishers FIELDS TERMINATED BY ','"
                + " LINES TERMINATED BY '\r\n' ignore 1 rows (publisherID, publisherName) ";

        String authorISBN = "LOAD DATA LOCAL INFILE '" + "/Users/ruiyang/Documents/SJSU/Fall2018/CS 157A/projectJDBC/CS157A-BookDatabase-Spring-JDBC/src/main/resources/authorISBN.csv"
                + "' INTO TABLE codeman.authorISBN FIELDS TERMINATED BY ','"
                + " LINES TERMINATED BY '\r\n' ignore 1 rows (isbn, authorID) ";
        String titles = "LOAD DATA LOCAL INFILE '" + "/Users/ruiyang/Documents/SJSU/Fall2018/CS 157A/projectJDBC/CS157A-BookDatabase-Spring-JDBC/src/main/resources/title.csv"
                + "' INTO TABLE codeman.titles FIELDS TERMINATED BY ','"
                + " LINES TERMINATED BY '\r\n' ignore 1 rows (isbn,title, editionNumber, year, publisherID, price) ";
        try {
            //stmt.executeUpdate(author);
            //stmt.executeUpdate(publishers);
            stmt.executeUpdate(authorISBN);
            // stmt.executeUpdate(titles);
            System.out.println("Entires populated");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //1) Select all authors from the authors table. Order the information alphabetically by the author’s last name and first name.
    //   works perfect, bug free
    public static void selectQueryOnAuthor(Statement stmt) {

        try {
            String sql = "SELECT * FROM authors ORDER BY lastname ASC, firstname ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int authorID = rs.getInt("authorID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                System.out.print("AuthorID: " + authorID);
                System.out.print(", Lastname: " + lastname);
                System.out.print(", Firstname: " + firstname);

                System.out.println();
            }
            rs.close();
        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
    //2) Select all publishers from the publishers table.
    //   works perfect, bug free
    public static void selectQueryOnPublihers(Statement stmt) {

        try {
            String sql = "SELECT * FROM publishers";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int publisherID = rs.getInt("publisherID");
                String publisherName = rs.getString("publisherName");

                System.out.print("PublisherID: " + publisherID);
                System.out.print(", Publiher Name: " + publisherName);

                System.out.println();
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------
    //3) Select a specific publisher and list all books published by that publisher.
    //   Include the title, year and ISBN number. Order the information alphabetically by title.
    //   works perfect, bug free
    public static void selectBooksFromSpecificPublisher(Statement stmt) {

        try {
            String sql = "SELECT * FROM titles WHERE publisherID = 4 ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int publisherID = rs.getInt("publisherID");
                int editionNum = rs.getInt("editionNumber");
                String year = rs.getString("year");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                long price = rs.getLong("price");


                System.out.print("ISBN: " + isbn);
                System.out.print(", Title: " + title);
                System.out.print(", Edition Number: " + editionNum);
                System.out.print(", Year: " + year);
                System.out.print(", PublisherID: " + publisherID);
                System.out.print(", Price: " + price);
                System.out.println();
            }
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
    //4) Add new author
    //   works perfect, bug free
    public static void addNewAuthorQuery(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter Last Name: ");
        String lastName = sc.nextLine();
        try {
            String sql = "SELECT * FROM authors WHERE firstname = '" + firstName + "'" + " AND lastname =  '" + lastName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Failure! Author already existed");
            } else {
                sql = "INSERT INTO authors(firstname, lastname) VALUE ('" + firstName + "'" + ", '" + lastName + "'" + ")";
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
    //5) Add new publisher
    //   works perfect, bug free
    public static void addNewPublisherQuery(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher's  Name: ");
        String pbname = sc.nextLine();
        try {

            String sql = "SELECT * FROM publishers WHERE publisherName = '" + pbname + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Failure! publisher already existed");
            } else {
                sql = "INSERT INTO publishers(publisherName) VALUE ('" + pbname + "'" + ")";
                stmt.executeUpdate(sql);
            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // 6)Edit/Update the existing information about a publisher
    //   works perfect, bug free
    public static void updatePublisherInfo(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Publisher's  Name You want to update: ");
        String pbname = sc.nextLine();
        try {

            String sql = "SELECT * FROM publishers WHERE publisherName = '" + pbname + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Enter the New publisher's Name You want to update: ");
                String newPBname = sc.nextLine();
                sql = " UPDATE publishers " + " SET publisherName = '" + newPBname + "'" + " WHERE publisherName = '" + pbname + "'";
                stmt.executeUpdate(sql);
            } else {
                System.out.println("Failure! publisher already existed");
            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------
    // 7)Edit/Update the existing information about an author

    public static void updateAuthorInfo(Statement stmt) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Author's First name you want to update: ");
        String oldfirstName = sc.next();
        System.out.println("Enter Author's Last name you want to update: ");
        String oldlastName = sc.next();
        try {

            String sql = "SELECT * FROM authors WHERE firstname = '" + oldfirstName + "'" + " AND lastname =  '" + oldlastName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Enter the New author's first name You want to update: ");
                String newFN = sc.next();
                System.out.println("Enter the New author's last name You want to update: ");
                String newLN = sc.next();
                sql = " UPDATE authors " + " SET firstname = '" + newFN + "', " + " lastname ='" + newLN + "'"
                        + " WHERE firstname = '" + oldfirstName + "' AND " + " lastname ='" + oldfirstName + "'";
                stmt.executeUpdate(sql);
            } else {
                System.out.println("Failure! author does not  exist");
            }

        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------
    // 8) add a new title for an author
    //   works perfect, bug free
    public static void addNewTitleForAuthor(Statement stmt, Connection con) {

        try {

            String sql = "set foreign_key_checks = 0";
            stmt.execute(sql);

            // String sql = "CALL `codeman`.`add_new_title_to_author`('ISBN000113', 'Hello World', 1, '1990', 'HouseTagerreal', 99.99, 'Roy', 'Oh');";
            CallableStatement cStmt = con.prepareCall("{call add_new_title_to_author(?, ?, ?, ?, ?, ?, ? ,?)}");

            //author info
            cStmt.setString(7, "Roy");
            cStmt.setString(8, "Oh");

            //authorISBN info
            cStmt.setString(1, "ISBN000143");

            //publisher info
            cStmt.setString(5, "House Hmong");

            //title info
            cStmt.setString(2, "Hello New World");
            cStmt.setInt(3, 1);
            cStmt.setString(4, "1991");
            cStmt.setDouble(6, 100.99);

            cStmt.execute();
            cStmt.close();
            stmt.close();
            System.out.println("Stored procedure called successfully!");

        } catch (SQLException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

