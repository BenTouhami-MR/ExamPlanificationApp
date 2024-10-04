package com.exam.core.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.exam.core.bo.*;
import com.exam.core.services.IElementPedagogiqueService;
import com.exam.core.services.IEnseignantService;
import com.exam.core.services.INiveauService;
import com.exam.core.services.ITypeElementService;
import com.exam.core.web.models.*;
import com.exam.core.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/admin/elementsPed")
@CrossOrigin(origins = "http://localhost:3000")
public class ElemenentPedagController {

	@Autowired
	private IElementPedagogiqueService elementPedagService;

	@Autowired
	private ITypeElementService typeElementService;

	@Autowired
	private IEnseignantService enseignantService;

	@Autowired
	private INiveauService niveauService;


	/**
	 * fontion pour inserer les donnees necessaire dans le modele
	 *
	 * @param model
	 */
	public void insertNecessaryInfoInModel(Model model) {

		//récuperer les types d'elements
		Map<Long, String> listTypeElements = new HashMap<Long, String>();

		List<TypeElement> typesElements = typeElementService.getAllTypeElements();

		for (TypeElement te : typesElements) {

			listTypeElements.put(te.getIdTypeElm(), te.getType());
		}


		//récuperer les coordinateurs

		Map<Long, String> listEnseignants = new HashMap<Long, String>();

		List<Enseignant> enseignants = enseignantService.getAllEnseignants();

		for (Enseignant c : enseignants) {

			listEnseignants.put(c.getIdPersonnel(), c.getNom() + " " + c.getPrenom() + " [" + c.getFiliere().getNomFiliere() + "/" + c.getDepartement().getNomDepartement() + "]");
		}


		//récuperer les niveaux

		Map<Long, String> listNiveaux = new HashMap<Long, String>();

		List<Niveau> niveaux = niveauService.getAllNivaux();

		for (Niveau n : niveaux) {

			listNiveaux.put(n.getIdNiv(), n.getType());
		}


//
//
//
		model.addAttribute("listTypeElements", listTypeElements);
		model.addAttribute("listEnseignant", listEnseignants);
		model.addAttribute("listCoordinateur", listEnseignants);

		model.addAttribute("listNiveau", listNiveaux);
		model.addAttribute("ElementsPedagogiqueList", elementPedagService.getAllElementPedagogiques());
	}


	public void insertNecessaryInfoInModelForUpdate(Model model, ElementPedagogique elmPadag) {


		//récuperer les Types d'elements


		List<TypeElement> typeElements = typeElementService.getAllTypeElements();
		int index = typeElements.indexOf(elmPadag.getTypeElement()); // Find the index of the target element

		if (index != -1 && index > 0) {  // Check if element exists and isn't already first
			Object elementToMove = typeElements.remove(index);  // Remove the element
			typeElements.add(0, (elmPadag).getTypeElement());                // Add it to the beginning
		}

		model.addAttribute("listTypeElements", typeElements);
		model.addAttribute("listTypeElements", typeElements);


		//récuperer les niveaux


		List<Niveau> niveaux = niveauService.getAllNivaux();
		// Assuming your collection is called "myList"
		index = niveaux.indexOf(elmPadag.getNiveau()); // Find the index of the target element

		if (index != -1 && index > 0) {  // Check if element exists and isn't already first
			Object elementToMove = niveaux.remove(index);  // Remove the element
			niveaux.add(0, (elmPadag).getNiveau());                // Add it to the beginning
		}

		model.addAttribute("listNiveau", niveaux);


		//récuperer les enseignants


		List<Enseignant> enseignants = enseignantService.getAllEnseignants();
		index = enseignants.indexOf(elmPadag.getEnseignant()); // Find the index of the target element

		if (index != -1 && index > 0) {  // Check if element exists and isn't already first
			Object elementToMove = enseignants.remove(index);  // Remove the element
			enseignants.add(0, (elmPadag).getEnseignant());                // Add it to the beginning
		}

		model.addAttribute("listEnseignant", enseignants);


		//récuperer les coordinateurs

		List<Enseignant> coordinateurs = enseignantService.getAllEnseignants();

		index = coordinateurs.indexOf(elmPadag.getCoordinateur()); // Find the index of the target element

		if (index != -1 && index > 0) {  // Check if element exists and isn't already first
			Object elementToMove = coordinateurs.remove(index);  // Remove the element
			coordinateurs.add(0, (elmPadag).getCoordinateur());                // Add it to the beginning
		}

		model.addAttribute("listCoordinateur", coordinateurs);


	}




