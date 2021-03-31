package Search;

import DataSorter.Filters.*;

/**
 * 
 * This class gets the table name of query after given filters.
 * 
 * @author Boaz Tene
 *
 */
public class Classify {
	private Filter[] filters;
	private String query;
	
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
		String tableName = "";
		
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
			
			for (int j = 0; j < temp.length; j++) {
				result[i][0] += findNumerology(temp[j]);
			}
		}
		
	    int[] temp = new int[2];    
		
		 for (int i = 0; i < result.length; i++) {     
	          for (int j = i+1; j < result.length; j++) {     
	              if (result[i][1] > result[j][1]) {      //swap elements if not in order
	                 temp = result[i];    
	                 result[i] = result[j];    
	                 result[j] = temp;    
	              }     
	          }     
        }    
		 
		String tableName = "";
		for (int i = 0; i < result.length; i++) {
			tableName += array[result[i][1]][1] + array[result[i][1]][0];
		}
		
		return tableName;
	}
	
	private int findNumerology(char chr) {
		 char[][] num_val={{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'},
                 {1, 2, 3, 4, 5, 8, 3, 5, 1, 1, 2, 3, 4, 5, 7, 8, 1, 2, 3, 4, 6, 6, 6, 5, 1, 7}};
		
		 for(int i=0;i<26;i++) {
	            if(chr==(num_val[0][i])){
	                return num_val[1][i];
	            }
        }
		 
        return 0;

	}
}
