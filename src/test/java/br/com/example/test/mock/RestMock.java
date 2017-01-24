package br.com.example.test.mock;

import java.util.List;

import br.com.example.main.MainRest;
import br.com.example.pojo.WordDistance;

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
