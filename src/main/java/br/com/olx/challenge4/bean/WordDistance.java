package br.com.olx.challenge4.bean;

public class WordDistance {
	
	private int distance;
	private String word1;
	private String word2;
	
	public WordDistance(String word1, String word2, int distance){
		this.word1 = word1;
		this.word2 = word2;
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}
	
}
