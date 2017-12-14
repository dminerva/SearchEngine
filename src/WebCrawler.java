import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private ArrayList<String> URLList = new ArrayList<String>();
		
	WebCrawler() {
		
	}
	
	public ArrayList<String> getPageLinks(String URL) {
		ArrayList<String> results = new ArrayList<String>();
		
		try {
			Document document = Jsoup.connect(URL).get();
			Elements linksOnPage = document.select("a[href]");
			
			//System.out.println("URL: " + URL + " - links too: ");
			
            for (Element page : linksOnPage) {
            	//System.out.println(page.attr("abs:href"));
                results.add(page.attr("abs:href"));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public void crawlWeb(String URL) {

				
	}
}
