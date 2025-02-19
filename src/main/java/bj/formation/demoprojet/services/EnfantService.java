package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.EnfantDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.EnfantMapper;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.EnfantRepository;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import bj.formation.demoprojet.requests.EnfantRequest;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnfantService {
    private final EnfantRepository enfantRepository;
    private final AgentRepository agentRepository;
    private final NiveauEnfantRepository niveauEnfantRepository;
    private  final EnfantMapper enfantMapper;

    public ResponseEntity<Object> saveEnfant(EnfantRequest resquest){
        /*Agent oldAgent = agentRepository.findById(resquest.getAgent().getMatricule())
                        .orElse(null);
        NiveauEnfant niveauEnfant = niveauEnfantRepository.findById()
                .orElse(null);
        if(niveauEnfant != null){

            if(oldAgent != null){*/
                Enfant enfant = enfantMapper.deDto(resquest);
                enfantRepository.save(enfant);
                return HttpResponseHandler.generateResponse(
                        true,
                        "Enfant enregistré",
                        HttpStatus.OK,enfant,
                        "/api/enfants"
                );
            /*}else {
                return HttpResponseHandler.generateResponse(
                        false,
                        "L'agent fourni est introuvable",
                        HttpStatus.NOT_FOUND,null,
                        "/api/enfants"
                );
            }
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le niveau fourni est introuvable",
                    HttpStatus.NOT_FOUND,null,
                    "/api/enfants"
            );
        }*/
    }

    public ResponseEntity<Object> getAllEnfants(){
        List<Enfant> enfants = enfantRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "Liste des enfants",
                HttpStatus.OK,enfants,
                "/api/enfants"
        );
    }

    public Enfant findEnfantById(Long id){
        Enfant enf = enfantRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Enfnat introuvable avec l'id "+id));
        return enf;
    }

    public Enfant updateEnfant(Long id, Enfant enfantDetails){
        Enfant enf = enfantRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Enfnat introuvable avec l'id "+id));
        enf.setNom(enfantDetails.getNom());
        enf.setPrenom(enfantDetails.getPrenom());
        enf.setDateNaissance(enfantDetails.getDateNaissance());
        enf.setNiveauEnfant(enfantDetails.getNiveauEnfant());
        enf.setAgent(enfantDetails.getAgent());

        return enfantRepository.save(enf);
    }

    public void deleteEnfant(Long id){
        Enfant enf = enfantRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Enfnat introuvable avec l'id "+id));
        enfantRepository.delete(enf);
    }
}
