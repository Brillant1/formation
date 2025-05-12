package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.requests.GradeRequest;
import bj.formation.demoprojet.services.GradeService;
import bj.formation.demoprojet.validators.OnCreate;
import bj.formation.demoprojet.validators.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/grades")
public class GradeController {
    private final GradeService gradeService;


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Validated({OnCreate.class, Default.class}) GradeDto request){
        return gradeService.saveGrade(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllGrades() {
        return gradeService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getGradeByCode(@PathVariable String code){
        return gradeService.findGradeByCode(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateGrade(@PathVariable String code, @RequestBody @Validated({OnUpdate.class, Default.class}) GradeDto gradeDetails) {
        return gradeService.updateGrade(code,gradeDetails);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteGrade(@PathVariable String code){
        return gradeService.deleteGrade(code);
    }
}
