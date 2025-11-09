package com.hibernate.queries;

public interface ItemsQueries {

	public static final String FIND_ITEMS_BY_U_ID = "finditemsByUid";
	public static final String FIND_ITEMS_BY_U_ID_QUERY = "from Items i where i.u_id = :id";

	public static final String FIND_ITEMS_BY_P_ID = "finditemsByPid";
	public static final String FIND_BIDS_BY_P_ID_QUERY = "from Items i where p.p_id = :p_id";

}
