package com.little_brainees.app.ws.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.DTO.TeacherDTO;
import com.little_brainees.app.ws.exceptions.DuplicateRecordFoundException;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.RecordNotFoundException;
import com.little_brainees.app.ws.io.entity.ClassEntity;
import com.little_brainees.app.ws.io.entity.SubjectEntity;
import com.little_brainees.app.ws.io.entity.TeacherEntity;
import com.little_brainees.app.ws.utilities.HibernateUtils;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;

public class BaseMySQLDAO implements DAO {
	
	Session session ;

	@Override 
	public void openConnection() {
		SessionFactory sessionFactory =  HibernateUtils.getSessionFactory();
		this.session = sessionFactory.openSession();

	}
	
	@Override
	public void closeConnection() {
		if (this.session != null) {
			this.session.close();
		}

	}
	
	
	// =============================================================================================
		
	@Override
	public TeacherDTO getTeacherByEmail(String email) {
		
		TeacherDTO teacherDTO = null;
		List<TeacherEntity> resultList = this.getTeacher(email); 
		if( resultList != null && resultList.size() > 0) {		
			TeacherEntity  teacherEntity = resultList.get(0);
			try {
			teacherDTO = ModelMapperUtil.shared.getMapper().map(teacherEntity, TeacherDTO.class);
			}catch(Exception ex){
				System.out.println("Error Occured");
			}
		}
		System.out.println("No record Found : " + (teacherDTO == null));
		return teacherDTO; 
	}
	
	
	private List<TeacherEntity> getTeacher(String email){
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TeacherEntity> criteria = cb.createQuery(TeacherEntity.class);
		Root<TeacherEntity> profileRoot = criteria.from(TeacherEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("teacherEmail"), email));
		Query<TeacherEntity> query = session.createQuery(criteria);
		return query.getResultList();
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
		List<ClassEntity> resultList = this.getClassBy(classCode);
		if(resultList != null && resultList.size() > 0) {
			ClassEntity classEntity = resultList.get(0);
			try {
			classDTO = new ClassDTO(classEntity.getClassCode(),classEntity.getClassName());
			
			}catch(Exception ex) {
				System.out.println("Error Occured");
			}
		}
		
		System.out.println("No record Found : " + (classDTO == null));
		
		return classDTO;
	}
	
	private List<ClassEntity> getClassBy(String classCode){
		
		System.out.println("Is session open : "+session.isOpen());
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ClassEntity> criteria = cb.createQuery(ClassEntity.class);
		Root<ClassEntity> profileRoot = criteria.from(ClassEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("classCode"),classCode));
		Query<ClassEntity> query = session.createQuery(criteria);
		
		return query.getResultList();
	}
	

	@Override
	public ClassDTO saveClass(ClassDTO classDTO){
		
		if (this.getClassByClassCode(classDTO.getClassCode()) != null)
		  throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());	
		     
			ClassEntity classEntity = ModelMapperUtil.shared.getMapper().map(classDTO, ClassEntity.class);
			try {
			session.beginTransaction();
			session.save(classEntity);
			session.getTransaction().commit();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
			ClassDTO createdDTO = ModelMapperUtil.shared.getMapper().map(classEntity, ClassDTO.class);
			return createdDTO;
		}
	
	// =============================================================================================

	@Override
	public SubjectDTO getSubjectBySubjectCode(String subjectCode) {
		SubjectDTO subjectDTO = null;
		
		System.out.println("before subject resultList");
		List<SubjectEntity> resultList = this.getSubject(subjectCode);
		System.out.println("after subject resultList");
		
		if (resultList != null && resultList.size() > 0) {
			SubjectEntity subjectEntity = resultList.get(0);
			
			try {
				System.out.println("before mapping");
				subjectDTO = ModelMapperUtil.shared.getMapper().map(subjectEntity, SubjectDTO.class);
				System.out.println("afterMapping");
			}catch(Exception ex) {
				throw new RuntimeException(ex.getLocalizedMessage());
			}
		}
		
		System.out.println("Subject found with subject code : " + (subjectDTO == null));
		
		return subjectDTO;
	}
	
	private List<SubjectEntity> getSubject(String subjectCode){
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<SubjectEntity> criteria = cb.createQuery(SubjectEntity.class);
		Root<SubjectEntity> profileRoot = criteria.from(SubjectEntity.class);
		criteria.select(profileRoot);
		criteria.where(cb.equal(profileRoot.get("subjectCode"),subjectCode));
		Query<SubjectEntity> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
		
		SubjectDTO existingDTO = this.getSubjectBySubjectCode(subjectDTO.getSubjectCode());
	       if (existingDTO != null) {
	    	   throw new DuplicateRecordFoundException(ErrorMessages.DUPLICATE_RECORD_FOUND.getErrorMessage());
	       }
	       
	       
		ClassEntity classEntity = null;
		try {		
			classEntity =  this.getClassBy(subjectDTO.getClassCode()).get(0);			
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		System.out.println("Class Retrieved with Code : " + classEntity.getClassCode());
		SubjectEntity subjectEntity = ModelMapperUtil.shared.getMapper().map(subjectDTO,SubjectEntity.class);
		classEntity.addSubjectEntity(subjectEntity);
	    subjectEntity.setClassEntity(classEntity);
	    
	    try {
	    	session.beginTransaction();
	    	session.save(subjectEntity);
	    	session.getTransaction().commit();
	    }catch(Exception ex) {
	    	throw new RuntimeException(ex.getLocalizedMessage());
	    }
		
		return ModelMapperUtil.shared.getMapper().map(subjectEntity, SubjectDTO.class);
	}
	
	
	// =============================================================================================
	
	
	private void checkSession() {
		if(!session.isOpen())
		   {
			this.openConnection();
		   }
	}
	
		
	}

