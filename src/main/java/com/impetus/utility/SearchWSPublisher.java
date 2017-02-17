package com.impetus.utility;

import javax.xml.ws.Endpoint;

// TODO: Auto-generated Javadoc
//Endpoint publisher
/**
 * The Class SearchWSPublisher.
 */
public class SearchWSPublisher {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/Library", new SearchWSImpl());
	}

}