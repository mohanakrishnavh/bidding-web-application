package com.data.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ItemManager {

	public static void addItem()
			 {

		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory sf = con.buildSessionFactory();
		Session ss = sf.openSession();
		Items itemprovider = new Items();
		itemprovider.setP_name("wplbook");
		itemprovider.setP_desc("Any wpl book");
		//itemprovider.setU_id(1);

		Transaction TR = ss.beginTransaction();
		ss.save(itemprovider);
		System.out.println("Object saved successfully");
		TR.commit();
		ss.close();
		sf.close();
	}



	public static void insertInfo(){
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory sf = con.buildSessionFactory();
		Session ss = sf.openSession();
		DataProvider provider = new DataProvider();
		provider.setUname("mohan1212");
		provider.setFname("mo");
		provider.setLname("na");
		provider.setEmail("a@gmail11.com");
		//provider.setId(5);
		provider.setPassword("kikcsddi");
		Transaction TR = ss.beginTransaction();
		ss.save(provider);
		System.out.println("Object saved successfully");
		TR.commit();
		ss.close();
		sf.close();
	}

	public void getData(){
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		SessionFactory sf = con.buildSessionFactory();
		Session ss = sf.openSession();
		Object ob = ss.load(DataProvider.class, new String("singhunnati25@gmail.com"));
		DataProvider provider = (DataProvider)ob;

		System.out.println("Username" + provider.getUname() + " " + provider.getFname());

		Transaction TR = ss.beginTransaction();
		ss.save(provider);
		System.out.println("Object saved successfully");
		TR.commit();
		ss.close();
		sf.close();

	}
	public static void main(String[] args) {
		new ItemManager().addItem();

	}

}
