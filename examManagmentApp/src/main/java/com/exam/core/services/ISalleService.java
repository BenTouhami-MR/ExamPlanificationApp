package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Salle;

public interface ISalleService {
	public void addSalle(Salle pSalle);

	public void updateSalle(Salle pSalle);

	public List<Salle> getAllSalles();

	public void deleteSalle(Long id);

	public Salle getSalleById(Long id);
}
