package com.data.hibernate;

public class BidsQueries {

	//public static final String FIND_USER_BY_PHONE = "findUserByPhoneNo";

	//public static final String FIND_USER_BY_PHONE_QUERY = "from DataProvider u where u.phoneNo = :phoneNo";*/

	//public static final String FIND_USER_BY_EMAIL_ID = "findUserByEmailId";


	//public static final String FIND_USER_BY_UNAME = "findUserByUname";

	public static final String FIND_BIDS_BY_U_ID = "findBidsByUid";
	public static final String FIND_BIDS_BY_U_ID_QUERY = "from Bids u where u_id = :id";

	public static final String FIND_BIDS_BY_P_ID = "findBidsByPid";
	public static final String FIND_BIDS_BY_P_ID_QUERY = "from Bids p where p.id = :p_id";

	public static final String  FETCH_All_BIDS = "findBids";
	public static final String  FETCH_All_BIDS_QUERY = "from Bids";



	//public static final String FIND_BIDS_BY_P_ID = "from Bids b where b.p_id = :p_id";



	//public static final String FIND_USER_BY_UNAME_QUERY = "from DataProvider u where u.uname = :uname";

	//public static final String FIND_USER_BY_U_ID_QUERY = "from DataProvider u where u.id = :id";

}
