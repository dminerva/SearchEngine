import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	//get urls cralwed from the database
	public ArrayList<String> getURLs() {
		ArrayList<String> results = new ArrayList<String>();
		
		try {
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM crawl_results");
			
			while(myRs.next()) {
				if(!results.contains(myRs.getString("url"))) {
					results.add(myRs.getString("url"));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	//gets pointing data
	public ArrayList<String> getPoints(String url) {
		ArrayList<String> results = new ArrayList<String>();
		
		try {
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM crawl_results WHERE url='" + url + "'");
			
			while(myRs.next()) {
				if(!results.contains(myRs.getString("url"))) {
					results.add(myRs.getString("points_too"));
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public ArrayList<Data> getData() {
		ArrayList<Data> results = new ArrayList<Data>();

		
		
		return results;
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


