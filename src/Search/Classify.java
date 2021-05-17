package Search;

import java.util.Arrays;

import DataSorter.Filters.*;

/**
 * 
 * This class gets the table name of query after given filters.
 * 
 * @author Boaz Tene
 *
 */
public class Classify {
	private static final int numberOfCharacterInAlphabet = 26;
	private static final char[][] num_val={{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'},
			{1, 2, 3, 4, 5, 8, 3, 5, 1, 1, 2, 3, 4, 5, 7, 8, 1, 2, 3, 4, 6, 6, 6, 5, 1, 7}};
	private final Filter[] filters;
	private final String query;
	
	/**
	 * This is the class constructor, it initializing the filters, query fields.
	 *
	 * 
	 * @param filters - An array of all the filters to be used on the query
	 * @param query - A String (File path) to be classified
	 */
	public Classify(Filter[] filters, String query) {
		 this.filters = filters;
		 this.query = query;
	}
	
	/**
	 * This method go through the filters array and add them to Table name.
	 * 
	 * @return - String the table name.
	 */
	public String GetTableNameByFilters() {

		String[][] classifyResult = new String[this.filters.length][2];
		
		for (int i = 0; i < this.filters.length; i++) {
			classifyResult[i] = this.filters[i].classify(query);
		}
		
		return organizeClassifyResult(classifyResult);
	}
	
	
	/**
	 * Organize the list by numerology order.
	 * 
	 * @param array - The Classify result
	 * @return - The Table Name.
	 */
	private String organizeClassifyResult(String[][] array) {
		int[][] result = new int[array.length][2];
		
		for (int i = 0; i < array.length; i++) {
			
			char[] temp = array[i][0].toCharArray();
			result[i][0] = 0;
			result[i][1] = i;

			for (char c : temp) {
				result[i][0] += findNumerology(c);
			}
		}
		
	    int[] temp;
		
		 for (int i = 0; i < result.length; i++) {     
	          for (int j = i+1; j < result.length; j++) {     
	              if (result[i][1] > result[j][1]) {      //swap elements if not in order
	                 temp = result[i];    
	                 result[i] = result[j];    
	                 result[j] = temp;    
	              }     
	          }     
        }    
		 
		StringBuilder tableName = new StringBuilder();
		for (int[] ints : result) {
			tableName.append(array[ints[1]][1]).append(array[ints[1]][0]);
		}
		
		return tableName.toString();
	}

	/**
	 * This method finds the numerology's number of a char.
	 * @param chr
	 * @return
	 */
	private int findNumerology(char chr) {
		chr = Character.toUpperCase(chr);

		
		 for(int i=0;i<numberOfCharacterInAlphabet;i++) {
	            if(chr==(num_val[0][i])){
	                return num_val[1][i];
	            }
        }
		 
        return 0;

	}
}
