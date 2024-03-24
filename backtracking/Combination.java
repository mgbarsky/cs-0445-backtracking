package backtracking;
/**
 *  Simple demo program for exhaustive generation of combinations 
 *  @author M. Barsky
 *  */

class Combination {	
	static String combinationToLine(int [] data) {
		StringBuilder sb = new StringBuilder();
        for (int j=0; j<data.length; j++)
        	sb.append(data[j]+" ");     
        return sb.toString()+System.lineSeparator();
	}
    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Starting and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static String combination(int arr[], int data[], int start,
                                int end, int index, int r)  {
        // Current combination is ready -- return it
        if (index == r){     
            return combinationToLine(data);
        }
 
        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        String result ="";
        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            data[index] = arr[i];
            String s = combination(arr, data, i+1, end, index+1, r);
            result += s;
        }
        return result;
    }  
 
    /*Driver function to check for above function*/
    public static void main (String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int r = 3;
        int n = arr.length;
        // A temporary array to store all combination one by one
        int data[]=new int[r];
        System.out.println(combination(arr, data, 0, n-1, 0, r));
    }
}