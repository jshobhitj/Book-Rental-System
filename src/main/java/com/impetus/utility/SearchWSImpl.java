package com.impetus.utility;

import java.io.File;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.impetus.dao.SearchBookDAO;
import com.impetus.model.BookCatalogue;


//Service Implementation
/**
 * The Class SearchWSImpl.
 */
@SuppressWarnings("deprecation")
@WebService(endpointInterface = "com.impetus.utility.SearchWS")
public class SearchWSImpl implements SearchWS {
	private static Logger logger = Logger.getLogger(SearchWSImpl.class);
	/** The session factory. */
	private SessionFactory sessionFactory;

	/** The session. */
	private Session session;

	/** The searchBookDAO. */
	private  SearchBookDAO searchBookDAO;

	/**
	 * Gets the session factory.
	 * 
	 * @return the session factory
	 * @throws Exception
	 *             the exception
	 */
	private SessionFactory getSessionFactory() {
		return createConfiguration().buildSessionFactory();
	}

	/**
	 * Creates the configuration.
	 * 
	 * @return the configuration
	 * @throws Exception
	 *             the exception
	 */
	private Configuration createConfiguration() {
		return loadConfiguration();
	}

	/**
	 * Load configuration.
	 * 
	 * @return the configuration
	 */
	private Configuration loadConfiguration() {
		return new AnnotationConfiguration()
				.addAnnotatedClass(BookCatalogue.class)
				.configure(new File("src/main/resources/hibernate.cfg.xml"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.impetus.utility.SearchWS#searchBooks(java.lang.String)
	 */
	@Override
	public String searchBooks(String str) {
		try {
			sessionFactory = getSessionFactory();
		} catch (Exception e) {

			logger.info(e.getMessage());
		}
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		searchBookDAO = new SearchBookDAO();
		searchBookDAO.setSessionFactory(sessionFactory);
		List<BookCatalogue> result = searchBookDAO.findAllDAO(str);
		Gson gson = new Gson();
		return gson.toJson(result);

	}

}