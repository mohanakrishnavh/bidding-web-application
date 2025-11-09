package com.services.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.data.hibernate.DataInsertion;
import com.data.hibernate.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/postBid")
public class PostItemService {

	@POST
	@Path("/item")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem(@FormParam(ParameterConstants.USER_ID) Integer userId,
			@FormParam(ParameterConstants.PNAME) String pname,
			@FormParam(ParameterConstants.PDESC) String pdesc) throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		System.out.println(userId + "userId");
		int pID = DataInsertion.addItem(userId, pname, pdesc);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put(ParameterConstants.PID, pID);
		Response response = respBuilder.entity(JsonParser.convertToJson(responseData)).build();
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem() throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		// System.out.println(userId+"userId");
		List<Items> items = DataInsertion.fetchItems();

		// json = JsonParser.convertToJson(details);
		// Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData.put(ParameterConstants.PID,pID );
		Response response = respBuilder.entity(JsonParser.convertToJson(items)).build();
		System.out.println(response);

		return response;
	}

}