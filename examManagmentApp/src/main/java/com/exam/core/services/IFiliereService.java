package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Filiere;

public interface IFiliereService {
	public void addFiliere(Filiere pFiliere);

	public void updateFiliere(Filiere pFiliere);

	public List<Filiere> getAllFilieres();

	public void deleteFiliere(Long id);

	public Filiere getFiliereById(Long id);
}
