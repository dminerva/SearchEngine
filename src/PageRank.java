import java.util.LinkedList;

public class PageRank {
	private LinkedList<LinkedList<Data>> matrix = new LinkedList<LinkedList<Data>>();
	private LinkedList<Data> vector = new LinkedList<Data>();
	private LinkedList<Data> error = new LinkedList<Data>();
	private int size;
	private double epoc = 0.01;
	private double beta = 0.85;

	PageRank(LinkedList<LinkedList<Data>> m, int s) {
		matrix = m;
		size = s;
		
		for(int i = 0; i < size; i++) {			
			vector.add(new Data(1.0 / size, i + 1, 1));
			error.add(new Data(1.0, i, 1));
		}
	}	
	
	public LinkedList<Data> calcSMVMultiplication() {
		LinkedList<Data> result = new LinkedList<Data>();
		double sum2 = 0.0;

		//for each row of matrix
		for(int i = 0; i < matrix.size(); i++) {
			double sum = 0.0;			
			//for each column
			for(int j = 0; j < matrix.get(i).size(); j++) {
				//loop through vector for matching matrix col and vector row
				for(int k = 0; k < vector.size(); k++) {
					if(matrix.get(i).get(j).getCol() == vector.get(k).getRow()) {
						sum += matrix.get(i).get(j).getValue() * vector.get(k).getValue();
					}
				}
			}
			// adjust rank BMR + (1-B)/N
			sum = (beta * sum)  + ((1 - beta) / vector.size());
			//System.out.println(beta + " * " + sum + " + " + "((" + 1 + " - " + beta + ")/ " + vector.size());
			//System.out.println("rank adjustment: " + sum);			
			
			result.add(new Data(sum, i + 1, 1));
		}
		
		//check for leaks
		for(int x = 0; x < result.size(); x++) {
			sum2 += result.get(x).getValue();
		}			
		//System.out.println("leak check: " + sum2);	
		
		if( sum2 < 1.0) {
			double leak;
			
			leak = (1 - sum2) / result.size();
			
			for(int y = 0; y < vector.size(); y++) {
				result.get(y).setValue(result.get(y).getValue() + leak);
			}
		}
		
		return result;
	}
	
	public LinkedList<Data> calcPageRank() {
		LinkedList<Data> previousVector = new LinkedList<Data>();
		double change = 1.0;
		
		while (epoc < change) {
			vector = this.calcSMVMultiplication();
			System.out.println(vector.toString());
			
			if(!previousVector.isEmpty()) {
				double sum = 0.0;
				
				//calculate change
				for(int i = 0; i < vector.size(); i++) {
					error.get(i).setValue(Math.pow(previousVector.get(i).getValue() - vector.get(i).getValue(), 2));
					//error.get(i).setValue(Math.abs(previousVector.get(i).getValue() - vector.get(i).getValue()));
				}
				
				for(int j = 0; j < error.size(); j++) {					
					sum += error.get(j).getValue() / error.size();
				}
				
				change = sum;
			}			
			previousVector = vector;
		}
		
		return vector;
	}
}
