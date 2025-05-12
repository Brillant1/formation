package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.dtos.UpdateGradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.GradeMapper;
import bj.formation.demoprojet.repositories.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public ResponseEntity<Object> saveGrade(GradeDto request){
        Grade oldGrade = gradeRepository.findById(request.code())
                .orElse(null);
        if(oldGrade == null){
            Grade grade = gradeMapper.toGrade(request);
            gradeRepository.save(grade);
            return HttpResponseHandler.generateResponse(
                    true,
                    "Grade créé avec succès",
                    HttpStatus.CREATED,grade,
                    null, "/api/grades"
            );
        } else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le grade avec le code "+request.code()+" existe déjà",
                    HttpStatus.MULTI_STATUS,null, null,
                    "/api/agents"
            );
        }
    }

    public ResponseEntity<Object> findAll(){
        List<Grade> grades = gradeRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "List des grades",
                HttpStatus.OK,
                grades, null,
                "api/grades"
        );
    }

    public ResponseEntity<Object> updateGrade(String code, GradeDto gradeDetails){
            Grade grade = gradeRepository.findById(code)
                    .orElseThrow(()->new EntityNotFoundException("Le grade avec le code "+code+ " n'existe pas"));
            grade.setCode(code);
            grade.setLibelle(gradeDetails.libelle());
            grade.setIndice(gradeDetails.indice());

            gradeRepository.save(grade);
            return HttpResponseHandler.generateResponse(
                    true,
                    "Grade modifié avec succès",
                    HttpStatus.OK,grade,
                    null, "/api/grades"
            );
    }

    public ResponseEntity<Object> findGradeByCode(String code){
        Grade grade = gradeRepository.findById(code)
                .orElseThrow(()->new EntityNotFoundException("Le grade avec le code "+code+ " n'existe pas"));

            return HttpResponseHandler.generateResponse(
                    true,
                    "Le grade a été récupéré",
                    HttpStatus.OK,grade,
                    null, "/api/grades/"+code
            );
    }

    public  ResponseEntity<Object> deleteGrade(String code){
        Grade grade = gradeRepository.findById(code)
                .orElseThrow(()->new EntityNotFoundException("Le grade avec le code "+code+ " n'existe pas"));

            gradeRepository.delete(grade);
            return HttpResponseHandler.generateResponse(
                    true,
                    "Le grade a été supprimé avec succès",
                    HttpStatus.OK,null,
                    null, "/api/grades/"+code
            );

    }



}
