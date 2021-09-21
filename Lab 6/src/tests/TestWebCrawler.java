package tests;

import webcrawler.WebCrawler;
import jsjf.LinkedQueue;

/** Test program for WebCrawler class*/
public class TestWebCrawler {

	/**Crawl acm.org or the 110 textbook author's site and find 10 
	 * subULRs.
	 * @param args
	 */
	public static void main(String[] args) {
			LinkedQueue<String> test = new LinkedQueue<String>();
			test.enqueue("hi");
			test.enqueue("guy");
			test.enqueue("sly");
			System.out.println(test);
            String URLString = "https://www.acm.org";
            //String URLString = "https://www.rwu.edu";

            WebCrawler w = new WebCrawler(URLString);
            w.setMaxURLs(10);
            w.crawlAndPrint();
                
	}

}
