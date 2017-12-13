import java.util.LinkedList;

public class PageRank {
	LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
	LinkedList<Data> vector = new LinkedList<Data>();
	LinkedList<Data> error = new LinkedList<Data>();
	int size;
	private double epoc = 0.01;

	PageRank(LinkedList<LinkedList<Data>> m, int s) {
		matrix = m;
		size = s;
		
		for(int i = 0; i < size; i++) {			
			vector.add(new Data(1.0 / size, i, 1));
			error.add(new Data(1.0, i, 1));
		}
	}	
	
	public LinkedList<Data> calculateMatrixVector() {
		LinkedList<Data> result = new LinkedList<Data>();

		//for each row of matrix
		for(int i = 0; i < matrix.size(); i++) {
			double sum = 0.0;
			//for each column
			for(int j = 0; j < matrix.get(i).size(); j++) {
				//System.out.println("row: " + i + "has this many non zero columns: " + matrix.get(i).size());
				//loop through vector for matching matrix col and vector row
				for(int k = 0; k < vector.size(); k++) {
					if(matrix.get(i).get(j).getCol() == vector.get(k).getRow()) {
						System.out.println(matrix.get(i).get(j).toString() + " * " + vector.get(k).toString() + "\n");
						//System.out.println(matrix.get(i).get(1).toString());
						sum += matrix.get(i).get(j).getValue() * vector.get(k).getValue();
					}
				}
			}
			result.add(new Data(sum, i + 1, 1));
		}
		
		return result;
	}
}
