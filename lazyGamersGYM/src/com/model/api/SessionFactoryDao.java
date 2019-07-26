package com.model.api;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFactoryDao {
	static {
		try {
			System.out.println("Session BUILD");
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			System.out.println("Session ERROR");
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	private static final SessionFactory sessionFactory;

	public static SessionFactory getSession() {
		return sessionFactory;
	}
}
