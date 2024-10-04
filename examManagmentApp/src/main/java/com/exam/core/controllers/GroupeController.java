package com.exam.core.controllers;

import java.util.*;

import com.exam.core.web.models.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.exam.core.bo.Departement;
import com.exam.core.bo.Enseignant;
import com.exam.core.bo.Filiere;
import com.exam.core.bo.Groupe;

import java.util.stream.Collectors;

import com.exam.core.services.IDepartementService;
import com.exam.core.services.IEnseignantService;
import com.exam.core.services.IFiliereService;
import com.exam.core.services.IGroupeService;

import javax.swing.*;

@RestController
@RequestMapping("/api/admin/groupe")
@CrossOrigin(origins="http://localhost:3000")
public class GroupeController {

	@Autowired
	private IGroupeService groupeService;
	
	@Autowired
	private IEnseignantService ensgService;
	
	@Autowired
	private IFiliereService filiereService;
	
	@Autowired
	private IDepartementService departementService;
	
	
 	@DeleteMapping("/delete/{idGroupe}/member/{idEnseignant}")
	public String deleteMembre(@PathVariable long idGroupe,@PathVariable long idEnseignant){
		groupeService.deleteEnseignantById(Long.valueOf(idGroupe),Long.valueOf(idEnseignant));

		return "The member is deleted succussfully";

	}




	@GetMapping("getEnseignants/{type}/{id}")
	public List<EnseignantModel> getEnseignants(@PathVariable String type,@PathVariable long id){

		List<Enseignant> es;

		if (type.equalsIgnoreCase("dep")){

		Departement dep = departementService.getDepartementById(Long.valueOf(id));
		es = ensgService.getEnseignantByDepartement(dep);

		} else if (type.equalsIgnoreCase("fil")) {

		Filiere fil = filiereService.getFiliereById(Long.valueOf(id));
		es = ensgService.getEnseignantByFiliere(fil);

		}
		else {

			es = ensgService.getAllEnseignants();
		}
		return es.stream().map(EnseignantModel::new).collect(Collectors.toList()); // return 200 OK with JSON
	}




	@GetMapping("list")
	public  List<GroupeModel> getGroups(){

		List<Groupe> groupesList = groupeService.getAllGroupes();

		// Initialize lazy-loaded fields manually before returning the result
		for (Groupe groupe : groupesList) {
			for (Enseignant e : groupe.getEnseignants()){

				e.getFiliere();  // Ensures Filiere is loaded
				e.getDepartement();  // Ensures Departement is loaded
			}

		}

		return groupesList.stream().map(GroupeModel::new).collect(Collectors.toList()); // return 200 OK with JSON


	}




	@PostMapping("addEnseignants/{idGroupe}")
	public String addEnseignants (@RequestBody SelectedEnseignants set,@PathVariable long idGroupe){

		 for (Integer id :set.getEnseignants()){
			 groupeService.ajouterUnEnseignant(Long.valueOf(id),Long.valueOf(idGroupe));
		 }

		System.out.println(set.getEnseignants());
		return "members succussfully added to the group";
	}



	@DeleteMapping("delete/{idGroupe}")
	public String deleteGroupe(@PathVariable long idGroupe){

		 groupeService.deleteGroupe(Long.valueOf(idGroupe));

		 return "Group is deleted successfully";
	}




	@GetMapping("initialData")
	public GroupInitialData getInitalData(){

		GroupInitialData idt=new GroupInitialData(filiereService.getAllFilieres(),departementService.getAllDepartements());

		 return idt;

	}




	@PostMapping("create")
	public String createGroupe(@RequestBody GroupeModel gm){
		 Groupe g = new Groupe();
		BeanUtils.copyProperties(gm,g);
		if(gm.getGroupeType().equalsIgnoreCase("General Group")){
			g.setGroupeType("aleatoire");
		} else if (gm.getGroupeType().equalsIgnoreCase("Department Group")) {
			g.setGroupeType("dep");

		} else if (gm.getGroupeType().equalsIgnoreCase("Sector Group")) {
			g.setGroupeType("fil");
		}
		Set<Enseignant> es =new HashSet<>();

		for (Long idgrp : gm.getEnseignantsIds()){
			es.add(ensgService.getEnseignantById(idgrp));
		}
		g.setEnseignants(es);

		groupeService.createGroupe(g);


		return "Group Created Successfully";
	}


	/**
	 * 
	 * @param groupeModel
	 * @param bindingResult
	 * @param model
	 * @return
	 */
//	@PostMapping("addEnseignants")
//	public String addEnseignants(@Valid @ModelAttribute GroupeModel groupeModel,BindingResult bindingResult,Model model) {
//
//		insertDataInModel(model, groupeModel.getTypeGroupe());
//		if(bindingResult.hasErrors()) {
//			return "admin/groupe/DepFilGroupeForm";
//
//		}
//
//
//
//		System.out.println("NomFil: "+groupeModel.getFiliere());
//		Set<Enseignant> enseignants;
//		if(groupeModel.getTypeGroupe()==GroupeModel.TYPE_FILIERE) {
//			enseignants =  groupeModel.getFiliere().getEnseignants();
//
//		}else {
//			 enseignants = groupeModel.getDepartement().getEnseignants();
//
//		}
//
//
//		model.addAttribute("listEnseignants",enseignants);
//
//		return "admin/groupe/DepFilListEnseignants";
//	}
//
//
//
//	/**
//	 *
//	 * @param groupeModel
//	 * @param bindingResult
//	 * @param model
//	 * @return
//	 */
//
//	@PostMapping("addGroupe")
//	public String createGroupe(@Valid @ModelAttribute GroupeModel groupeModel,BindingResult bindingResult,Model model) {
//		System.out.println(groupeModel.getEnseignants());
//		System.out.println(groupeModel.getTypeGroupe());
//
//
//		insertDataInModel(model, groupeModel.getTypeGroupe());
//
//		if(bindingResult.hasErrors()) {
//
//			if(groupeModel.getTypeGroupe() == GroupeModel.TYPE_ALEATOIRE) {
//				return "admin/groupe/aleatoireGroupeForm";
//			}else {
//
//				return "admin/groupe/DepFilGroupeForm";
//			}
//		}
//
//
////		coller l'objet groupeModel dans l'objet groupe
//		Groupe groupe =new Groupe();
//		BeanUtils.copyProperties(groupeModel, groupe);
//
//
////		cree le groupe
//		groupeService.createGroupe(groupe);
//
//
////		ajouter un message de succees
//
//		return "redirect:/admin/groupe/form?typeGroupe="+groupeModel.getTypeGroupe();
//
//
//	}
//
//	/**
//	 *
//	 * @param model
//	 * @return
//	 */
//
//	@RequestMapping("manageGroupe")
//	public String showGroupeList(Model model) {
//
//		model.addAttribute("ListGroupes",groupeService.getAllGroupes());
//		return "admin/groupe/listGroupe";
//	}
//
//
//	/**
//	 *
//	 * @param idGroupe
//	 * @param model
//	 * @return
//	 */
//
//	@RequestMapping("listEnseignantsDuGroupe/{idGroupe}")
//	public String EnseignantsOfGroupe(@PathVariable int idGroupe, Model model) {
//
//		Groupe groupe = groupeService.getGroupeById(Long.valueOf(idGroupe));
//
//		model.addAttribute("EnseignantsList",groupe.getEnseignants());
//
//		return "admin/groupe/listEnseignants";
//	}
}
