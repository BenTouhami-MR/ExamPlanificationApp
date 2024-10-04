package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.TypeElement;
import com.exam.core.dao.ITypeElementRepositoryDao;
import com.exam.core.services.ITypeElementService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ITypeElementServiceImp  implements ITypeElementService{
	
	
	@Autowired
	private ITypeElementRepositoryDao elmentDao;
	
	public List<TypeElement> getAllTypeElements(){
		
		
		return elmentDao.findAll();
		
	}
	
	


	public TypeElement getTypeElementById(Long id) {
		return  elmentDao.findById(id).get();
	}





	

}
