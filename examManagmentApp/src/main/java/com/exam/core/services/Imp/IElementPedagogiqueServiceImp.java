package com.exam.core.services.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.ElementPedagogique;
import com.exam.core.dao.IElementPedagogiqueRepositoryDao;
import com.exam.core.services.IElementPedagogiqueService;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class IElementPedagogiqueServiceImp implements IElementPedagogiqueService{
	
	
	@Autowired
	private IElementPedagogiqueRepositoryDao elementPedagogiqueDao;

	
	
	
	public void addElementPedagogique(ElementPedagogique pElementPedagogique) {
		
		elementPedagogiqueDao.save(pElementPedagogique);
	}
	
	
	public void updateElementPedagogique(ElementPedagogique pElementPedagogique) {
		
		elementPedagogiqueDao.save(pElementPedagogique);		
	}
	
	public void deleteElementPedagogique(Long id) {
		
		elementPedagogiqueDao.deleteById(id);
	}
	
	
	public List<ElementPedagogique> getAllElementPedagogiques(){
		
		
		return elementPedagogiqueDao.findAll();
		
	}
	
	
	public ElementPedagogique getElementPedagogiqueById(Long id) {
		
		return elementPedagogiqueDao.findById(id).get();
	}


	public ElementPedagogique getElementPedagogiqueByTitre(String titre) {
		return elementPedagogiqueDao.getElementPedagogiqueByTitre(titre);
	}
	
	

	

}
