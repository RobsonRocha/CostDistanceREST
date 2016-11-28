package br.com.olx.challenge4.test.mock;

import java.util.List;

import br.com.olx.challenge4.bean.WordDistance;
import br.com.olx.challenge4.main.MainRest;

public class RestMock {
	
	public String insertWord(String word){
		MainRest mr = new MainRest();
		return mr.insertWord(word);
	}
	 
	public List<String> getWords(){
		MainRest mr = new MainRest();
		return mr.getWords();
	}
	
	public List<WordDistance> getMinDistance(String word, Integer limit){
		MainRest mr = new MainRest();
		return mr.getMinDistance(word, limit);
	}
	
	
}
