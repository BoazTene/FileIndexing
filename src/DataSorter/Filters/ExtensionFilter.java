package DataSorter.Filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DataSorter.MyFile;


/**
 * 
 * Filter file extenstion.
 * 
 * @author Itay Bar Nissim
 *
 */
public class ExtensionFilter implements Filter{
	private String query;
	private final String name = "ext";
	
	public ExtensionFilter(String query) {
		this.query = query;

	}
	
	public ExtensionFilter() {}
	
	@Override
	public void addIndex() {
		String extension = classify(this.query)[0];
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}
	
	public char numberToStringNumber(char chr) {
		final char[] unacceptableChars = {
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'-', '_', '=', '+', '[', '{', ']', '}', '\\', '|', '/', '?',
				'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
				'.', '>', ',', '<', ';', ':', '\'', '"'};
		
		final String[] values = {
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
		
		
		
		
		try {
			if (getIndexOf(chr, unacceptableChars) == -1) return chr;
			return values[getIndexOf(chr, unacceptableChars)].charAt(0);
		} catch (Exception e) {
			return 's';
		}		
	}
	
	public int getIndexOf(char toSearch, char[] tab ) {
		  for( int i=0; i< tab.length ; i ++ )
		    if( tab[ i ] == toSearch)
		     return i;

		  return -1;
	}
	
	@Override
	public String[] classify(String query) {
		query = query.substring(query.lastIndexOf('\\')+1);
		if (!query.contains(".")) return new String[] {"", "ext"};
		char[] extention = query.substring(query.lastIndexOf('.')+1).toCharArray();
		for (int i = 0; i < extention.length; i++) {
			extention[i] = numberToStringNumber(extention[i]);
		}
		return new String[]{new String(extention).replaceAll(" ", ""), "ext"};
	}

	@Override
	public String getName() {
		return this.name;
	}
}

