

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
	



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
*/
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*50, 
                 maxFileSize=1024*1024*100,      
                 maxRequestSize=1024*1024*500)   
public class UploadServlet extends HttpServlet {
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/logic";

	   //  Database credentials
	static final String USER = "root";
	static final String PASS = "";
    
    /**
     * handles file upload
     */
    ArrayList first = new ArrayList();
    ArrayList<String> innerSecond = new ArrayList<String>();
    ArrayList<ArrayList<String>> outerSecond = new ArrayList<ArrayList<String>>();
    	{
		   try{
			   
			   
			  
		 	     
			   XSSFRow row;
        FileInputStream fis = new FileInputStream( new File("data.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet1 = workbook.getSheetAt(0);
        XSSFSheet spreadsheet2 = workbook.getSheetAt(1);
        Iterator < Row > rowIterator1 = spreadsheet1.iterator();
        Iterator < Row > rowIterator2 = spreadsheet2.iterator();
        Connection conn = null;
		   Statement stmt = null;
		  
		      Class.forName(JDBC_DRIVER);
		      conn = DriverManager.getConnection(DB_URL,USER, PASS);
		      String sql, sql1, sql2, sql3, sql4;
		      sql1= "DROP TABLE IF EXISTS city;";
		      stmt.executeUpdate(sql1);
		      sql2 = "CREATE TABLE city (cityname varchar (50));";
		      stmt.executeUpdate(sql2);
		      sql3 = "DROP TABLE IF EXISTS route;";
		      stmt.executeUpdate(sql3);
		      sql4 = "CREATE TABLE route (source varchar (50), destination varchar (50), time int, serviceprovider varchar (50));";
		     stmt.executeUpdate(sql4);
		     sql = "INSERT INTO city (cityname) VALUES ('banglore')";
	 	      stmt.executeUpdate(sql);
        Cell cell1 = null,cell2;
        {
        	while (rowIterator1.hasNext()) 
        {
      	  
           row = (XSSFRow) rowIterator1.next();
           Iterator < Cell > cellIterator1 = row.cellIterator();
           
           while ( cellIterator1.hasNext()) 
           {
              cell1  = cellIterator1.next();                 
           }
           
           
           sql = "INSERT INTO city (cityname) VALUES ('"+cell1.getStringCellValue()+"')";
 	      stmt.executeUpdate(sql);
        }
        	
        	while (rowIterator2.hasNext()) 
        {
      	  Double x;
           row = (XSSFRow) rowIterator2.next();
           Iterator < Cell > cellIterator2 = row.cellIterator();
           while ( cellIterator2.hasNext()) 
           {
               cell2 = cellIterator2.next();
              
              if(cell2.getCellType() == Cell.CELL_TYPE_NUMERIC)
              {
              	 x = cell2.getNumericCellValue();
              	innerSecond.add(x.toString());
              }
              else
              {
              	innerSecond.add(cell2.getStringCellValue());
              }
              sql = "INSERT INTO route(source, destination, time, serciveprovider) VALUES ('"+cell1.getStringCellValue()+"','"+cell1.getStringCellValue()+"','"+cell1.getNumericCellValue()+"','"+cell1.getStringCellValue()+"')";
     	      stmt.executeUpdate(sql);
              
           }
                 
           
        }
        	try{
        	       if(conn!=null)
        	          conn.close();
        	    }catch(SQLException se){
        	       se.printStackTrace();
        	    }	
        fis.close();
  	   }
		   }
		   catch (Exception e){}
    }
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fileName = null;
        for (Part part : request.getParts()) {
             fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        
       // request.setAttribute("message", "Upload has been done successfully!");
        request.setAttribute("message", first);
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
        
        
        
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
}
    