	@GetMapping("elsPedg")
	public List<ElemPedagDToProvider> getElementPedagogique() {
		List<ElementPedagogique> elementPedagogique = elementPedagService.getAllElementPedagogiques();
		return elementPedagogique.stream().map(ElemPedagDToProvider::new).collect(Collectors.toList()); // return 200 OK with JSON


	}




	@GetMapping("initialData")
	public ElementPedagInitialData getInitialData() {
		ElementPedagInitialData initialData = new ElementPedagInitialData();

		//recuperer les enseignants
		List<Enseignant> enseignants = enseignantService.getAllEnseignants();
		initialData.setEnseignantsList(enseignants);

		//recuperer les niveau
		List<Niveau> niveaux = niveauService.getAllNivaux();
		initialData.setNiveauList(niveaux);

		//recuperer les Types d'element
		List<TypeElement> typeElements = typeElementService.getAllTypeElements();
		initialData.setTypeElementList(typeElements);

		return initialData;

	}




	@PostMapping("addElmPedag")
	public String addElmPedag(@Valid @RequestBody ElemPedagDToReceiver epReceiver) {

		ElementPedagogique elementPedagogique = new ElementPedagogique();

		// Ajouter les donnees necassaires

		//Titre
		elementPedagogique.setTitre(epReceiver.getTitre());

		//Enseignant
		elementPedagogique.setEnseignant(enseignantService.getEnseignantById(epReceiver.getIdEnseignant()));

		//Cordinator
		elementPedagogique.setCoordinateur(enseignantService.getEnseignantById(epReceiver.getIdCordinateur()));

		//TypeElement

		elementPedagogique.setTypeElement(typeElementService.getTypeElementById(epReceiver.getIdTypeElm()));

		//Niveau
		elementPedagogique.setNiveau(niveauService.getNiveauById(epReceiver.getIdNiveau()));

		System.out.println(elementPedagogique);
		//Enregistrer l'element pedagogique

		elementPedagService.addElementPedagogique(elementPedagogique);


		return epReceiver.getTitre() + " is successfully added ";

	}




	// Update personnel
	@PostMapping("update/{elmPedagId}")
	public ResponseEntity<String> updatePersonnel(@PathVariable("elmPedagId") long elmPedagId,
												  @Valid @RequestBody ElemPedagDToReceiver epReceiver) {
		System.out.println("Hello in update function =============================");
		ElementPedagogique ep = elementPedagService.getElementPedagogiqueById(Long.valueOf(elmPedagId));



		if (ep == null) {
			return new ResponseEntity<>("Educational Element not found", HttpStatus.NOT_FOUND); // 404 Not Found
		}

		BeanUtils.copyProperties(epReceiver,ep);
		ep.setNiveau(niveauService.getNiveauById(epReceiver.getIdNiveau()));
		ep.setCoordinateur(enseignantService.getEnseignantById(epReceiver.getIdCordinateur()));
		ep.setEnseignant(enseignantService.getEnseignantById(epReceiver.getIdEnseignant()));
		ep.setTypeElement(typeElementService.getTypeElementById(epReceiver.getIdTypeElm()));

		elementPedagService.updateElementPedagogique(ep);


		return new ResponseEntity<>("Educational Element "+epReceiver.getTitre()+" is updated successfully", HttpStatus.OK); // 200 OK
	}




	@DeleteMapping("/delete/{idElmPedg}")
	public ResponseEntity<String> deleteElemPedg(@PathVariable("idElmPedg") long idElmPedg) {

		elementPedagService.deleteElementPedagogique(Long.valueOf(idElmPedg));
		System.out.println("deleted successfully");

		return new ResponseEntity<>("the educational element is deleted successfully.", HttpStatus.OK); // 204 No Content
	}


}
