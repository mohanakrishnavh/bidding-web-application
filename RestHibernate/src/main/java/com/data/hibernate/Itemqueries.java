package com.data.hibernate;

public interface Itemqueries {

	public static final String FETCH_All_ITEMS = "findallitems";

	public static final String FETCH_All_ITEMS_QUERY = "from Items";

	public static final String FETCH_All_ITEMS_ON_SEARCH = "findallitemsonsearch";

	public static final String FETCH_All_ITEMS_ON_SEARCH_QUERY = "from Items i where i.p_name = :search ";

}