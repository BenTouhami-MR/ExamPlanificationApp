package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.TypeElement;

public interface ITypeElementService {
	
	public TypeElement getTypeElementById(Long id);
	public List<TypeElement> getAllTypeElements();

}
