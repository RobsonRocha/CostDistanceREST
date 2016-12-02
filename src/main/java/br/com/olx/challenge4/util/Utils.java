package br.com.olx.challenge4.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {
	
	public static String translate(String str){
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
        
		//return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static int getDistance(String str1, String str2) {

		int matrix[][] = new int[str1.length() + 1][str2.length() + 1];
		int cost = 0;

		for (int i = 0; i < str1.length() + 1; i++)
			matrix[i][0] = i;

		for (int i = 0; i < str2.length() + 1; i++)
			matrix[0][i] = i;

		for (int i = 1; i < str1.length() + 1; i++)
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1))
					cost = 0;
				else
					cost = 1;

				matrix[i][j] = Math.min(
						Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1),
						matrix[i - 1][j - 1] + cost);
			}

		return matrix[str1.length()][str2.length()];

	}
}
