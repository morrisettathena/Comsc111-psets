package filecompression;

/****************************************** 
* @Author John Morrisett
* @since 3/24/2021
* Program: FileCompression.java
* COMSC111L.51
* Professor Patterson
* Purpose:  Contains a couple methods for compressing and decompressing files.
* 			Two of the compression methods are recursive, and one is iterative.  
*           Additionally, there is a decompression method.
*******************************************/

public class FileCompression {

	public static String recursiveCompress(String data) {
		//compresses a string of 1's and 0's
		
		//base case
		if (data.length() == 0)
			return "";
		
		int index = 0;
		int count = 0;
		char value = data.charAt(0);
		
		//while items are similar, count them
		while(index < data.length() && data.charAt(index) == value) {
			count++;
			index++;
		}
		
		//when done, return the string representation of the list
		return count + " " + value + " " + recursiveCompress(data.substring(index));
	} 


	public static String recursiveCompressCompact(String data) {
		//compresses a string of 1's and 0's in a more compact form
		
		//base case
		if (data.length() == 0)
			return "";
		
		int index = 0;
		int count = 0;
		char value = data.charAt(0);
		
		//while items are similar, count them
		while(index < data.length() && data.charAt(index) == value) {
			count++;
			index++;
		}
		
		//if this is just one 1 or one 0, return 1 or 0
		if (count <= 1)
			return value + " " + recursiveCompressCompact(data.substring(index));
		
		//otherwise, when done return string representation of this value
		return count + " " + value + " " + recursiveCompressCompact(data.substring(index));
	} 
	
	
	public static String uncompress(String data) {
		//takes a string and decompresses it
		
		//get rid of any leading or trailing whitespace
		data = data.trim();

		//base case
		if (data.isEmpty())
			return "";
		
		//get the count, value, and rest of the data separated by whitespace
		String[] info = data.split(" ", 3);
		int count = Integer.parseInt(info[0]);

		String resultString = "";
		
		while (count > 0) {
			//iterate over string until it has reached count limit
			resultString += info[1];
			count--;
		}
		
		if (info.length > 2)
			//if there is more data, return the result and uncompress the rest
			return resultString + uncompress(info[2]);
		//other base case.  If no more data, return the resulting string.
		return resultString;
	}
	
   
    public static String iterativeCompress(String input) {
        //initialize the compressed result
        String result = "";
    
        //keep a count of the number of consecutive characters in the current 'block'
        int count = 1;
        
        //walk down the input string
        for(int i = 0; i < input.length(); i++) {
            //if we are in the middle of a 'block' (the next character is the same and we aren't at the end)
            //then increment the counter
            if((i + 1) < input.length() && input.charAt(i) == input.charAt(i + 1))
                count++;
            //if we have reached the end of a 'block' 
            //then add the count and current character to the compressed result
            //and reset the counter for the next 'block'
            else {
                result += " " + count + " " + input.charAt(i);
                count = 1; 
            }
        }
        return result;
    }
    
}
