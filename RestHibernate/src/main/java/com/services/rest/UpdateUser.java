package com.services.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.type.MetaType;

import com.data.hibernate.DataInsertion;
import com.data.hibernate.DataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/Update")
public class UpdateUser {

	// private static final Logger logger =
	// LogManager.getLogger(CreateUserService.class);

	@POST
	@Path("createUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@FormParam(ParameterConstants.USERNAME) String uname,
			@FormParam(ParameterConstants.PASSWORD) String password, @FormParam(ParameterConstants.EMAIL) String email,
			@FormParam(ParameterConstants.FIRST_NAME) String fname,
			@FormParam(ParameterConstants.LAST_NAME) String lname, @FormParam(ParameterConstants.USER_ID) Integer id)
			throws JsonProcessingException {

		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		System.out.println(uname + "******");

		DataProvider user = DataInsertion.updateUser(uname, fname, lname, password, email, id);
		int userId = user.getId();
		MemCacheUtil.putInCache("user" + userId, JsonParser.convertToJson(user));

		Map<String, Object> responseData = new HashMap<String, Object>();

		responseData.put(ParameterConstants.USER_ID, userId);

		Response response = respBuilder.entity(JsonParser.convertToJson(responseData)).build();
		return response;
	}

	/*
	 * @GET
	 *
	 * @Path("ping")
	 *
	 * @Produces(MediaType.TEXT_PLAIN)
	 * public String ping() throws JsonProcessingException {
	 * return new Date().toString();
	 * }
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@QueryParam(ParameterConstants.USER_ID) Integer userId) throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();

		/*
		 * if (!SecurityCheckUtil.isRequestFromValidSource(secretValue)) {
		 * String errorMsg =
		 * "Request is not from a valid source. Turning down the request!";
		 * logger.error(errorMsg);
		 * return respBuilder.entity(JsonParser.convertToJson(errorMsg)).build();
		 * }
		 */

		StringBuilder cacheKeyBuilder = new StringBuilder();
		String cacheKey = cacheKeyBuilder.append("user").append(userId).toString();

		if (userId != null) {
			String json = MemCacheUtil.getFromCache(cacheKey);

			if (json == null) {
				DataProvider user = DataInsertion.getUserByUid(userId);
				Map<String, Object> responseData = new HashMap<String, Object>();
				responseData.put(ParameterConstants.FIRST_NAME, user.getFname());
				json = JsonParser.convertToJson(user);
				MemCacheUtil.putInCache(cacheKey, json);
			}
			return respBuilder.entity(json).build();
		} else {
			String errorMsg = "User ID is null.";
			// logger.error(errorMsg);
			return respBuilder.entity(errorMsg).build();
		}
	}

}