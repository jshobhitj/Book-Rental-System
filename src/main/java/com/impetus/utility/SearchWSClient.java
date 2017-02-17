package com.impetus.utility;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * The Class SearchWSClient.
 */
public class SearchWSClient {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:9999/ws/Library?wsdl");

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://utility.impetus.com/","SearchWSImplService");

		Service service = Service.create(url, qname);

		SearchWS search = service.getPort(SearchWS.class);
		System.out.println(search.searchBooks("Da Vinci"));

	}

}