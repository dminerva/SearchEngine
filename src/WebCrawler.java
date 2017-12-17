import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private ArrayList<String> URLList = new ArrayList<String>();
	private ArrayList<String> crawled = new ArrayList<String>();
	private DataManager dm = new DataManager();
	
	WebCrawler() {
		
	}
	
	//returns an array list where the first elm is the url and the following are the urls it points too
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
		ArrayList<String> results = new ArrayList<String>();
		int count = 0;
		
		//load up first url to crawl
		URLList.add(URL);
		
		//until no more urls to crawl
		while(!URLList.isEmpty() && count < 26) {
			count++;
			results.clear();
			results = this.getPageLinks(URLList.get(0));
			
			//insert into database
			for(int i = 1; i < results.size(); i++) {
				dm.insert(results.get(0), results.get(i));
			}
			
			if(results.size() == 1) {
				dm.insert(results.get(0), "NULL");
			}
			
			crawled.add(URLList.get(0));
			
			for(int i = 1; i < results.size(); i++) {
				if(!URLList.contains(results.get(i)) && !crawled.contains(results.get(i))) {
					URLList.add(results.get(i));
				}
			}
			
			URLList.remove(0);
		}
	}
}
