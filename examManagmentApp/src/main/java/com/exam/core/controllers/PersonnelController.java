
package com.exam.core.controllers;

import com.exam.core.bo.*;
import com.exam.core.web.models.PersonnelInitialData;
import com.exam.core.services.IElementPedagogiqueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.core.web.models.PersonnelModel;
import com.exam.core.services.IDepartementService;
import com.exam.core.services.IFiliereService;
import com.exam.core.services.IPersonnelService;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/admin")
@CrossOrigin(origins = "*")
public class PersonnelController {

	@Autowired
	private IPersonnelService personnelService;

	@Autowired
	private IFiliereService filiereService;

	@Autowired
	private IDepartementService departementService;

	@Autowired
	private IElementPedagogiqueService elmPedag;




	// Method to know personnel type and return a list of PersonnelModel
	public List<PersonnelModel> knowPersonnelType(List<Personnel> personnels) {
		List<PersonnelModel> modelPersonnels = new ArrayList<>();
		for (Personnel personnel : personnels) {
			PersonnelModel pm = new PersonnelModel();
			if (personnel instanceof Enseignant) {
				BeanUtils.copyProperties(personnel, pm);
				pm.setTypePersonnel(PersonnelModel.TYPE_ENSEIGN);
				modelPersonnels.add(pm);
			} else if (personnel instanceof Administrateur) {
				BeanUtils.copyProperties(personnel, pm);
				pm.setTypePersonnel(PersonnelModel.TYPE_ADMIN);
				modelPersonnels.add(pm);
			}
		}
		return modelPersonnels;
	}




	// Get all personnels as JSON
	@GetMapping("/personnels")
	public List<PersonnelModel> getAllPersonnels() {
		List<Personnel> personnels = personnelService.getAllPersonnels();

		return personnels.stream().map(PersonnelModel::new).collect(Collectors.toList()); // return 200 OK with JSON
	}




	// Get all personnels as JSON
	@GetMapping("/personnelInitialeData")
	public PersonnelInitialData getInitialData() {
		List<Filiere> fileresList = filiereService.getAllFilieres();
		List<Departement> departementList =departementService.getAllDepartements();

		PersonnelInitialData pIData =new PersonnelInitialData();
		pIData.setDepartementsList(departementList);
		pIData.setFilieresList(fileresList);

		return pIData;
	}




	// Add a new Personnel (Admin or Enseignant)
	@PostMapping("/addpersonnel")
	public ResponseEntity<String> addPersonnel(@Valid @RequestBody PersonnelModel personnelModel) {

		if(personnelModel.getTypePersonnel()==PersonnelModel.TYPE_ENSEIGN){
			Filiere filiere  = filiereService.getFiliereById(Long.valueOf(personnelModel.getId_filiere()));
			Departement departement = departementService.getDepartementById(Long.valueOf(personnelModel.getId_departement()));
			Enseignant e =new Enseignant();
			e.setDepartement(departement);
			e.setFiliere(filiere);
			e.setNom(personnelModel.getNom());
			e.setPrenom(personnelModel.getPrenom());

			personnelService.addPersonnel(e);

		}else{
			Administrateur adm  = new Administrateur();
			BeanUtils.copyProperties(personnelModel,adm);
			personnelService.addPersonnel(adm);

		}
		return new ResponseEntity<>(personnelModel.getPrenom() + " is added successfully",HttpStatus.CREATED);
	}





	// Delete personnel by ID
	@DeleteMapping("/personnel/{idPersonnel}")
	public ResponseEntity<String> deletePersonnel(@PathVariable("idPersonnel") long idPersonnel) {

		personnelService.deletePersonnel(Long.valueOf(idPersonnel));
		System.out.println("deleted successfully");

		return new ResponseEntity<>("Personnel is deleted successfully.", HttpStatus.OK); // 204 No Content
	}




	// Get a personnel by ID
	@GetMapping("/personnel/{idPersonnel}")
	public ResponseEntity<PersonnelModel> getPersonnelById(@PathVariable("idPersonnel") long idPersonnel) {

		Personnel personnel = personnelService.getPersonnelById(idPersonnel);

		return new ResponseEntity<>(new PersonnelModel(personnel), HttpStatus.OK); // return 200 OK with JSON
	}




	// Update personnel
	@PostMapping("/updatePersonnel/{personnelId}")
	public ResponseEntity<String> updatePersonnel(@PathVariable("personnelId") long personnelId,
												  @Valid @RequestBody PersonnelModel personnelModel) {
		System.out.println("Hello in update function =============================");
		Personnel personnel = personnelService.getPersonnelById(Long.valueOf(personnelId));
		System.out.println(personnelModel);


		if (personnel == null) {
			return new ResponseEntity<>("Personnel not found", HttpStatus.NOT_FOUND); // 404 Not Found
		}

		System.out.println(personnelModel.getTypePersonnel() == PersonnelModel.TYPE_ENSEIGN);
		if (personnelModel.getTypePersonnel() == PersonnelModel.TYPE_ENSEIGN) {
			System.out.println("hello I'm teacher");
			BeanUtils.copyProperties(personnelModel, personnel);
			System.out.println(personnel);

			/// mise a jour de la filiere
			Long idFil =Long.valueOf(personnelModel.getId_filiere());
			((Enseignant)personnel).setFiliere(filiereService.getFiliereById(idFil));
			System.out.println(idFil);

			/// mise a jour du departement
			Long idDep =Long.valueOf(personnelModel.getId_departement());
			((Enseignant)personnel).setDepartement(departementService.getDepartementById(idDep));
			System.out.println(idDep.toString());

			System.out.println(personnel);
			personnelService.updatePersonnel((Enseignant) personnel);

		} else if (personnelModel.getTypePersonnel() == PersonnelModel.TYPE_ADMIN) {

			BeanUtils.copyProperties(personnelModel, personnel);
			personnelService.updatePersonnel((Administrateur) personnel);
		}

		return new ResponseEntity<>("Personnel "+personnelModel.getNom()+" "+personnelModel.getPrenom()+ " is updated successfully", HttpStatus.OK); // 200 OK
	}
}