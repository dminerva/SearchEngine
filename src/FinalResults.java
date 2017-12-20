
public class FinalResults {
	String url;
	Double rank;
	
	public FinalResults(String url, Double rank) {;
		this.url = url;
		this.rank = rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "url: " + url + " - rank: " + rank;
	}	
}
