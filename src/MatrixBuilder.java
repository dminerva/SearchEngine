import java.util.ArrayList;
import java.util.LinkedList;

public class MatrixBuilder {
	private DatabaseManager dm = new DatabaseManager();
	
	MatrixBuilder() {
		
	}
	
	public LinkedList<LinkedList<Data>> build() {
		LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
		LinkedList<LinkedList<String>> d = new LinkedList<LinkedList<String>>();
		ArrayList <String> crawled = dm.getURLs();
		ArrayList <String> points = new ArrayList <String>();
		
		//give matrix its dimensions
		for(int x = 0; x < crawled.size(); x++) {
			matrix.add(new LinkedList<Data>());
			d.add(new LinkedList<String>());
		}
		
		for(int i = 0; i < crawled.size(); i++) {
			
		}
		
		//columns
		/*for(int i = 0; i < crawled.size(); i++) {
			points.clear();
			points = dm.getPointingData(crawled.get(i));
			System.out.println(" points: " + points.toString());
			
			//rows
			for(int j = 0; j < crawled.size(); j++) {
				for(int k = 0; k < points.size(); k++) {
					System.out.println("crawled: " + crawled.get(j) + " - points: " + points.get(k));
					if(crawled.get(j) == points.get(k)) {
						matrix.get(j).add(new Data(1.0 / points.size(), j + 1, i + 1));
					}
				}
			}
		}*/
		
		return matrix;	
	}
}
