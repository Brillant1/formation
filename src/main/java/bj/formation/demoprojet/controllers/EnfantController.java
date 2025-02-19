package bj.formation.demoprojet.controllers;


import bj.formation.demoprojet.dtos.EnfantDto;
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
    public ResponseEntity<Object> saveEnfant(EnfantRequest request){
        return enfantService.saveEnfant(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllEnfants(){
        return enfantService.getAllEnfants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfant> getEnfantById(@PathVariable Long id){
        Enfant enf = enfantService.findEnfantById(id);
        return ResponseEntity.ok(enf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable Long id, @RequestBody Enfant enfantDetails){
        Enfant enf = enfantService.updateEnfant(id, enfantDetails);
        return ResponseEntity.ok(enf);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnfant(Long id){
        try {
            enfantService.deleteEnfant(id);
            return ResponseEntity.ok("Enfant supprimé avec succès");
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}
