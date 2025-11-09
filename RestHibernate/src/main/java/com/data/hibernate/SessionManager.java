package com.data.hibernate;



import java.io.Serializable;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionManager {

	//private static final Logger logger = LogManager.getLogger(SessionManager.class);

	private static SessionFactory factory;

	static {

			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	}

	public static Session getSession() {
		return factory.openSession();
	}

	public static void closeSession(Session session) {
		session.close();
	}

	public static void saveObject(Session session, Object obj) {
		session.save(obj);
	}

	public static Serializable saveObject(Object obj) {
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			Serializable id = session.save(obj);
			transaction.commit();
			return id;
		} finally {
			closeSession(session);
		}
	}

	public static void updateObject(Object obj) {
		Session session = getSession();
		try {
			Transaction transaction = session.beginTransaction();
			transaction.begin();
			session.saveOrUpdate(obj);
			transaction.commit();
		} finally {
			closeSession(session);
		}
	}

}

