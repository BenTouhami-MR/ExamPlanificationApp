package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.Surveillance;

public interface ISurveillanceService {
	public void addSurveillance(Surveillance pSurveillance);

	public void updateSurveillance(Surveillance pSurveillance);

	public List<Surveillance> getAllSurveillances();

	public void deleteSurveillance(Long id);

	public Surveillance getSurveillanceById(Long id);
}

