package com.basiccrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawlerWithDepth {

	// Crawling depth
	private static final int MAX_PAGE_TO_SEARCH = 5;
	// Set for Unique entries 
	private HashSet<String> links;
	
	//Constructor
	public WebCrawlerWithDepth() {
		links = new HashSet<>();
	}

	/**
	 * Get Page Links and Crawl site recursively and Print result on console 
	 * Use of Jsoup Java Library
	 * 
	 * @param URL
	 * @param page
	 * @param desc
	 * 
	 * @author Prashank Mohan
	 */

	public void getPageLinks(String URL, int page, String desc) {
		if ((!links.contains(URL) && (page < MAX_PAGE_TO_SEARCH))) {
			System.out.println(">> Page: " + page + " -> " + desc + "\n [" + URL + "] \n");
			
			/*
			 * to match pattern of page title
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Matcher matcher = pattern.matcher(desc);
			if (!matcher.matches()) {
		           //System.out.println("string '"+desc + "' contains special character");
		      } else {
		    	  System.out.println(">> Page: " + page + " -> " + desc + "\n [" + URL + "] \n ");
		      }
			*/

			try {
				// add links to set
				links.add(URL);

				//connect to URL using Jsoup
				Document document = Jsoup.connect(URL).get();
				Elements otherLinks = document.select("a[href*=redhat.com]");
				//Elements otherLinks = document.select("[title]");

				page++;

				for (Element link : otherLinks) {
					if (links.add(URL)) {
						System.out.println(URL);
					}
					getPageLinks(link.attr("abs:href"), page, link.text());
				}
			} catch (IOException e) {
				//System.err.println("Error For URL -> '" + URL + "': " + e.getMessage());
			}

		}

	}
}
