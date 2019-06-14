package it.objectmethod.cercanazionedb.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CercaNazioneServlet extends HttpServlet {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/world";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "rootroot";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String nazione = request.getParameter("nazione");
		String risultato=null;
		
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      String sql = "SELECT Code,Capital FROM world.country WHERE Name = ?";
		      stmt = conn.prepareStatement(sql);
		      
		      //String code = "ITA";
		      stmt.setString(1, nazione);
		      
		      ResultSet rs = stmt.executeQuery();

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         String code = rs.getString("Code");
		         String capital = rs.getString("Capital");
		         risultato = code+" "+capital;

		         //Display values
		         System.out.print("Code: " + code);
		         System.out.print(", Capital: " + capital);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
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
		
		request.setAttribute("risultato", risultato);
		request.getRequestDispatcher("/Home.jsp").forward(request, response);

	}

}
