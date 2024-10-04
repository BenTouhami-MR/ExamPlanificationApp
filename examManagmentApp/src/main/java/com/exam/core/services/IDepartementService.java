package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Departement;


public interface IDepartementService {
	
	
	public void addDepartement(Departement pDepartement);

	public void updateDepartement(Departement pDepartement);

	public List<Departement> getAllDepartements();

	public void deleteDepartement(Long id);

	public Departement  getDepartementById(Long id);

	public Departement getDepartementByNom(String nomDepartment);
	

}
