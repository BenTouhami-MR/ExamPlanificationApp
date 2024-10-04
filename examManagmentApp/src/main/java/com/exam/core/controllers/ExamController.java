package com.exam.core.controllers;

import com.exam.core.bo.*;
import com.exam.core.services.*;
import com.exam.core.services.*;
import com.exam.core.web.models.ExamInitialData;
import com.exam.core.web.models.ExamModelReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.exam.core.web.models.ExamModel;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/admin/exam")
@CrossOrigin(origins="http://localhost:3000")
public class ExamController {


	@Autowired
	private ISessionService sessionService;

	@Autowired
	private ISemestreService semestreService;

	@Autowired
	private ISalleService salleService;

	@Autowired
	private IElementPedagogiqueService elmPeadagSerivce;

	@Autowired
	private  ITypeExamService typeExamService;

	@Autowired
	private  IEnseignantService enseignantService;

	@Autowired
	private ISurveillanceService surveillanceService ;

	@Autowired
	private IExamService examService;




	@GetMapping("initialData")
	public ExamInitialData getexamInitalData(){
		List<Salle> listSalles = salleService.getAllSalles();
		List<ElementPedagogique> eps  = elmPeadagSerivce.getAllElementPedagogiques();
		List<Enseignant> es = enseignantService.getAllEnseignants();
		List<Session> ss= sessionService.getAllSessions();
		List<Semestre> sms = semestreService.getAllSemestres();
		List<TypeExam> tes = typeExamService.getAllTypeExams();

		return new ExamInitialData(listSalles,eps,es,ss,sms,tes);

	}

	@PostMapping("create")
	public String createExam(@RequestBody ExamModelReceiver exm){
		examService.addExam(exm);

		return "Exam is Successfully Scheduled";

	}

	@RequestMapping("showForm")
	public String showForm(Model model) {

//		insererDonneesNecessaire(model);

		ExamModel examModel = new ExamModel();

//		examModel.setSession(sessionService.getAllSessions().get(0));

		model.addAttribute("examModel",examModel);

		return "admin/exam/form";
	}





	@PostMapping("chooseEnsignants")
	public String ChooseEnseignants(@Valid @ModelAttribute("examModel") ExamModelReceiver examModel, BindingResult bindinResult, Model model) {

		if(bindinResult.hasErrors()) {

//			insererDonneesNecessaire(model);

			return "admin/exam/form";
		}


		examService.addExam(examModel);

//		System.out.println(examModel.getNbreSurvaillance());

		System.out.println(examModel.getSalles());

		return "redirect:/admin/exam/showForm";

	}






	@RequestMapping("manageExams")
	public String manageExam(Model model) {

		model.addAttribute("listExams",examService.getAllExams());

		return "admin/exam/listExam";

	}






	@RequestMapping("deleteExam/{idExam}")
	public String deleteExam(@PathVariable int idExam,Model model) {

		Exam exam = examService.getExamById(Long.valueOf(idExam));

		for (Surveillance s: exam.getSurveillances()) {

			surveillanceService.deleteSurveillance(s.getIdSurveil());
		}

		examService.deleteExam(Long.valueOf(idExam));



		return "redirect:/admin/exam/manageExams";
	}



	@RequestMapping("updateExam/{idExam}")
	public String updateExam(@PathVariable int idExam ,Model model){

		//TODO

		return null;

	}


	@RequestMapping("addExam")
	public String addExam(Model model) {

		return "admin/exam/form";

	}

}
