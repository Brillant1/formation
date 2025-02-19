package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.GradeMapper;
import bj.formation.demoprojet.repositories.GradeRepository;
import bj.formation.demoprojet.requests.GradeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                    false,
                    "Grade créé avec succès",
                    HttpStatus.CREATED,grade,
                    "/api/grades"
            );
        } else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le grade avec ce code existe déjà",
                    HttpStatus.MULTI_STATUS,null,
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
                grades,
                "api/grades"
        );
    }

    public ResponseEntity<Object> updateGrade(String code, GradeDto gradeDetails){
        Grade grade = gradeRepository.findById(code)
                .orElse(null);
        if(grade != null){
            grade = gradeMapper.toGrade(gradeDetails);
            gradeRepository.save(grade);
            return HttpResponseHandler.generateResponse(
                    false,
                    "Grade modifié avec succès",
                    HttpStatus.OK,grade,
                    "/api/grades"
            );
        }

        return HttpResponseHandler.generateResponse(
                false,
                "Le grade avec ce code n'existe pas",
                HttpStatus.NOT_FOUND,null,
                "/api/grades/"+code
        );

    }

    public ResponseEntity<Object> findGradeByCode(String code){
        Grade grade = gradeRepository.findById(code)
                .orElse(null);

        if(grade != null){
            return HttpResponseHandler.generateResponse(
                    true,
                    "Le grade a été récupéré",
                    HttpStatus.OK,grade,
                    "/api/grades/"+code
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/grades/"+code
            );
        }
    }

    public  ResponseEntity<Object> deleteGrade(String code){
        Grade grade = gradeRepository.findById(code)
                .orElse(null);

        if(grade != null){
            gradeRepository.delete(grade);
            return HttpResponseHandler.generateResponse(
                    true,
                    "Le grade a été supprimé avec succès",
                    HttpStatus.OK,null,
                    "/apie grades/"+code
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le grade n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/grades/"+code
            );
        }
    }



}
