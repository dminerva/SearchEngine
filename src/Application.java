import java.util.LinkedList;

public class Application {
	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler();
		DataManager dm = new DataManager();
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		
		dm.delete();
		wc.crawlWeb("http://localhost/WebCrawlTest/PageA.html");	
		
		
	}
}
