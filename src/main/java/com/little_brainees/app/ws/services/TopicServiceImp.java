/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DAO.BaseMySQLDAO;
import com.little_brainees.app.ws.DAO.DAO;
import com.little_brainees.app.ws.DTO.TopicDTO;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ValidationUtility;

/**
 * @author ashish
 *
 */
public class TopicServiceImp implements TopicService {
	
	public static TopicService shared = new TopicServiceImp();
	
	public TopicServiceImp(){
		
	}
	
	private DAO databse = new BaseMySQLDAO();
	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.TopicService#createTopic(com.little_brainees.app.ws.DTO.TopicDTO)
	 */
	@Override
	public TopicDTO createTopic(TopicDTO topicDTO) {
        LBLogger.logMessage("CreateTopic in TopicService", topicDTO.toString());
	    ValidationUtility.validateRequiredFieldsInDTO(topicDTO);
	    LBLogger.logMessage("ErrorNotThrown");
	    return this.saveTopic(topicDTO);
	}

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.TopicService#getTopics()
	 */
	private TopicDTO saveTopic(TopicDTO topicDTO) {
		TopicDTO savedDTO = null;
		try {
			this.databse.openConnection();
			savedDTO = this.databse.saveTopic(topicDTO);
		}catch(Exception ex) {
			LBLogger.logMessage(ex.toString());
			throw new RuntimeException(ex);
		}
		finally {
			this.databse.closeConnection();
		}
		return savedDTO;
	}
	public List<TopicDTO> getTopics() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.TopicService#getTopictBy(java.lang.String)
	 */
	@Override
	public TopicDTO getTopictBy(String topicCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
