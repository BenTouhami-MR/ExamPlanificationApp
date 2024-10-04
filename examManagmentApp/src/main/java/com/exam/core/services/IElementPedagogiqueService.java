package com.exam.core.services;

import java.util.List;

import com.exam.core.bo.ElementPedagogique;

public interface IElementPedagogiqueService {

	
public void addElementPedagogique(ElementPedagogique pElementPedagogique);

public void updateElementPedagogique(ElementPedagogique pElementPedagogique);

public List<ElementPedagogique> getAllElementPedagogiques();

public void deleteElementPedagogique(Long id);

public ElementPedagogique  getElementPedagogiqueById(Long id);

public ElementPedagogique getElementPedagogiqueByTitre(String titre);


}
