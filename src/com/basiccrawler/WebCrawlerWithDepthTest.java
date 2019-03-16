package com.basiccrawler;

import java.io.IOException;

public class WebCrawlerWithDepthTest {

	/**
	 * This is our test. It creates a WebCrawlerWithDepth and crawls the web.
	 * 
	 * @param args
	 *            - not used
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException

	{
		new WebCrawlerWithDepth().getPageLinks("http://www.redhat.com", 0, "redhat");
	}

}
