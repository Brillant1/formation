package bj.formation.demoprojet.controllers;


import bj.formation.demoprojet.dtos.NiveauEnfantDto;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.services.NiveauEnfantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/niveau-enfants")
@RequiredArgsConstructor
public class NiveauEnfantController {

    private final NiveauEnfantService niveauEnfantService;

    @PostMapping
    public ResponseEntity<Object> saveNiveauEnfant(@RequestBody NiveauEnfantDto niveauEnfantDto){
        return niveauEnfantService.saveNiveauEnfant(niveauEnfantDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllNiveauEnfant(){
        return niveauEnfantService.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getNiveauEnfantByCode(@PathVariable String code){
        return niveauEnfantService.findNiveauEnfantByCode(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateNiveauEnfnat(@PathVariable String code, @RequestBody NiveauEnfantDto niveauEnfant){
        return   niveauEnfantService.updateNiveauEnfant(code, niveauEnfant);
    }

    @DeleteMapping("/{code}")
    public  ResponseEntity<Object> deleteNiveauEnfant(@PathVariable String code){
        return niveauEnfantService.deleteNiveauEnfant(code);
    }

}
