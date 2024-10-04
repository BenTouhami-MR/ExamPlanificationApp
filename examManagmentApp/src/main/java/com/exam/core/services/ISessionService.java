package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Session;

public interface ISessionService {
	public void addSession(Session pSession);

	public void updateSession(Session pSession);

	public List<Session> getAllSessions();

	public void deleteSession(Long id);

	public Session getSessionById(Long id);
}
