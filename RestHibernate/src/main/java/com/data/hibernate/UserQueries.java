package com.data.hibernate;

public interface UserQueries {

	/*public static final String FIND_USER_BY_PHONE = "findUserByPhoneNo";

	public static final String FIND_USER_BY_PHONE_QUERY = "from DataProvider u where u.phoneNo = :phoneNo";*/

	public static final String FIND_USER_BY_EMAIL_ID = "findUserByEmailId";

	public static final String FIND_USER_BY_EMAIL_ID_QUERY = "from DataProvider u where u.email = :email";

}
