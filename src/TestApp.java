import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;

public class TestApp {

	public static void main(String[] args) {
		LinkedList<Data> row1 = new LinkedList<Data>();
		LinkedList<Data> row2 = new LinkedList<Data>();
		LinkedList<Data> row3 = new LinkedList<Data>();
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		int numURL = 3;
		
		row1.add(new Data(0.5, 1, 1));
		row1.add(new Data(0.5, 1, 2));
		//row1.add(new Data(0, 1, 3));		
		row2.add(new Data(0.5, 2, 1));
		//row2.add(new Data(0, 2, 2));
		row2.add(new Data(1.0, 2, 3));		
		//row3.add(new Data(0, 3, 1));
		row3.add(new Data(0.5, 3, 2));
		//row3.add(new Data(0, 3, 3));
		
		System.out.println(row1.toString());
		System.out.println(row2.toString());
		System.out.println(row3.toString());
		
		matrix.add(row1);
		matrix.add(row2);
		matrix.add(row3);
		
		PageRank pr = new PageRank(matrix, numURL);
		//System.out.println(pr.calcSMVMultiplication().toString());
		//pr.calcPageRank();
		
		DataManager dm = new DataManager();
		//dm.insert("test insert1", "test insert 2");
		//dm.selectAll();
		//dm.delete();
		//System.out.println(dm.getURLs().toString());
		//System.out.println("number of urls: " + dm.getURLs().size());
		//System.out.println(dm.getPointingData("http://localhost/WebCrawlTest/PageA.html").toString());
		System.out.println(dm.getData());
		//System.out.println(dm.getMatrix(dm.getData()));
		//dm.getMatrix(dm.getData());
		
		WebCrawler wc = new WebCrawler();
		//wc.getPageLinks("http://localhost/WebCrawlTest/PageA.html");
		//wc.crawlWeb("http://localhost/WebCrawlTest/PageA.html");
		//wc.crawlWeb("http://facebook.com");
		
	}

}
