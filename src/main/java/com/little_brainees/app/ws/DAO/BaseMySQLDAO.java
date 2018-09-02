package com.little_brainees.app.ws.DAO;

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
import com.little_brainees.app.ws.io.entity.ClassEntity;
import com.little_brainees.app.ws.io.entity.ModuleEntity;
import com.little_brainees.app.ws.io.entity.SubjectEntity;
import com.little_brainees.app.ws.io.entity.TeacherEntity;
import com.little_brainees.app.ws.io.entity.TopicEntity;
import com.little_brainees.app.ws.utilities.HibernateUtils;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;

public class BaseMySQLDAO implements DAO {
	
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
	
		
	
	
	// =============================================================================================
		
	@Override
	public TeacherDTO getTeacherByEmail(String email) {
		
		TeacherDTO teacherDTO = null;
		TeacherEntity  teacherEntity = this.getTeacher(email); 
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
	
	
	private TeacherEntity getTeacher(String email){
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TeacherEntity> criteria = cb.createQuery(TeacherEntity.class);
		Root<TeacherEntity> profileRoot = criteria.from(TeacherEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("teacherEmail"), email));
		Query<TeacherEntity> query = session.createQuery(criteria);
		List<TeacherEntity> resultList = query.getResultList();
		LBLogger.logMessage("ResultList Size : " + resultList.size());
		
		return  (resultList == null || resultList.size() == 0)? null : resultList.get(0);
	}



	@Override
	public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
			
		if (this.getTeacherByEmail(teacherDTO.getTeacherEmail()) != null) 
			throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
		
		
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

	
	// =============================================================================================
	
	@Override
	public ClassDTO getClassByClassCode(String classCode) {
	
		ClassDTO classDTO = null;
		ClassEntity classEntity = this.getClassBy(classCode);
		if(classEntity != null ) {
			try {
			classDTO = new ClassDTO(classEntity.getClassCode(),classEntity.getClassName());
			
			}catch(Exception ex) {
				System.out.println("Error Occured");
			}
		}
		
		System.out.println("No record Found : " + (classDTO == null));
		
		return classDTO;
	}
	
	private ClassEntity getClassBy(String classCode){
		
		System.out.println("Is session open : "+session.isOpen());
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
	

	@Override
	public ClassDTO saveClass(ClassDTO classDTO){
		
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
	
	// =============================================================================================

	@Override
	public SubjectDTO getSubjectBySubjectCode(String subjectCode) {
		SubjectDTO subjectDTO = null;
		
		SubjectEntity subjectEntity = this.getSubject(subjectCode);
		LBLogger.logMessage("Modules present in - "+subjectEntity.toString()+" are : "+subjectEntity.getModules().get(0).getTopics());
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

	@Override
	public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
		
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
	
	
	// =============================================================================================
	
	@Override
	public ModuleDTO getModuleByModuleCode(String moduleCode) {
		ModuleDTO moduleDTO = null;
		SubjectEntity subjectEntity = this.getSubject(moduleCode);
		if (subjectEntity != null ) {
			try {
				moduleDTO = (ModuleDTO)ModelMapperUtil.map(subjectEntity, SubjectDTO.class);
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
		}
		
		System.out.println("Subject found with subject code : " + (moduleDTO == null));
		return moduleDTO;
	}
	
	private ModuleEntity getModule(String moduleCode){
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

	@Override
	public ModuleDTO saveModule(ModuleDTO moduleDTO) {
		
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
	
	// =============================================================================================
	

	@Override
	public TopicDTO getTopicByTopicCode(String topicCode) {
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

	@Override
	public TopicDTO saveTopic(TopicDTO topicDTO) {
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
	    
		return (TopicDTO)ModelMapperUtil.map(moduleEntity, TopicDTO.class);
	}
	
	// =============================================================================================
	
	
		
	}

