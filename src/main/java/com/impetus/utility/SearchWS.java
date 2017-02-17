package com.impetus.utility;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

// TODO: Auto-generated Javadoc
//Service Endpoint Interface
/**
 * The Interface SearchWS.
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface SearchWS {

	/**
	 * Search books.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	@WebMethod
	String searchBooks(String str);

}