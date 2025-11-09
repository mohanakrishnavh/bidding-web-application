package com.services.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.data.hibernate.DataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.data.hibernate.DataInsertion;
import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/login")
public class LoginServices {

	// private static final Logger logger =
	// LogManager.getLogger(LoginService.class);

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam(ParameterConstants.EMAIL) String email,
			@FormParam(ParameterConstants.PASSWORD) String password) throws JsonProcessingException {
		{
			DataProvider user = DataInsertion.getUserByEmail(email);
			// System.out.println("i am here too");
			Map<String, Object> responseData = new HashMap<String, Object>();
			System.out.println("hii!!");
			/*
			 * if (!SecurityCheckUtil.isRequestFromValidSource(secretValue)) {
			 * String errorMsg =
			 * "Request is not from a valid source. Turning down the request!";
			 * System.out.println(errorMsg);
			 * return errorMsg;
			 * }
			 */
			System.out.println(user.getId());
			if (user != null) {
				if (password.equals(user.getPassword())) {
					responseData.put(ParameterConstants.USER_ID, user.getId());
					responseData.put(ParameterConstants.FIRST_NAME, user.getFname());
					responseData.put(ParameterConstants.LAST_NAME, user.getLname());
					responseData.put(ParameterConstants.EMAIL, user.getEmail());
					responseData.put(ParameterConstants.EMAIL, user.getUname());
					responseData.put(ParameterConstants.LAST_SUCCESSFUL_LOGIN_TIME, user.getLastlogintime());
					responseData.put(ParameterConstants.FAILED_LOGIN_COUNT, user.getFailedLoginCount());
					DataInsertion.recordSuccessfulLogin(user);
					System.out.println("USer is valid");
				} else {
					DataInsertion.recordFailedLogin(user);
					responseData.put(ParameterConstants.FAILED_LOGIN_COUNT, user.getFailedLoginCount());
				}
			} /*
				 * else if (isLoginThruFB == 1) {
				 * Integer userId = DataManager.addUser(userName, null, emailId, "fb",
				 * CustomerType.CLIENT);
				 * responseData.put(ParameterConstants.USER_ID, userId);
				 * } else {
				 * responseData.put(ParameterConstants.USER_ID, null);
				 * }
				 */
			return JsonParser.convertToJson(responseData);
		}

	}
}