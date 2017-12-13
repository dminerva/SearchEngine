public class Data {
	double value;
	int row;
	int col;
	
	public Data(double value, int row, int col) {
		this.value = value;
		this.row = row;
		this.col = col;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "[value=" + value + ", row=" + row + ", col=" + col + "]";
		//return "" + value + "";
	}
}