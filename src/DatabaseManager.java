import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	private String dbUrl = "jdbc:mysql://localhost/web_crawl_data";
	private String user = "root";
	private String pass = "";
	private Connection myConn;
	
	DatabaseManager() {
		//get connection
		try {
			myConn = DriverManager.getConnection(dbUrl, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insert
	public void insert(String url1, String url2) {
		try {
			Statement myStmt = myConn.createStatement();
			
			String sql = "INSERT INTO crawl_results (id, url, points_too) VALUES (NULL, '"+url1+"', '"+url2+"')";
			
			myStmt.executeUpdate(sql);
			
			System.out.println("insert complete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//delete
	public void delete() {
		try {
			Statement myStmt = myConn.createStatement();
			
			String sql = "DELETE FROM crawl_results";
			
			myStmt.executeUpdate(sql);
			
			System.out.println("data deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//select all
	public void selectAll() {		
		
		try {
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM crawl_results");		
			
			while(myRs.next()) {
				System.out.println(myRs.getString("id") + " - " + myRs.getString("url") + " - " + myRs.getString("points_too"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	}

}


