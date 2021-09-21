package webcrawler;

import java.net.URL;
import java.util.Scanner;
import jsjf.ArrayUnorderedList;

/**
 * A very simple URL parser that find subURLs of a given URL
 *
 * @author Sonya Cates
 */
public class URLParser {

    private final static String URL_START = "http:";
    private final static String URL_END1 = "'";
    private final static String URL_END2 = "\"";

    private String inputURL;
    URL url;

    /**
     * Default constructor
     */
    public URLParser() {

    }

    /**
     * Set the inputURL
     */
    public URLParser(String inputURL) {
        setURL(inputURL);
    }

    /**
     * Set the inputURL
     */
    public void setURL(String inputURL) {
        this.inputURL = inputURL;
        try {
            url = new URL(inputURL);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * getSubURLS returns and ArrayUnorderedList of Strings containing all the
     * subURLS of the inputURL. Only subURLs in the form http:\"..." or
     * http:\'...' are included.
     */
    public ArrayUnorderedList<String> getSubURLS() {
        int max = 100;
        int count = 0;
        //Create the list to hold the subURLS
        ArrayUnorderedList<String> urlList = new ArrayUnorderedList<>();
        try { //try to open the given URL
            Scanner input = new Scanner(url.openStream());

            int startIndex = 0;
            while (input.hasNext()) {
                String nextLine = input.nextLine();
                //find the next instance of http:
                startIndex = (nextLine.toLowerCase()).indexOf(URL_START, startIndex);
                while (startIndex > 0) { //if http was found
                    //find the next ' or "
                    int temp1 = nextLine.indexOf(URL_END1, startIndex);
                    int temp2 = nextLine.indexOf(URL_END2, startIndex);
                    int endIndex = -1;
                    if (temp1 == -1 && temp2 == -1) {
                        endIndex = startIndex;
                    } else if (temp2 == -1) {
                        endIndex = temp1;
                    } else {
                        endIndex = temp2;
                    }
                    //int endIndex = Math.min(nextLine.indexOf(URL_END1, startIndex), nextLine.indexOf(URL_END2, startIndex));

                    if (endIndex > 0) { //if the end of the url was found
                        //get the correct substring
                        String subURL = nextLine.substring(startIndex, endIndex);
                        //add to list
                        if (count > max) {
                            return urlList;
                        }
                        count++;
                        urlList.addToRear(subURL);
                        //find next instance of http:
                        startIndex = (nextLine.toLowerCase()).indexOf(URL_START, endIndex);
                    } else {
                        startIndex = -1;
                    }
                }
            }
            input.close();
        } catch (Exception e) {//URL failed to open
            return urlList;
            //System.out.println("Error: " + e.getMessage());
        }

        return urlList;
    }


    /**
     * Print subURLs instead of returning them in an ArrayList. This method may
     * be useful for debugging, but is not needed otherwise.
     */
    public void printSubURLS() {
        try {
            URL url = new URL(inputURL);
            Scanner input = new Scanner(url.openStream());

            int startIndex = 0;
            while (input.hasNext()) {
                String nextLine = input.nextLine();
                startIndex = (nextLine.toLowerCase()).indexOf(URL_START, startIndex);
                while (startIndex > 0) {
                    int endIndex = Math.min(nextLine.indexOf(URL_END1, startIndex), nextLine.indexOf(URL_END2, startIndex));
                    if (endIndex > 0) {
                        String subURL = nextLine.substring(startIndex, endIndex);
                        System.out.println(subURL);
                        startIndex = (nextLine.toLowerCase()).indexOf(URL_START, endIndex);
                    } else {
                        startIndex = -1;
                    }
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
