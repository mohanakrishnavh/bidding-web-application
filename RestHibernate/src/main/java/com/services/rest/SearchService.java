package com.services.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.data.hibernate.DataInsertion;
import com.data.hibernate.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/Search")
public class SearchService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem(@QueryParam(ParameterConstants.SEARCH) String search) throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		// System.out.println(userId+"userId");
		List<Items> items = DataInsertion.fetchSearch(search);

		// json = JsonParser.convertToJson(details);
		// Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData.put(ParameterConstants.PID,pID );
		Response response = respBuilder.entity(JsonParser.convertToJson(items)).build();
		System.out.println(response);

		return response;
	}

}