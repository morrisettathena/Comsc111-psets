
package filecompression;

/**
 * Test program for FileCompression methods.
 */
public class TestFileCompression {
 
    public static void main(String[] args){
        String[] testStrings = {"11110", "00000", "101010", "11100010",
        		"11111111111111111111111111111111111000011"};
        String[] compressedStrings = {"4 0 3 1 1 0 1 0 0 1 5 1 9 0"};

        for (String testString : testStrings) {
            System.out.println("Compressing: " + testString);
            System.out.println("Recursive Output: " + FileCompression.recursiveCompressCompact(testString));
            System.out.println("Iterative Output: " + FileCompression.iterativeCompress(testString));
            System.out.println("Uncompressed Output: " +
            FileCompression.uncompress(FileCompression.iterativeCompress(testString)));
        }
        
    }
}
