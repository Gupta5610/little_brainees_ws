package com.little_brainees.app.ws.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.exceptions.DuplicateRecordFoundException;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.RecordNotFoundException;
import com.little_brainees.app.ws.io.entity.ChildEntity;
import com.little_brainees.app.ws.io.entity.ClassEntity;
import com.little_brainees.app.ws.io.entity.ModuleEntity;
import com.little_brainees.app.ws.io.entity.ParentEntity;
import com.little_brainees.app.ws.io.entity.SubjectEntity;
import com.little_brainees.app.ws.io.entity.TeacherEntity;
import com.little_brainees.app.ws.io.entity.TopicEntity;
import com.little_brainees.app.ws.response.ChildResponseDTO;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.HibernateUtils;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;

public class MySQLDAO implements IMySQLDAO {
	
	Session session ;

	@Override
	public void openConnection() {
		SessionFactory sessionFactory =  HibernateUtils.getSessionFactory();
		this.session = sessionFactory.openSession();
		LBLogger.logMessage("Session Open : "+this.session.isOpen());;
	}
	
	@Override
	public void closeConnection() {
		if (this.session != null) {
			this.session.close();
		}

	}
	
	
	// ============================================================================================
	@Override
    public BaseDTO getEntity(RequestDTO requestDTO) {
    	
    	BaseDTO resultDTO = null;
    	Class type = requestDTO.getType();
    	String requestParameter = requestDTO.getRequestString();
    	LBLogger.logMessage("getEnity()", "requestDTO value : ", type + " , " + requestParameter);
    	
    	if (type == TeacherDTO.class)
    	   resultDTO = this.getTeacherByID(requestParameter);
    	else if(type == ClassDTO.class)
    		resultDTO = this.getClassByClassCode(requestParameter);
    	else if(type == SubjectDTO.class)
    		resultDTO = this.getSubjectBySubjectCode(requestParameter);
    	else if(type == ModuleDTO.class)
    		resultDTO = this.getModuleByModuleCode(requestParameter);
    	else if(type == TopicDTO.class)
    		resultDTO = this.getTopicByTopicCode(requestParameter);
    	else if(type == ParentDTO.class)
    		resultDTO = this.getParentById(requestParameter);
    	else if(type == ChildDTO.class)
    		resultDTO = this.getChildById(requestParameter);
    	
    	return resultDTO;
    }
    
	@Override
    public BaseDTO saveEntity(BaseDTO requestDTO) {
    	BaseDTO resultDTO = null;
       
    	LBLogger.logMessage("SaveEntity() called");
    	if(requestDTO instanceof TeacherDTO) {
    		resultDTO = this.saveTeacher((TeacherDTO) requestDTO);
    	}else if (requestDTO instanceof ClassDTO) {
    		resultDTO = this.saveClass((ClassDTO)requestDTO);
    	}else if (requestDTO instanceof SubjectDTO) {
    		resultDTO = this.saveSubject((SubjectDTO)requestDTO);
    	}else if (requestDTO instanceof ModuleDTO) {
    		resultDTO = this.saveModule((ModuleDTO)requestDTO);
    	}else if (requestDTO instanceof TopicDTO) {
    		resultDTO = this.saveTopic((TopicDTO)requestDTO);
    	}else if (requestDTO instanceof ChildDTO) {
    		resultDTO = this.saveChild((ChildDTO)requestDTO);
    	}else if (requestDTO instanceof ParentDTO) {
    		resultDTO = this.saveParent((ParentDTO)requestDTO);
    	}
    	return resultDTO;
    }
		
	
	@Override
	public List<BaseDTO> getAllEntity(RequestDTO requestDTO){
		List<BaseDTO> resultList = null;
		
		Class type = requestDTO.getType();
    	String requestParameter = requestDTO.getRequestString();
		
		if(type == ClassDTO.class) {
		   resultList = this.getAllClass();
		}else if(type == SubjectDTO.class) {
			resultList = this.getAllSubject(requestParameter);
		}else if(type == ModuleDTO.class) {
			resultList = this.getAllModule(requestParameter);
		}else if(type == TopicDTO.class) {
			resultList = this.getAllTopic(requestParameter);
		}
		
		return resultList;
	}
	
	
	@Override
	public BaseDTO updateEntity(BaseDTO requestDTO) {
		BaseDTO resultDTO = null;
		
		if(requestDTO instanceof TeacherDTO) {
			resultDTO = this.updateTeacher((TeacherDTO) requestDTO);
		}else if (requestDTO instanceof ClassDTO) {
			resultDTO = this.updateClass((ClassDTO)requestDTO);
		}else if (requestDTO instanceof SubjectDTO) {
			resultDTO = this.updateSubject((SubjectDTO)requestDTO);
		}else if (requestDTO instanceof ModuleDTO) {
			resultDTO = this.updateModule((ModuleDTO)requestDTO);
		}else if (requestDTO instanceof TopicDTO) {
			resultDTO = this.updateTopic((TopicDTO)requestDTO);
		}else if (requestDTO instanceof ParentDTO) {
			resultDTO = this.updateParent((ParentDTO)requestDTO);
		}else if (requestDTO instanceof ChildDTO) {
			resultDTO = this.updateChild((ChildDTO)requestDTO);
		}
		
		return resultDTO;
	}
	
	
	// =============================================================================================
		
	
	
