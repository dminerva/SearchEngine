import java.util.LinkedList;

public class Application {
	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler();
		DataManager dm = new DataManager();
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		int size;
		PageRank pr;
		LinkedList<Data> results;
		
		dm.delete();
		wc.crawlWeb("http://localhost/WebCrawlTest2/PageI.html");
		
		System.out.println("\n");
		System.out.println("MATRIX");
		
		matrix = dm.getMatrix(dm.getData());
		size = dm.getURLs().size();		
		pr = new PageRank(matrix, size);
		
		
		System.out.println("\n");
		System.out.println("CALCULATING RANK");
		
		results = pr.calcPageRank();		
		//System.out.println(dm.getURLs().toString());
		
		System.out.println("\n");
		System.out.println("RESULTS");
		
		for(int i = 0; i < results.size(); i++) {
			System.out.println("url: " + dm.getURLs().get(i) + " - rank: " + results.get(i).getValue());
		}
		
	}
}
