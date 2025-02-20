package bj.formation.demoprojet.controllers;


import bj.formation.demoprojet.dtos.EnfantDto;
import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.requests.EnfantRequest;
import bj.formation.demoprojet.services.EnfantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enfants")
@RequiredArgsConstructor
public class EnfantController {

    private final EnfantService enfantService;


    @PostMapping
    public ResponseEntity<Object> saveEnfant(@RequestBody EnfantRequestDto request){
        return enfantService.saveEnfant(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllEnfants(){
        return enfantService.getAllEnfants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnfantById(@PathVariable Long id){
        return enfantService.findEnfantById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEnfant(@PathVariable Long id, @RequestBody EnfantRequestDto enfantDetails){
        return enfantService.updateEnfant(id, enfantDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEnfant(@PathVariable Long id){
        return enfantService.deleteEnfant(id);
    }


}
