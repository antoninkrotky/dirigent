package org.dirigent.executor;

import java.text.Normalizer;

public class TemplateUtils {

	public static String normalizeString(String text) {
		String temp = Normalizer.normalize(text, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		return temp.toUpperCase().replace(" ", "_");
	}
}
