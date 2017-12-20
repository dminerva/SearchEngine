import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Application {
	public static void main(String[] args) {
		WebCrawler wc = new WebCrawler();
		DataManager dm = new DataManager();
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		int size;
		PageRank pr;
		LinkedList<Data> results;
		ArrayList<FinalResults> fr = new ArrayList<FinalResults>();
		
		System.out.println("\n==================== WEB CRAWL ====================\n");
		
		dm.delete();
		wc.crawlWeb("http://localhost/WebCrawlTest3/PageA.html");
		
		System.out.println("\n==================== MATRIX ====================\n");
		
		matrix = dm.getMatrix(dm.getData());
		size = dm.getURLs().size();		
		pr = new PageRank(matrix, size);
		
		System.out.println("\n==================== STARTING VECTOR ====================\n");
		
		System.out.println(pr.getStartVector().toString());		
		
		System.out.println("\n==================== CALCULATING RANK ====================\n");
		
		results = pr.calcPageRank();		
		//System.out.println(dm.getURLs().toString());
		
		System.out.println("\n==================== RESULTS ====================\n");
		
		for(int i = 0; i < results.size(); i++) {
			System.out.println("url: " + dm.getURLs().get(i) + " - rank: " + results.get(i).getValue());
			fr.add(new FinalResults(dm.getURLs().get(i), results.get(i).getValue()));
		}
		
		System.out.println("\n==================== RANKING ====================\n");
		
		Collections.sort(fr, Comparator.comparing(FinalResults::getRank));
		Collections.reverse(fr);
		
		for(int j = 0; j < fr.size(); j++) {
			fr.get(j).setRank(j + 1.0);
			System.out.println(fr.get(j).toString());
		}
	}
}
