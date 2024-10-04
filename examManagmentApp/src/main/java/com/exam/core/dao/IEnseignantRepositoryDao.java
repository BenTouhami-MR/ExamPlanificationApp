package com.exam.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;

public interface IEnseignantRepositoryDao extends JpaRepository<Enseignant, Long> {
			List<Enseignant> getEnseignantsByFiliere(Filiere filiere);
			List<Enseignant> getEnseignantsByDepartement(Departement departement);
}
