package com.exam.core.data;
import com.exam.core.bo.*;
import com.exam.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IFiliereRepositoryDao filiereRepository;

    @Autowired
    private INiveauRepositoryDao niveauRepository;

    @Autowired
    private ISalleRepositoryDao salleRepository;

    @Autowired
    private ISemestreRepositoryDao semestreRepository;

    @Autowired
    private IDepartementRepositoryDao departementRepositoryDao;

    @Autowired
    private ISessionRepositoryDao sessionRepository;

    @Autowired
    private ITypeElementRepositoryDao typeElementRepository;

    @Autowired
    private ITypeExamRepositoryDao typeExamRepository;

    @Override
    public void run(String... args) throws Exception {

        if (filiereRepository.findAll().size()==0){
            // Insert Filiere data
            filiereRepository.saveAll(Arrays.asList(
                    new Filiere(1L, "AP"),
                    new Filiere(2L, "GI"),
                    new Filiere(3L, "GC"),
                    new Filiere(4L, "ID"),
                    new Filiere(5L, "GM")
            ));

            // Insert Niveau data
            niveauRepository.saveAll(Arrays.asList(
                    new Niveau(1L, "CP1"),
                    new Niveau(2L, "CP2"),
                    new Niveau(3L, "GI1"),
                    new Niveau(4L, "GI2"),
                    new Niveau(5L, "GI3"),
                    new Niveau(6L, "ID1"),
                    new Niveau(7L, "ID2")
            ));

            // Insert Salle data
            salleRepository.saveAll(Arrays.asList(
                    new Salle(1L, "Classroom3", 50),
                    new Salle(2L, "Classroom4", 45),
                    new Salle(3L, "Classroom5", 30),
                    new Salle(4L, "Amphi A", 120),
                    new Salle(5L, "Amphi B", 200),
                    new Salle(6L, "Classroom8", 35),
                    new Salle(7L, "Classroom9", 60)
            ));

            // Insert Semestre data
            semestreRepository.saveAll(Arrays.asList(
                    new Semestre(1L, "printemps"),
                    new Semestre(2L, "automne")
            ));
            // Insert Semestre data
            semestreRepository.saveAll(Arrays.asList(
                    new Semestre(1L, "printemps"),
                    new Semestre(2L, "automne")
            ));

            // Insert Session data
            sessionRepository.saveAll(Arrays.asList(
                    new Session(1L, "rattrapage"),
                    new Session(2L, "normal")
            ));

            // Insert TypeElement data
            typeElementRepository.saveAll(Arrays.asList(
                    new TypeElement(1L, "module"),
                    new TypeElement(2L, "element")
            ));
            // Insert TypeExam data
            typeExamRepository.saveAll(Arrays.asList(
                    new TypeExam(1L, "DS 1"),
                    new TypeExam(2L, "DS 2")
            ));

            // Insert Department data
            departementRepositoryDao.saveAll(Arrays.asList(
                    new Departement(1L, "math"),
                    new Departement(2L, "info")
            ));

            System.out.println("Data inserted successfully!");
        }

    }




}
