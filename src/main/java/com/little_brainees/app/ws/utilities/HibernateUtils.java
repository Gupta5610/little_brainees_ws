package com.little_brainees.app.ws.utilities;

import org.hibernate.cfg.*;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	
	static {
		
		Configuration config = new Configuration();
		config.configure();
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Exception ex) {
			System.err.println("Initial SessionFactory creation Failed" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
