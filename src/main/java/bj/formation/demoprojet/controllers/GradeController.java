package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.requests.GradeRequest;
import bj.formation.demoprojet.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GradeDto request){
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
    public ResponseEntity<Object> updateGrade(@PathVariable String code, @RequestBody GradeDto gradeDetails) {
        return gradeService.updateGrade(code, gradeDetails);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteGrade(@PathVariable String code){
        return gradeService.deleteGrade(code);
    }
}
