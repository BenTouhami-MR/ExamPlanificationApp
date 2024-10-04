package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Semestre;

public interface ISemestreService {
	
	public void addSemestre(Semestre pSemestre);

	public void updateSemestre(Semestre pSemestre);

	public List<Semestre> getAllSemestres();

	public void deleteSemestre(Long id);

	public Semestre getSemestreById(Long id);	
}
