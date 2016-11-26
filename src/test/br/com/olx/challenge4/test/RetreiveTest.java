package br.com.olx.challenge4.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.olx.challenge4.bean.WordDistance;
import br.com.olx.challenge4.test.mock.RestMock;
import br.com.olx.challenge4.test.util.Utils;

public class RetreiveTest {
	
	@Test
	public void getMinDistanceWithThreshold() throws Exception{
		RestMock rm = new RestMock();
		List<String> listWords = rm.getWords();
		int position = (int) Math.random()*(listWords.size()-1);
		
		String word = listWords.get(position);
		
		List<WordDistance> listWD = rm.getMinDistance(word, 5);
		
		for(WordDistance wd : listWD){
			int compare = Utils.levenshteinDistance(word, wd.getWord2());
			Assert.assertEquals(compare, wd.getDistance());	
			Assert.assertTrue(wd.getDistance() <= 5);
		}				
	}
	
	@Test
	public void getMinDistanceWithoutThreshold() throws Exception{
		RestMock rm = new RestMock();
		List<String> listWords = rm.getWords();
		int position = (int) Math.random()*(listWords.size()-1);
		
		String word = listWords.get(position);
		
		List<WordDistance> listWD = rm.getMinDistance(word, null);
		
		for(WordDistance wd : listWD){
			int compare = Utils.levenshteinDistance(word, wd.getWord2());
			Assert.assertEquals(compare, wd.getDistance());		
			Assert.assertTrue(wd.getDistance() <= 3);
		}				
	}
}
