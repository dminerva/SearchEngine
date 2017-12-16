import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DataManager {
	private String dbUrl = "jdbc:mysql://localhost/web_crawl_data";
	private String user = "root";
	private String pass = "";
	private Connection myConn;
	
	DataManager() {
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
			
			//System.out.println("insert complete");
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
		ArrayList<String> crawled = getURLs();
		
		//where crawled is the url and points are the outgoing links for that url
		for(int i = 0; i < crawled.size(); i++) {
			ArrayList<String> points = getPoints(crawled.get(i));
			double value = 1.0 / points.size();
			
			for(int j = 0; j < points.size(); j++) {
				for(int k = 0; k < crawled.size(); k++) {
					if(crawled.get(k).equals(points.get(j))) {
						results.add(new Data(value, k + 1, i + 1));
					}
					
				}
			}
		}
		
		return results;
	}
	
	public LinkedList<LinkedList<Data>> getMatrix(ArrayList<Data> data) {
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		ArrayList<Integer> rowNum = new ArrayList<Integer>();
		
		for(int i = 0; i < data.size(); i++) {			
			if(!rowNum.contains(data.get(i).getRow())) {
				rowNum.add(data.get(i).getRow());
			}
		}
		
		Collections.sort(rowNum);
		
		for(int j = 0; j < rowNum.size(); j++) {
			matrix.add(new LinkedList<Data>());
			
			for(int k = 0; k < data.size(); k++) {
				if(rowNum.get(j).equals(data.get(k).getRow())) {
					matrix.get(j).add(data.get(k));
				}
			}
		}
		
		/*for(int j = 0; j < rowNum.size(); j++) {
			matrix.add(new ArrayList<Data>());
			
			for(int k = 0; k < data.size(); k++) {
				if(rowNum.get(j) == data.get(k).getRow()) {
					matrix.get(j).add(data.get(k));
				}
			}
		}*/
		
		for(int l = 0; l < matrix.size(); l++) {
			System.out.println(matrix.get(l).toString());
		}
		
		return matrix;
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


