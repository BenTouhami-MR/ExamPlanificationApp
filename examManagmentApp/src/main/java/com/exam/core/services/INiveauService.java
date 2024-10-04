package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Niveau;
import com.exam.core.bo.TypeElement;

public interface INiveauService {
	public Niveau getNiveauById(Long id);
	public List<Niveau> getAllNivaux();


}
