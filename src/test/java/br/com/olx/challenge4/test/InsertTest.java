package br.com.olx.challenge4.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import br.com.olx.challenge4.bean.WordDistance;
import br.com.olx.challenge4.db.DBConnection;
import br.com.olx.challenge4.test.mock.RestMock;
import br.com.olx.challenge4.test.util.Utils;

public class InsertTest {
	
	private String randomWord;
	
	@AfterMethod
	private void deleteWord() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("delete from olx_desafio4.palavras where termo = ? ");
		Connection con = null;
		PreparedStatement ps = null;
		
		String newWord = randomWord
				.toUpperCase().trim();		
		try {
			
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			
			ps.setString(1, newWord);
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("Erro com o banco de dados.");
			e.printStackTrace();
			throw new Exception("Erro com o banco de dados.");
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fehar o banco de dados.");
				e.printStackTrace();
				throw new Exception("Erro ao fechar o banco de dados.");
			}

		}
	}
	
	@Test
	public void insertWordTest() throws Exception{
		RestMock rm = new RestMock();
		randomWord = Utils.randomWord();
		String answer = rm.insertWord(randomWord);
		List<WordDistance> wd = rm.getMinDistance(randomWord, 0);
		Assert.assertTrue(wd.get(0).getWord2().equals(randomWord.toUpperCase()));
		Assert.assertTrue(answer.contains("sucesso"));		
	}
	
	@Test(expectedExceptions=WebApplicationException.class)
	public void insertAWordThatAlreadyExistsTest() throws Exception{
		RestMock rm = new RestMock();
		randomWord = Utils.randomWord();
		rm.insertWord(randomWord);
		rm.insertWord(randomWord);		
	}
	
	@Test
	public void insertWordWithSpecialCharacterTest() throws Exception{
		RestMock rm = new RestMock();
		randomWord = Utils.randomWord();
		String randomWordWithoutSpecialCharacter = randomWord + "cao";
		randomWord +="ção";
		String answer = rm.insertWord(randomWord);
		List<WordDistance> wd = rm.getMinDistance(randomWordWithoutSpecialCharacter, 0);
		randomWord = randomWordWithoutSpecialCharacter;
		Assert.assertTrue(answer.contains("sucesso"));
		Assert.assertEquals(wd.size(), 1);
		Assert.assertEquals(wd.get(0).getDistance(), 0);
		Assert.assertFalse(wd.get(0).getWord2().equals(randomWord));
		Assert.assertTrue(wd.get(0).getWord2().equals(randomWordWithoutSpecialCharacter.toUpperCase()));
		
	}
}
