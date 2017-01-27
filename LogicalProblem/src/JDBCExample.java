
//STEP 1. Import required packages
import java.sql.*;
public class JDBCExample {
 // JDBC driver name and database URL
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 static final String DB_URL = "jdbc:mysql://localhost/logic";

 //  Database credentials
 static final String USER = "root";
 static final String PASS = "";
 
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
    //STEP 2: Register JDBC driver
    Class.forName(JDBC_DRIVER);
    String sql1, sql2, sql3, sql4, sql;
    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER, PASS);

    //STEP 4: Execute a query
    System.out.println("Opening database...");
    stmt = conn.createStatement();
    System.out.println("Creating Table...");
     sql1= "DROP TABLE IF EXISTS city;";
     stmt.executeUpdate(sql1);
     sql2 = "CREATE TABLE city (cityname varchar (50));";
     stmt.executeUpdate(sql2);
     sql3 = "DROP TABLE IF EXISTS route;";
     stmt.executeUpdate(sql3);
     sql4 = "CREATE TABLE route (source varchar (50), destination varchar (50), time int, serviceprovider varchar (50));";
    stmt.executeUpdate(sql4);
    System.out.println("table created successfully...");
    System.out.println("table Insertion...");
    sql = "INSERT INTO city (cityname) VALUES ('abad')";
    stmt.executeUpdate(sql);
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("Goodbye!");
}//end main
}//end JDBCExample
