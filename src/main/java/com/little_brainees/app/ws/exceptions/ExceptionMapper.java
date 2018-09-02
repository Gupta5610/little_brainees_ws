package com.little_brainees.app.ws.exceptions;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.utilities.LBLogger;


@Singleton
public class ExceptionMapper  {

public static Response Response(Exception ex) {
	    ErrorMessage errorMessage = null;
	    Status status = Status.INTERNAL_SERVER_ERROR;
	    
	    LBLogger.logError(ex.toString());
	    
		if (ex instanceof MissingRequiredFieldException) {
			LBLogger.logError(ex.getMessage());
			status = Status.NOT_ACCEPTABLE;
			errorMessage = new ErrorMessage(ex.getMessage(),406,"www.google.com");
		}else if(ex instanceof DuplicateRecordFoundException) {
			errorMessage = new ErrorMessage(ex.getMessage(),302,"www.google.com");
			status = status.FOUND;
		}else if(ex instanceof RecordNotFoundException) {
			errorMessage = new ErrorMessage(ex.getMessage(),404,"www.google.com");
			status = Status.NOT_FOUND;
		}
		else {
			errorMessage = new ErrorMessage(ex.getMessage(),500,"www.google.com");
		}
		
		
		return Response.status(status).entity(errorMessage).build();
		
	}
}
