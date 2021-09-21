package webcrawler;

/****************************************** 
* @Author John Morrisett
* @since 3/12/2021
* Program: WebCrawler.java
* COMSC111L.51
* Professor Patterson
* Purpose:  Crawls through the sublinks given from a base link
*******************************************/

import jsjf.ArrayUnorderedList;
import jsjf.LinkedQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class WebCrawler {
	private int maxURLCount = 100;
	private String startURL;
	
	public WebCrawler(String startURL) {
		setStartURL(startURL);
	}
	
	
	public WebCrawler() {
		setStartURL("http://www.google.com");
	}
	
	
	public int getMaxURLs() {
		return maxURLCount;
	}
	
	
	public void setMaxURLs(int value) {
		//sets the max number of sublinks that the url can crawl through
		maxURLCount = value;
	}
	
	
	public String getStartURL() {
		return startURL;
	}
	
	
	public void setStartURL(String value) {
		//sets the starting url to be crawled through
		startURL = value;
	}
	
	
	public void crawlAndPrint() {
		//takes the base url, and crawls through the sublinks
		ArrayUnorderedList<String> visitedList = new ArrayUnorderedList<String>();
		LinkedQueue<String> pendingQueue = new LinkedQueue<String>();
		int visitCount = 0;
		
		pendingQueue.enqueue(startURL);
		
		//while the queue has elements to go through excecute the following
		while (!pendingQueue.isEmpty() && visitCount < maxURLCount) {
			String nextURL = pendingQueue.dequeue();
			
			//only excecute this if the url hasn't been visited
			if (!visitedList.contains(nextURL)){
				System.out.println(nextURL);
				URLParser parser = new URLParser(nextURL);
				
				//get the sublinks from this sublink
				ArrayUnorderedList<String> subURLs = parser.getSubURLS();
				
				for (String url: subURLs) {
					//if the sublinks haven't been visited, add them to the queue
					if (!visitedList.contains(url)) {
						pendingQueue.enqueue(url);
					}
				}
			}
			visitCount++;
		}
	}
}
