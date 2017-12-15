import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	
	WebCrawler() {
		
	}
	
	public ArrayList<String> getPageLinks(String URL) {
		ArrayList<String> results = new ArrayList<String>();
		
		try {
			Document document = Jsoup.connect(URL).get();
			Elements linksOnPage = document.select("a[href]");
			
			System.out.println("URL: " + URL + " - links too: ");
			
			results.add(URL);
			
            for (Element page : linksOnPage) {
            	System.out.println(page.attr("abs:href"));
                results.add(page.attr("abs:href"));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return results;
	}
	
	public void crawlWeb(String URL) {
		ArrayList<String> URLList = new ArrayList<String>();
		ArrayList<String> crawled = new ArrayList<String>();
		ArrayList<String> results = new ArrayList<String>();
		
		URLList.add(URL);
		
		while(!URLList.isEmpty()) {			
			if(!crawled.contains(URLList.get(0))) {
				crawled.add(URLList.get(0));
				
				results = this.getPageLinks(URLList.get(0));
				//System.out.println("results: " + results.toString());
								
				//insert into database
				
				URLList.remove(0);
			}
						
			for(int i = 1; i < results.size(); i++) {
				if(!crawled.contains(results.get(i))) {
					URLList.add(results.get(i));
				}
			}
		}
	}
}
