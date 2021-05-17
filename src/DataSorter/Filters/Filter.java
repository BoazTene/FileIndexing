package DataSorter.Filters;

import java.sql.SQLException;


/**
 * this is an interface which is used for building each filter
 * @author -  itay Bar Nissim
 */
public interface Filter {
	public static final char[] unacceptableChars = {
			'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
			'-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', '/', '?',
			'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
			'.', '>', ',', '<', ';', ':', '\'', '"'};

	public static final String[] values = {
			"one",
			"two",
			"three",
			"four",
			"five",
			"six",
			"seven",
			"eight",
			"nine",
			"zero",
			"hyphen",
			"dash",
			"equal",
			"plus",
			"obrackets",
			"obraces",
			"cbrackets",
			"cbraces",
			"backslash",
			"vertical",
			"slash",
			"quation",
			"ampersand",
			"exclamation",
			"atsign",
			"hashtag",
			"dolar",
			"precentage",
			"power",
			"and",
			"mul",
			"oparentheses",
			"cparentheses",
			"",
			"bigger",
			"comma",
			"less",
			"semicolon",
			"colon",
			"apostrophe",
			"ellipsis"
	};
	
	String[] classify(String query);
	String getName();

}
