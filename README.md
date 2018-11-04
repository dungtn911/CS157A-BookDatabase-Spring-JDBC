# CS157A-Project-Spring-JDBC

The goal of this project is to use JDBC to create a Books database, populate it, and then execute different SQL statements to query or manipulate the Books database.

In the above schema (E-R diagram), blue represents the name of the table, and green represents the primary key. The line between authors and authorISBN is one-to-many relationship (author can have many ISBNs). Line between authorISBN and titles tables is many-to-one (one title can have many authors). The sole purpose of the authorISBN table is to represent a many-to-many relationship between the authors and titles tables; an author can have many books and a book can have many authors.

Overview:
In this project, using JDBC application that access an installed RDBMS (you need to have an account), you will need to:
   Page 1 of 4\
• Create the Books database tables as specified in the schema definition below.\
• Initialize the different tables (at least 15 entries per table) appropriately: all
fields cannot be null.\
• Issue the following SQL statements. For queries print the results from java into your console:\
• Select all authors from the authors table. Order the information alphabetically by the author’s last name and first name.\
• Select all publishers from the publishers table.\
• Select a specific publisher and list all books published by that publisher. Include the title, year and ISBN number. Order the information alphabetically by title.\
• Add new author\
• Edit/Update the existing information about an author\
• Add a new title for an author\
• Add new publisher\
• Edit/Update the existing information about a publisher
