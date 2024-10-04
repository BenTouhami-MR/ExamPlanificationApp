package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.core.bo.ElementPedagogique;

public interface IElementPedagogiqueRepositoryDao extends JpaRepository<ElementPedagogique,Long>{

	ElementPedagogique getElementPedagogiqueByTitre(String titre);
}
