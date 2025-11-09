package com.data.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.wpl.commons.ParameterConstants;

public class BidsInsertion {

	public static void findBidsByUid(Integer u_id
			) {
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml").addAnnotatedClass(Bids.class);
		SessionFactory sf = con.buildSessionFactory();
		Session ss = sf.openSession();
		try {
			System.out.println("getAll Bids");
			//List<DataProvider> users = ss.getNamedQuery(UserQueries.FIND_USER_BY_UNAME).setString(ParameterConstants.USERNAME, uname).list();
			List<Bids> bids = ss.getNamedQuery(BidsQueries.FIND_BIDS_BY_U_ID).setInteger(ParameterConstants.USER_ID,u_id).list();
			System.out.println(bids.get(0).getBid_id());
			if (bids.size() != 0) {
					System.out.println(bids.get(0).getBid_price());

					bids.get(0).getP_id();
					System.out.println(bids.get(0).getId());

//					System.out.println(bids.get(0).getBid_id());
//					System.out.println(bids.get(0).getBid_id());
//					System.out.println(bids.get(0).getBid_id());
//					System.out.println(bids.get(0).getBid_id());
					/*Transaction TR = ss.beginTransaction();
					ss.saveOrUpdate(bids.get(0));
					System.out.println("Object saved successfully");*/
				/*	TR.commit();*/

					System.out.println(" got the data");
					//return bids.get(0);
			}

		//	logger.error("Unable to find a valid user with the emailId {}. Returning null!", emailId);

		} finally {
			ss.close();
			sf.close();

		}
	}
	public static void main(String[] args){
		BidsInsertion di = new BidsInsertion();
		di.findBidsByUid(1);
	}

}
