package br.com.example.main;

import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.postgresql.util.PSQLException;

import br.com.example.connection.DBConnection;
import br.com.example.pojo.WordDistance;
import br.com.example.util.Utils;

@Path("/main")
public class MainRest {

	@GET
	@Path("/insertword/{name}")
	@Produces("text/plain")
	public String insertWord(@PathParam("name") String word) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into distancia.palavras (termo) values (?) ");
		Connection con = null;
		PreparedStatement ps = null;
		String result = "";
		
		String newWord = Utils.translate(word
				.toUpperCase().trim());		
		try {
			
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());
			
			ps.setString(1, newWord);
			ps.executeUpdate();			
			
			result = String.format(
					"A palavra %s foi cadastrada com sucesso!!!", newWord);
			return result;

		} catch (PSQLException e) {
			if (e.getMessage().contains("duplicate key value")) {
				System.out.println(String.format("A palavra %s já existe!!!",
						newWord));
				e.printStackTrace();
				throw new WebApplicationException(Response
						.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.entity(String.format("A palavra %s já existe!!!", newWord)).build());
			} else {
				System.out.println("Erro inesperado!!!");
				e.printStackTrace();
				throw new WebApplicationException(Response
						.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.entity("Erro inesperado!!!").build());
			}
		} catch (SQLException e) {
			System.out.println("Erro com o banco de dados.");
			e.printStackTrace();
			throw new WebApplicationException(Response
					.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("Erro com o banco de dados.").build());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fehar o banco de dados.");
				e.printStackTrace();
				throw new WebApplicationException(Response
						.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.entity("Erro ao fechar o banco de dados.").build());
			}

		}
	}

	@GET
	@Path("/getallwords")
	@Produces("application/json")
	public List<String> getWords() {

		StringBuilder sql = new StringBuilder();
		sql.append("select termo from distancia.palavras ");
		Connection con = null;
		PreparedStatement ps = null;
		List<String> result = new ArrayList<String>();
		ResultSet rs = null;
		
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("termo"));
			}

			return result;

		} catch (SQLException e) {
			throw new WebApplicationException(Response
					.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("Erro com o banco de dados!!!").build());
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				throw new WebApplicationException(Response
						.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.entity("Erro ao fechar o banco de dados!!!").build());
			}

		}
	}

	@GET
	@Path("/getmindistance")
	@Produces("application/json")
	public List<WordDistance> getMinDistance(@QueryParam("name") String word,
			@QueryParam("threshold") Integer limit) {

		if (word == null) {
			throw new WebApplicationException(Response
					.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("O parâmetro name é obrigatório!!!").build());
		}
		
		String newWord = Utils.translate(word
				.toUpperCase().trim());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select termo from distancia.palavras ");
		Connection con = null;
		PreparedStatement ps = null;
		List<WordDistance> result = new ArrayList<WordDistance>();
		ResultSet rs = null;

		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				String word2 = rs.getString("termo");
				int distance = Utils.getDistance(
						newWord, word2);

				if (limit == null && distance <= 3) {
					WordDistance wd = new WordDistance(newWord, word2, distance);
					result.add(wd);
				} else if (limit != null && distance <= limit) {
					WordDistance wd = new WordDistance(newWord, word2, distance);
					result.add(wd);
				}
			}

			return result;

		} catch (SQLException e) {
			System.out.println("Erro com o banco de dados.");
			e.printStackTrace();
			throw new WebApplicationException(Response
					.status(HttpURLConnection.HTTP_BAD_REQUEST)
					.entity("Erro com o banco de dados").build());
		} finally {
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o banco de dados.");
				e.printStackTrace();
				throw new WebApplicationException(Response
						.status(HttpURLConnection.HTTP_BAD_REQUEST)
						.entity("Erro ao fechar o banco de dados.").build());
			}

		}
	}

}
