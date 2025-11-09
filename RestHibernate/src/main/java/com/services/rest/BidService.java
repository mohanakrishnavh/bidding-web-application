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

import com.data.hibernate.Bids;
import com.data.hibernate.DataInsertion;
import com.data.hibernate.Items;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/bidRequest")
public class BidService {

	@POST
	@Path("/Bid")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBid(@FormParam(ParameterConstants.USER_ID) Integer userId,
			@FormParam(ParameterConstants.PID) Integer pid,
			@FormParam(ParameterConstants.BPRICE) Integer bprice) throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		// System.out.println(userId+"userId");
		int bid = DataInsertion.insertBIdInfo(userId, bprice, pid);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put(ParameterConstants.BID, bid);
		Response response = respBuilder.entity(JsonParser.convertToJson(responseData)).build();
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBids(@QueryParam(ParameterConstants.USER_ID) Integer userId) throws JsonProcessingException {
		ResponseBuilder respBuilder = new ResponseBuilderImpl();
		System.out.println(userId + "userId");

		List<Bids> bids = DataInsertion.fetchBids(userId);

		// json = JsonParser.convertToJson(details);
		// Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData.put(ParameterConstants.PID,pID );
		Response response = respBuilder.entity(JsonParser.convertToJson(bids)).build();
		// System.out.println(response);

		return response;
	}

}