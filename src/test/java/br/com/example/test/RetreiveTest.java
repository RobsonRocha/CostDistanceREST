package br.com.example.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.example.pojo.WordDistance;
import br.com.example.test.mock.RestMock;
import br.com.example.test.util.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RetreiveTest {
	
	private static final String REST_URL = "http://localhost:8080/CostDistanceRest/main/";

	@Test
	public void getMinDistanceWithThresholdTest() throws Exception {
		RestMock rm = new RestMock();
		List<String> listWords = rm.getWords();
		int position = (int) Math.random() * (listWords.size() - 1);

		String word = listWords.get(position);

		List<WordDistance> listWD = rm.getMinDistance(word, 5);

		for (WordDistance wd : listWD) {
			int compare = Utils.levenshteinDistance(word, wd.getWord2());
			Assert.assertEquals(compare, wd.getDistance());
			Assert.assertTrue(wd.getDistance() <= 5);
		}
	}

	@Test
	public void getMinDistanceWithoutThresholdTest() throws Exception {
		RestMock rm = new RestMock();
		List<String> listWords = rm.getWords();
		int position = (int) Math.random() * (listWords.size() - 1);

		String word = listWords.get(position);

		List<WordDistance> listWD = rm.getMinDistance(word, null);

		for (WordDistance wd : listWD) {
			int compare = Utils.levenshteinDistance(word, wd.getWord2());
			Assert.assertEquals(compare, wd.getDistance());
			Assert.assertTrue(wd.getDistance() <= 3);
		}
	}

	// Para esse teste o servi�o deve estar no ar

	@Test
	public void getAllWordsUsingRestServiceTest() throws Exception {

		URL url = new URL(REST_URL + "getallwords");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		List<String> answer = new ArrayList<String>();
		while ((output = br.readLine()) != null) {
			Gson gson = new Gson();
			answer = gson.fromJson(output, new TypeToken<List<String>>(){}.getType());
		}

		conn.disconnect();

		Assert.assertTrue(!answer.isEmpty());

	}
}
