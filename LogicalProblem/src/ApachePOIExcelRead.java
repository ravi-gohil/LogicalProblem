
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ApachePOIExcelRead 
{
   static XSSFRow row;
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/logic";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   public static void main(String[] args) throws Exception 
   {
	   try{
		   
		   
		  
		   Connection conn = null;
		   Statement stmt = null;
		  
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);
		      String sql1, sql2, sql3, sql4, sql, val;
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER, PASS);

		      //STEP 4: Execute a query
		      System.out.println("Opening database...");
		      stmt = conn.createStatement();
		      System.out.println("table Insertion...");
		      
      FileInputStream fis = new FileInputStream( new File("data.xlsx"));
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet spreadsheet1 = workbook.getSheetAt(0);
      XSSFSheet spreadsheet2 = workbook.getSheetAt(1);
      Iterator < Row > rowIterator1 = spreadsheet1.iterator();
      Iterator < Row > rowIterator2 = spreadsheet2.iterator();
      ArrayList first = new ArrayList();
      ArrayList<String> innerSecond = new ArrayList<String>();
      ArrayList<ArrayList<String>> outerSecond = new ArrayList<ArrayList<String>>();
      Cell cell1 = null, cell2 = null;
      while (rowIterator1.hasNext()) 
      {
    	  
         row = (XSSFRow) rowIterator1.next();
         Iterator < Cell > cellIterator1 = row.cellIterator();
         
         while ( cellIterator1.hasNext()) 
         {
            cell1  = cellIterator1.next();
           
            	
              
               System.out.print(
               cell1.getStringCellValue() + " \t \t " );
               
         }
        val = cell1.getStringCellValue();
         //first.add(cell1.getStringCellValue());
       // System.out.print(val);
        sql = "INSERT INTO city (cityname) VALUES ('"+cell1.getStringCellValue()+"')";
	      stmt.executeUpdate(sql);
      }
      try{
          if(conn!=null)
             conn.close();
       }catch(SQLException se){
          se.printStackTrace();
       }
      System.out.println();
      
     /*System.out.println(first);
      
      	while (rowIterator2.hasNext()) 
      {
    	  Double x;
         row = (XSSFRow) rowIterator2.next();
         Iterator < Cell > cellIterator2 = row.cellIterator();
         while ( cellIterator2.hasNext()) 
         {
             cell2 = cellIterator2.next();
            switch (cell2.getCellType()) 
            {
               case Cell.CELL_TYPE_NUMERIC:
            	
               System.out.print( 
               cell2.getNumericCellValue() + " \t \t " );
               
               break;	
               case Cell.CELL_TYPE_STRING:
            	  
               System.out.print(
               cell2.getStringCellValue() + " \t \t " );
               break;
               
            }
            if(cell2.getCellType() == Cell.CELL_TYPE_NUMERIC)
            {
            	 x = cell2.getNumericCellValue();
            	innerSecond.add(x.toString());
            }
            else
            {
            	innerSecond.add(cell2.getStringCellValue());
            }
            
            
         }
               
         System.out.println();
      }
      	outerSecond.add(innerSecond);
      	 System.out.println(outerSecond);
  */    fis.close();
	   }
	   catch(Exception e)
	   {
	   }
	   }
   }