    private TeacherDTO getTeacherByID(String teacherID) {
		
		TeacherDTO teacherDTO = null;
		TeacherEntity  teacherEntity = this.getTeacher(teacherID); 
		if( teacherEntity != null ) {		
			try {
			teacherDTO = (TeacherDTO)ModelMapperUtil.map(teacherEntity, TeacherDTO.class);
			}catch(Exception ex){
				System.out.println("Error Occured");
			}
		}
		System.out.println("No record Found : " + (teacherDTO == null));
		return teacherDTO; 
	}
	
	
	private TeacherEntity getTeacher(String teacherID){
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TeacherEntity> criteria = cb.createQuery(TeacherEntity.class);
		Root<TeacherEntity> profileRoot = criteria.from(TeacherEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("teacherID"), teacherID));
		Query<TeacherEntity> query = session.createQuery(criteria);
		List<TeacherEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
	}



	
	private TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
			
		if (this.getTeacherByID(teacherDTO.getTeacherEmail()) != null) 
			throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
		
		
		LBLogger.logMessage("is TeacherActive : " + teacherDTO.getIsActive());
		
		TeacherEntity teacherEntity = (new ObjectMapper()).convertValue(teacherDTO, TeacherEntity.class);
		
		try {
		session.beginTransaction();
		session.save(teacherEntity);
		session.getTransaction().commit();
		}catch(Exception ex) {
			throw new RuntimeException(ex.getLocalizedMessage());
		}
		TeacherDTO resultDTO = (new ObjectMapper()).convertValue(teacherEntity, TeacherDTO.class);
		return  resultDTO;
	}
	
	private TeacherDTO updateTeacher(TeacherDTO teacherDTO) {
		
		TeacherEntity teacherEntity = getTeacher(teacherDTO.getTeacherID());
		
		if(teacherDTO.getIsActive() != null ) {
			teacherEntity.setIsActive(teacherDTO.getIsActive());
		}
		
		if(teacherDTO.getTeacherAddress() != null && !teacherDTO.getTeacherAddress().isEmpty()) {
			teacherEntity.setTeacherAddress(teacherDTO.getTeacherAddress());
		}
		
		if(teacherDTO.getTeacherAdharNumber() != null && !teacherDTO.getTeacherAdharNumber().isEmpty()) {
			teacherEntity.setTeacherAdharNumber(teacherDTO.getTeacherAdharNumber());
		}
		
		if(teacherDTO.getTeacherBio() != null && !teacherDTO.getTeacherBio().isEmpty()) {
			teacherEntity.setTeacherBio(teacherDTO.getTeacherBio());
		}
		
		if(teacherDTO.getTeacherClass() !=null && !teacherDTO.getTeacherClass().isEmpty()) {
			teacherEntity.setTeacherClass(teacherDTO.getTeacherClass());
		}
		
		if(teacherDTO.getTeacherEmail() !=null && !teacherDTO.getTeacherEmail().isEmpty()) {
			teacherEntity.setTeacherEmail(teacherDTO.getTeacherEmail());
		}
		
		if(teacherDTO.getTeacherName() != null && !teacherDTO.getTeacherName().isEmpty()) {
			teacherEntity.setTeacherName(teacherDTO.getTeacherName());
		}
		
		if(teacherDTO.getTeacherPhoneNumber() != null && !teacherDTO.getTeacherPhoneNumber().isEmpty()) {
			teacherEntity.setTeacherPhoneNumber(teacherDTO.getTeacherPhoneNumber());
		}
		
		try {
			session.beginTransaction();
			session.update(teacherEntity);
			session.getTransaction().commit();
		}
		catch(Exception ex) {
			throw new RuntimeException(ex.getLocalizedMessage());
		}
		TeacherDTO resultDTO = (new ObjectMapper()).convertValue(teacherEntity, TeacherDTO.class);
		return  resultDTO;
	}

	
	// =============================================================================================
	
	
	private ClassDTO getClassByClassCode(String classCode) {
	
		ClassDTO classDTO = null;
		ClassEntity classEntity = this.getClassBy(classCode);
		if(classEntity != null ) {
			try {
			classDTO = new ClassDTO(classEntity.getClassCode(),classEntity.getClassName());
			
			}catch(Exception ex) {
				LBLogger.logError(ex.getLocalizedMessage());
			}
		}
		return classDTO;
	}
	
	private ClassEntity getClassBy(String classCode){
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ClassEntity> criteria = cb.createQuery(ClassEntity.class);
		Root<ClassEntity> profileRoot = criteria.from(ClassEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("classCode"),classCode));
		Query<ClassEntity> query = session.createQuery(criteria);
		List<ClassEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
		
	}
	

	
	private ClassDTO saveClass(ClassDTO classDTO){
		
		if (this.getClassByClassCode(classDTO.getClassCode()) != null)
		     throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());	
		     
			ClassEntity classEntity = (ClassEntity)ModelMapperUtil.map(classDTO, ClassEntity.class);
			try {
			session.beginTransaction();
			session.save(classEntity);
			session.getTransaction().commit();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
			ClassDTO createdDTO = (ClassDTO)ModelMapperUtil.map(classEntity, ClassDTO.class);
			return createdDTO;
		}
	
	
	private List<BaseDTO> getAllClass(){
		
		
//		List<ClassEntity> entityList = null;
//		try {
//		 entityList = session.createCriteria(ClassEntity.class).list();
//		 resultList = new ArrayList<BaseDTO>();
//		}catch(Exception ex) {
//			throw new RuntimeException(ex);
//		}
		
		List<BaseDTO> resultList = new ArrayList<BaseDTO>();
	
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ClassEntity> criteria = cb.createQuery(ClassEntity.class);
		Root<ClassEntity> profileRoot = criteria.from(ClassEntity.class);
		criteria.select(profileRoot);
		Query<ClassEntity> query = session.createQuery(criteria);
		List<ClassEntity> entityList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + entityList.size());
		ClassDTO dto;
		for(ClassEntity entity : entityList) {
			LBLogger.logMessage(entity.toString());
			dto = (ClassDTO)ModelMapperUtil.map(entity, ClassDTO.class);
			resultList.add(dto);
		}
		return resultList;
	}
	
	private ClassDTO updateClass(ClassDTO classDTO) {
		
		ClassEntity classEntity = this.getClassBy(classDTO.getClassCode());
		
		if(classDTO.getClassName() != null && !classDTO.getClassName().isEmpty()){
			classEntity.setClassName(classDTO.getClassName());
		}
		
		try {
			this.session.beginTransaction();
			this.session.update(classEntity);
			this.session.getTransaction().commit();
		}
		catch(Exception ex) {
			throw new RuntimeException(ex.getLocalizedMessage());
		}
		
		ClassDTO resultDTO = (new ObjectMapper()).convertValue(classEntity, ClassDTO.class);
		return  resultDTO;
	
	}
	
	// =============================================================================================

	
	private SubjectDTO getSubjectBySubjectCode(String subjectCode) {
		SubjectDTO subjectDTO = null;
		
		SubjectEntity subjectEntity = this.getSubject(subjectCode);
		if (subjectEntity != null ) {
			try {
				subjectDTO = (SubjectDTO)ModelMapperUtil.map(subjectEntity, SubjectDTO.class);
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
		}
		
		System.out.println("Subject found with subject code : " + (subjectDTO == null));
		
		return subjectDTO;
	}
	
	private SubjectEntity getSubject(String subjectCode){
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<SubjectEntity> criteria = cb.createQuery(SubjectEntity.class);
		Root<SubjectEntity> profileRoot = criteria.from(SubjectEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("subjectCode"),subjectCode));
		Query<SubjectEntity> query = session.createQuery(criteria);
		List<SubjectEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
		
	}

	
	private SubjectDTO saveSubject(SubjectDTO subjectDTO) {
		
	       if (this.getSubjectBySubjectCode(subjectDTO.getSubjectCode()) != null) {
	    	   throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
	       }
	       
	       
		ClassEntity classEntity = null;
		try {		
			classEntity =  this.getClassBy(subjectDTO.getClassCode());	
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		System.out.println("Class Retrieved with Code : " + classEntity.getClassCode());
		SubjectEntity subjectEntity = (SubjectEntity)ModelMapperUtil.map(subjectDTO,SubjectEntity.class);
		classEntity.addSubjectEntity(subjectEntity);
	    subjectEntity.setClassEntity(classEntity);
	    
	    try {
	    	session.beginTransaction();
	    	session.save(subjectEntity);
	    	session.getTransaction().commit();
	    }catch(Exception ex) {
	    	throw new RuntimeException(ex.getLocalizedMessage());
	    }
		
		return (SubjectDTO)ModelMapperUtil.map(subjectEntity, SubjectDTO.class);
	}
	
	private List<BaseDTO> getAllSubject(String classCode){
		
		List<BaseDTO> resultList = null;
		List<SubjectEntity> entityList = null;
		try {
			
			entityList = this.getClassBy(classCode).getSubjects();
		    resultList = new ArrayList<BaseDTO>();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		SubjectDTO dto;
		for(SubjectEntity entity : entityList) {
			LBLogger.logMessage(entity.toString());
			dto = (SubjectDTO)ModelMapperUtil.map(entity, SubjectDTO.class);
			resultList.add(dto);
		}
		return resultList;
	}
	
	private BaseDTO updateSubject(SubjectDTO subjectDTO){
		
		SubjectEntity subjectEntity = this.getSubject(subjectDTO.getSubjectCode());
		
		if(subjectDTO.getClassCode() != null && !subjectDTO.getClassCode().isEmpty()) {
			subjectEntity.setClassEntity(this.getClassBy(subjectDTO.getClassCode()));
		}
		
		if(subjectDTO.getSubjectName() != null && !subjectDTO.getSubjectName().isEmpty()) {
			subjectEntity.setSubjectName(subjectDTO.getSubjectName());
		}
		
		try {
			this.session.beginTransaction();
			this.session.update(subjectEntity);
			this.session.getTransaction().commit();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		return (SubjectDTO)ModelMapperUtil.map(subjectEntity, SubjectDTO.class);
	}
	
	
	// =============================================================================================
	
	
	private ModuleDTO getModuleByModuleCode(String moduleCode) {
		ModuleDTO moduleDTO = null;
		ModuleEntity moduleEntity = this.getModule(moduleCode);
		if (moduleEntity != null ) {
			try {
				moduleDTO = (ModuleDTO)ModelMapperUtil.map(moduleEntity, ModuleDTO.class);
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
		}
		
		System.out.println("Subject found with subject code : " + (moduleDTO == null));
		return moduleDTO;
	}
	
	private ModuleEntity getModule(String moduleCode){
		LBLogger.logMessage("getModule() called");
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ModuleEntity> criteria = cb.createQuery(ModuleEntity.class);
		Root<ModuleEntity> profileRoot = criteria.from(ModuleEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("moduleCode"),moduleCode));
		Query<ModuleEntity> query = session.createQuery(criteria);
		List<ModuleEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
		
	}

	
	private ModuleDTO saveModule(ModuleDTO moduleDTO) {
		
	       if (this.getModuleByModuleCode(moduleDTO.getModuleCode()) != null) {
	    	   throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
	       }
	       
		SubjectEntity subjectEntity = null;
		try {		
			subjectEntity =  this.getSubject(moduleDTO.getSubjectCode());		
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		System.out.println("subject Retrieved with Code : " + subjectEntity.getSubjectCode());
		ModuleEntity moduleEntity = (ModuleEntity)ModelMapperUtil.map(moduleDTO,ModuleEntity.class);
		subjectEntity.addModuleEntity(moduleEntity);
	    moduleEntity.setSubject(subjectEntity);
	    
	    try {
	    	session.beginTransaction();
	    	session.save(moduleEntity);
	    	session.getTransaction().commit();
	    }catch(Exception ex) {
	    	throw new RuntimeException(ex.getLocalizedMessage());
	    }
		
		return (ModuleDTO)ModelMapperUtil.map(moduleEntity, ModuleDTO.class);
	}
	
    private List<BaseDTO> getAllModule(String subjectCode){
		
		List<BaseDTO> resultList = null;
		List<ModuleEntity> entityList = null;
		try {
			
			entityList = this.getSubject(subjectCode).getModules();
		    resultList = new ArrayList<BaseDTO>();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
		
		ModuleDTO dto;
		for(ModuleEntity entity : entityList) {
			LBLogger.logMessage(entity.toString());
			dto = (ModuleDTO)ModelMapperUtil.map(entity, ModuleDTO.class);
			resultList.add(dto);
		}
		return resultList;
	}
    
    
    private ModuleDTO updateModule(ModuleDTO moduleDTO) {
    	
    	ModuleEntity moduleEntity = this.getModule(moduleDTO.getModuleCode());
    	
    	
    	if(moduleDTO.getModuleName()!=null && !moduleDTO.getModuleName().isEmpty()) {
    		moduleEntity.setModuleName(moduleDTO.getModuleName());
    	}
    	
    	if(moduleDTO.getSubjectCode() != null && !moduleDTO.getSubjectCode().isEmpty()) {
    		moduleEntity.setSubject(this.getSubject(moduleDTO.getSubjectCode()));
    	}
    	
    	try {
    		this.session.beginTransaction();
    		this.session.update(moduleEntity);
    		this.session.getTransaction().commit();
    	}catch(Exception ex) {
    		throw ex;
    	}
    	
        return (ModuleDTO)ModelMapperUtil.map(moduleEntity, ModuleDTO.class);
    }
	
	// =============================================================================================
	

	
	private TopicDTO getTopicByTopicCode(String topicCode) {
		TopicDTO topicDTO = null;
		TopicEntity topicEntity = this.getTopic(topicCode);
		if (topicEntity != null ) {
			try {
				topicDTO = (TopicDTO)ModelMapperUtil.map(topicEntity, TopicDTO.class);
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
		}
		
		System.out.println("Topic Object Found : " + (topicDTO != null));
		return topicDTO;
	}
	
	private TopicEntity getTopic(String topicCode){
		
		LBLogger.logMessage("getTopic(String topicCode) : ", topicCode);
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TopicEntity> criteria = cb.createQuery(TopicEntity.class);
		Root<TopicEntity> profileRoot = criteria.from(TopicEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("topicCode"),topicCode));
		Query<TopicEntity> query = session.createQuery(criteria);
		List<TopicEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
	}

	
	private TopicDTO saveTopic(TopicDTO topicDTO) {
		LBLogger.logMessage("saveTopic", topicDTO.toString());
	
		 if (this.getTopicByTopicCode(topicDTO.getTopicCode()) != null) {
			 LBLogger.logError("Topic already exist");
	    	   throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
	       }
	       
		 
		ModuleEntity moduleEntity = null;
		
	
		try {		
			moduleEntity =  this.getModule(topicDTO.getModuleCode());		
		}catch(Exception ex) {
			LBLogger.logError(ex.getMessage());
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		LBLogger.logMessage("ModuleEntity null : "+(moduleEntity == null));
		
		LBLogger.logMessage("Module Retrieved  : " + moduleEntity.toString());
		TopicEntity topicEntity = (TopicEntity)ModelMapperUtil.map(topicDTO,TopicEntity.class);
		LBLogger.logMessage("Save Topic -",topicEntity.toString());
		moduleEntity.addTopic(topicEntity);
		topicEntity.setEntity(moduleEntity);
	    try {
	    	session.beginTransaction();
	    	session.save(topicEntity);
	    	session.getTransaction().commit();
	    }catch(Exception ex) {
	    	LBLogger.logError(ex.getMessage());
	    	throw new RuntimeException(ex.getLocalizedMessage());
	    }
	    
		return (TopicDTO)ModelMapperUtil.map(topicEntity, TopicDTO.class);
	}
	
	 private List<BaseDTO> getAllTopic(String moduleCode){
			
			List<BaseDTO> resultList = null;
			List<TopicEntity> entityList = null;
			try {
				
				entityList = this.getModule(moduleCode).getTopics();
			    resultList = new ArrayList<BaseDTO>();
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			}
			
			TopicDTO dto;
			for(TopicEntity entity : entityList) {
				LBLogger.logMessage(entity.toString());
				dto = (TopicDTO)ModelMapperUtil.map(entity, TopicDTO.class);
				resultList.add(dto);
			}
			return resultList;
		}
	 
	 
	 private TopicDTO updateTopic(TopicDTO topicDTO) {
		 
		 TopicEntity topicEntity = this.getTopic(topicDTO.getTopicCode());
		 
		 try {
		    	session.beginTransaction();
		    	session.update(topicEntity);
		    	session.getTransaction().commit();
		    }catch(Exception ex) {
		    	LBLogger.logError(ex.getMessage());
		    	throw new RuntimeException(ex.getLocalizedMessage());
		    }
			return (TopicDTO)ModelMapperUtil.map(topicEntity, TopicDTO.class);
	 }

	
	// =============================================================================================
	 
	 private ParentDTO getParentById(String parentId) {
		 ParentDTO parentDTO = null;
		 ParentEntity parentEntity = getParent(parentId);
		 try {
			 parentDTO = (ParentDTO)ModelMapperUtil.map(parentEntity, ParentDTO.class);
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		 LBLogger.logMessage("getParentById", "Parent Found", parentDTO.toString());
		 return parentDTO;
	 }
	 
	 private ParentEntity getParent(String parentID){
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ParentEntity> criteria = cb.createQuery(ParentEntity.class);
			Root<ParentEntity> profileRoot = criteria.from(ParentEntity.class);
			criteria.select(profileRoot);
			criteria.where(cb.equal(profileRoot.get("parentID"),parentID));
			Query<ParentEntity> query = session.createQuery(criteria);
			List<ParentEntity> resultList = query.getResultList();
			LBLogger.logMessage("ResultList Size : " + resultList.size());
			return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
		}
	 
	 
	 private ParentEntity getParentByEmailandPhoneNumber(ParentDTO parentDTO) {
		 
		 CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ParentEntity> criteria = cb.createQuery(ParentEntity.class);
			Root<ParentEntity> profileRoot = criteria.from(ParentEntity.class);
			criteria.select(profileRoot);
			criteria.where(cb.equal(profileRoot.get("parentEmail"),parentDTO.getParentEmail()));
			criteria.where(cb.equal(profileRoot.get("parentNumber"),parentDTO.getParentNumber()));
		   
			Query<ParentEntity> query = session.createQuery(criteria);
			List<ParentEntity> resultList = query.getResultList();
			LBLogger.logMessage("ResultList Size : " + resultList.size());
			return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
	 }
	 
	 
	 private ParentDTO saveParent(ParentDTO parentDTO) {
		 
		 if(this.getParentByEmailandPhoneNumber(parentDTO)!=null){
			 throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
		 }
		 
		 ParentEntity parentEntity = (ParentEntity)ModelMapperUtil.map(parentDTO, ParentEntity.class);
		 
		 try {
			 session.beginTransaction();
			 session.save(parentEntity);
			 session.getTransaction().commit();
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		
		 return parentDTO;
	 }
	 
	 
	 private ParentDTO updateParent(ParentDTO parentDTO) {
		 ParentEntity parentEntity = this.getParent(parentDTO.getParentID());
		 
		 if(parentDTO.getParentAddress() != null && !parentDTO.getParentAddress().isEmpty()) {
			 parentEntity.setParentAddress(parentDTO.getParentAddress());
		 }
		 
		 if(parentDTO.getParentEmail() != null && !parentDTO.getParentEmail().isEmpty()) {
			 parentEntity.setParentEmail(parentDTO.getParentEmail());
		 }
		 
		 if(parentDTO.getParentName() != null && !parentDTO.getParentName().isEmpty()) {
			 parentEntity.setParentName(parentDTO.getParentName());
		 }
		 
		 if(parentDTO.getParentNumber() != null && !parentDTO.getParentNumber().isEmpty()) {
			 parentEntity.setParentNumber(parentDTO.getParentNumber());
		 }
		 
		 
		 try {
			 session.beginTransaction();
			 session.update(parentEntity);
			 session.getTransaction().commit();
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		 return (ParentDTO)ModelMapperUtil.map(parentEntity, ParentDTO.class);
	 }
	// =============================================================================================
	 
	 
	 private ChildResponseDTO getChildById(String childID) {
		 ChildResponseDTO childDTO = null;
		 ChildEntity childEntity = getChild(childID);
		 try {
			 childDTO = (ChildResponseDTO)ModelMapperUtil.map(childEntity, ChildResponseDTO.class);
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		 LBLogger.logMessage("getChildById", "Child Found", childDTO.toString());
		 return childDTO;
	 }
	 
	 
	 
	 
	 private ChildEntity getChild(String childID){
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<ChildEntity> criteria = cb.createQuery(ChildEntity.class);
			Root<ChildEntity> profileRoot = criteria.from(ChildEntity.class);
			criteria.select(profileRoot);
			criteria.where(cb.equal(profileRoot.get("childId"),childID));
			Query<ChildEntity> query = session.createQuery(criteria);
			List<ChildEntity> resultList = query.getResultList();
			LBLogger.logMessage("ResultList Size : " + resultList.size());
			return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
		}
	 
	 
     private ChildResponseDTO saveChild(ChildDTO childDTO) {
		 
		 if(this.getChild(childDTO.getChildId())!=null){
			 throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
		 }
		 
		 LBLogger.logMessage(childDTO.getParentId());
		 TeacherEntity teacher = this.getTeacher(childDTO.getTeacherId());
		 if(teacher == null) {
			 throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		 }
		 
		 
		 LBLogger.logMessage(childDTO.getParentId());
		 ParentEntity parent = this.getParent(childDTO.getParentId());
		 if(parent == null) {
			 throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		 }
		 
		 ClassEntity childClass = this.getClassBy(childDTO.getChildClassCode());
		 if(childClass == null) {
			 throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		 }
		 
		 ChildEntity childEntity = (ChildEntity)ModelMapperUtil.map(childDTO, ChildEntity.class);
		 
		 
		 teacher.getChildren().add(childEntity);
		 parent.getChildren().add(childEntity);
		 
     	 childEntity.setTeacher(teacher);
		 childEntity.setParent(parent);
	     childEntity.setChildClass(childClass);
		 
		 try {
			 session.beginTransaction();
			 session.save(childEntity);
			 session.getTransaction().commit();
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		 
		 ChildResponseDTO responseObject = (ChildResponseDTO)ModelMapperUtil.map(childEntity, ChildResponseDTO.class);
		 return responseObject;
	 }
     
     
     private ChildResponseDTO updateChild(ChildDTO childDTO){
    	 
    	 ChildEntity childEntity = this.getChild(childDTO.getChildId());
    	 
    	 if(childDTO.getChildClassCode() != null && !childDTO.getChildClassCode().isEmpty()) {
    		 childEntity.setChildClass(this.getClassBy(childDTO.getChildClassCode()));
    	 }
    	 
    	 if(childDTO.getChildName() != null && !childDTO.getChildName().isEmpty()) {
    		 childEntity.setChildName(childDTO.getChildName());
    	 }
    	 
    	 if(childDTO.getTeacherId() != null && !childDTO.getTeacherId().isEmpty()) {
    		 childEntity.setTeacher(this.getTeacher(childDTO.getTeacherId()));
    	 }
    	 
    	 if(childDTO.getChildSchool() != null && !childDTO.getChildSchool().isEmpty()) {
    		 childEntity.setChildSchool(childDTO.getChildSchool());
    	 }
    	 
    	 
    	 try {
			 session.beginTransaction();
			 session.update(childEntity);
			 session.getTransaction().commit();
		 }catch(Exception ex) {
			 throw new RuntimeException(ex);
		 }
		 
		 ChildResponseDTO responseObject = (ChildResponseDTO)ModelMapperUtil.map(childEntity, ChildResponseDTO.class);
		 return responseObject;
    	 
     }
     
	// =============================================================================================
	 
	 
		
	}

