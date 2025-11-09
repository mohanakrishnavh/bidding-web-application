package com.services.rest;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.data.hibernate.DataInsertion;
import com.data.hibernate.Orders;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.wpl.commons.ParameterConstants;
import com.wpl.json.JsonParser;

@Path("/order")
public class OrderService {

	// private static final Logger logger =
	// LogManager.getLogger(OrderService.class);
	// private static final String UTDEATS_SERVICES_EMAIL =
	// "https://localhost:9443/utdeats/services/email";

	@POST
	@Path("createOrder")
	@Produces(MediaType.APPLICATION_JSON)
	public Response placeOrder(@FormParam(ParameterConstants.USER_ID) Integer userId,
			@FormParam(ParameterConstants.ORDER_ITEMS) int quantity, @FormParam(ParameterConstants.TOTAL) int total,
			@FormParam(ParameterConstants.SELLERID) int sid)
			throws JsonParseException, JsonMappingException, IOException {

		ResponseBuilder respBuilder = new ResponseBuilderImpl();

		System.out.println(userId + total);
		// Integer orderId = DataInsertion.insertOrderInfo(userId, total, sid,
		// quantity);
		Integer orderId = DataInsertion.insertOrderInfo(3, 40, 2, 2);
		System.out.println(orderId);
		// logger.info("Successfully placed order for userId:{} and the orderId is {}",
		// userId, orderId);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put(ParameterConstants.ORDER_ID, orderId);
		// sendOrderConfirmationEmail(orderId, userId);
		// logger.info("Successfully sent the order confirmation email to the
		// userId:{}", userId);
		MemCacheUtil.removeFromCache(userId + "order");
		return respBuilder.entity(JsonParser.convertToJson(responseData)).build();

	}

}