package com.exam.core.services.Imp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.exam.core.bo.Salle;
import com.exam.core.bo.Surveillance;
import com.exam.core.bo.TypeElement;
import com.exam.core.dao.*;
import com.exam.core.exceptions.SalleIndisponibleException;
import com.exam.core.services.IEnseignantService;
import com.exam.core.web.models.ExamModel;
import com.exam.core.web.models.ExamModelReceiver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.core.bo.Exam;
import com.exam.core.services.IExamService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class IExamServiceImp implements IExamService{



	@Autowired
	private IExamRepositoryDao examDao;

	@Autowired
	private IEnseignantRepositoryDao ensgnDao;


	@Autowired
	private ISalleRepositoryDao salleDao;

	@Autowired
	private ITypeExamRepositoryDao typeExmDao;

	@Autowired
	private IElementPedagogiqueRepositoryDao epDao;

	@Autowired
	private ISemestreRepositoryDao smDao;

	@Autowired
	private  ISessionRepositoryDao sesDao;






	public void addExam(ExamModelReceiver examModel) {


		if(examModel.getElementPedagogique()== ExamModel.TYPE_MODULE) {
			examModel.setDureePrevue(90);
			examModel.setDurreeReelle(90);
		}else {

			examModel.setDureePrevue(120);
			examModel.setDurreeReelle(120);
		}


//		recuperer les exams dans la date reserver
		List<Exam> exams =examDao.findExamByDate(examModel.getDate());

		for(Exam exam:exams) {
			LocalTime heureDebut = exam.getHeureDebut();
			Duration duration = Duration.ofMinutes(exam.getDurreeReelle());
			LocalTime heureFin = heureDebut.plus(duration);


			Set<Surveillance> surveillances = exam.getSurveillances();

			for (Surveillance surveillance : surveillances) {

				for (Long idSalle : examModel.getSalles()) {
					System.out.println("CurentheureDebut====> "+ examModel.getHeureDebut());
					System.out.println("idSalle : " + idSalle + " =>" + surveillance.getSalle().getIdSalle() +" | heureDebut.isBefore : "+heureDebut +"===> "+heureDebut.isBefore(examModel.getHeureDebut())+ " | heureFin.isAfter: "+heureFin+" =====> " +heureFin.isAfter(examModel.getHeureDebut() ));

					if (surveillance.getSalle().getIdSalle() == idSalle && (heureDebut.isBefore(examModel.getHeureDebut()) ||heureDebut.equals(examModel.getHeureDebut()) ) &&( heureFin.isAfter(examModel.getHeureDebut() )|| heureFin.equals(examModel.getHeureDebut() )))
						throw new SalleIndisponibleException("Classromm " + surveillance.getSalle().getNomSalle() + " is already reserved at this time between " + heureDebut +" and " + heureFin);

				}

			}


		}

		//		au cas ou tout les salles choisis sont disponible
		Exam exam =new Exam();

		BeanUtils.copyProperties(examModel,exam);

		exam.setTypeExam(typeExmDao.findById(examModel.getTypeExam()).get());
		exam.setElementPedagogique(epDao.findById(examModel.getElementPedagogique()).get());
		exam.setSemestre(smDao.findById(Long.valueOf(examModel.getSemestre())).get());
		exam.setSession(sesDao.findById(Long.valueOf(examModel.getSession())).get());

		for (Long salleId:examModel.getSalles()){
			Surveillance surveillance = new Surveillance();
			surveillance.setCoordonnateur(ensgnDao.findById(examModel.getCordonnateur()).get());
			surveillance.setSalle(salleDao.findById(salleId).get());

			surveillance.setNbreSurvaillance(examModel.getNbreSurveillants().get(examModel.getSalles().indexOf(salleId)));

			exam.addSurveillance(surveillance);
			System.out.println(examModel.getNbreSurveillants().get(examModel.getSalles().indexOf(salleId)));
		}



//		examDao.save(exam); //TODO

	}


	public void updateExam(Exam pExam) {

		examDao.save(pExam);

	}

	public void deleteExam(Long id) {

		examDao.deleteById(id);
	}


	public List<Exam> getAllExams(){


		return examDao.findAll();

	}


	public Exam getExamById(Long id) {

		return examDao.findById(id).get();
	}

	public List<Exam> getExamByDate(LocalDate date){

		return examDao.findExamByDate(date);
	}


}
