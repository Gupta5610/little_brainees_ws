package com.little_brainees.app.ws.utilities;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseBuilderUtil {
	
	public static Response createResponse(Status status , Object responseObject ) {
		return Response.status(status).entity(responseObject).build();
	}

}
