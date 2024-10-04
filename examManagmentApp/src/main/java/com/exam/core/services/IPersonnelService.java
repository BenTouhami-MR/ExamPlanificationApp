package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Personnel;

public interface IPersonnelService {
	
	public void addPersonnel(Personnel pPersonnel);

	public void updatePersonnel(Personnel pPersonnel);

	public List<Personnel> getAllPersonnels();

	public void deletePersonnel(Long id);

	public Personnel getPersonnelById(Long id);

	
	

}
