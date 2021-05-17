package DataSorter.Filters;

/**
 * 
 * This class is a score filter, that filters by the extension of the file.
 *
 * 
 * @author Itay Bar Nissim
 *
 */
public class ExtensionFilter implements Filter{
	private static final String name = "ext";
	
	public ExtensionFilter() {}


	/**
	 * This method gets a char and returns the first letter of the name of the char.
	 *
	 * We need this function because not all the character can be used in the table name, so we replace them to the first letter of their name.
	 *
	 * @param chr The char.
	 * @return The first letter of the char name
	 */
	public char numberToStringNumber(char chr) {

		try {
			if (getIndexOf(chr, unacceptableChars) == -1) return chr;
			return values[getIndexOf(chr, unacceptableChars)].charAt(0);
		} catch (Exception e) {
			return 's';
		}		
	}

	/**
	 * This method finds the index of a char in a char array.
	 *
	 * If the char not in the array the method will return -1.
	 *
	 * @param toSearch The char element.
	 * @param array The array
	 * @return The index of char in the array.
	 */
	public int getIndexOf(char toSearch, char[] array ) {
		  for( int i = 0; i < array.length; i++ )
		    if (array[i] == toSearch)
		     return i;

		  return -1;
	}

	/**
	 * The method gets a path to file and returns the name of the table which it contains.
	 *
	 * @param query - A Path to a file.
	 * @return - A array of the classify result and the filter extension: 'ext' (extension )
	 */
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

	/**
	 *
	 * @return returns the name of the extension: 'ext'.
	 */
	@Override
	public String getName() {
		return name;
	}
}

