package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Session;
import com.exam.core.dao.ISessionRepositoryDao;
import com.exam.core.services.ISessionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ISessionServiceImp implements ISessionService{
	
	@Autowired
	private ISessionRepositoryDao sessionDao;
	
	
	
	
	
	public void addSession(Session pSession) {
		
		sessionDao.save(pSession);
		
	}
	
	
	public void updateSession(Session pSession) {
		
		sessionDao.save(pSession);
		
	}
	
	public void deleteSession(Long id) {
		
		sessionDao.deleteById(id);
	}
	
	
	public List<Session> getAllSessions(){
		
		
		return sessionDao.findAll();
		
	}
	
	
	public Session getSessionById(Long id) {
		
		return sessionDao.findById(id).get();
	}
	

}
