/**
 * 
 */
package com.little_brainees.app.ws.utilities;

import com.little_brainees.app.ws.DTO.TopicDTO;

/**
 * @author ashish
 *
 */
public final class LBLogger {
	
	private LBLogger() {
		
	}
	
	public static void logMessage(String message) {
		System.out.println(message);
	}
	
	public static void logMessage(String message,String topicDTO) {
		System.out.println(message +" - " + topicDTO);
	}
	
	
	public static void logMessage(String functionName , String message,String topicDTO) {
		System.out.println("Function Name : "+functionName +"\nMessage : "+message + " - " + topicDTO);
	}
	public static void logError(String error) {
		System.err.println(error);
	}

}
