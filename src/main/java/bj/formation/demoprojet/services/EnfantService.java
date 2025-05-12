package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.AgentEnfantDto;
import bj.formation.demoprojet.dtos.EnfantDto;
import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.EnfantMapper;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.EnfantRepository;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnfantService {
    private final EnfantRepository enfantRepository;
    private final AgentRepository agentRepository;
    private final NiveauEnfantRepository niveauEnfantRepository;
    private  final EnfantMapper enfantMapper;

    public ResponseEntity<Object> saveEnfant(EnfantRequestDto request){
        Enfant enfant = enfantMapper.toEntity(request);
        enfantRepository.saveAndFlush(enfant);

        return HttpResponseHandler.generateResponse(
                true,
                "Enfant enregistré",
                HttpStatus.OK,enfant,
                null,
                "/api/enfants"
        );
    }


    public ResponseEntity<Object> addChildrenToAgent(AgentEnfantDto requestDto){
        List<Enfant> enfants = new ArrayList<>();
        for(EnfantDto enfantDto : requestDto.getEnfantDtoList()){
            EnfantRequestDto enfantRequestDto = new EnfantRequestDto();
            BeanUtils.copyProperties(enfantDto, enfantRequestDto);
            enfantRequestDto.setAgentMatricule(requestDto.getAgentMatricule());

            Enfant enfant = enfantMapper.toEntity(enfantRequestDto);
            enfantRepository.saveAndFlush(enfant);
            enfants.add(enfant);
        }
        return HttpResponseHandler.generateResponse(
                true,
                "Enfant enregistré",
                HttpStatus.OK,enfants,
                null,
                "/api/enfants"
        );
    }

    public ResponseEntity<Object> getAllEnfants(){
        List<Enfant> enfants = enfantRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "Liste des enfants",
                HttpStatus.OK,enfants,
                null,
                "/api/enfants"
        );
    }

    public ResponseEntity<Object> findEnfantById(Long id){
        Enfant enf = enfantRepository.findById(id)
                .orElse(null);
        if(enf!=null){
            return HttpResponseHandler.generateResponse(
                    true,
                    "Enfant récupéré",
                    HttpStatus.OK,enf,
                    null,"/api/enfants/"+id
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Enfant introuvable",
                    HttpStatus.NOT_FOUND,null,
                    null,"/api/enfants/"+id
            );
        }
    }

    public ResponseEntity<Object> updateEnfant(Long id, EnfantRequestDto enfantDetails){
        Enfant enfantExist = enfantRepository.findById(id)
                .orElse(null);

        if(enfantExist == null){
            return HttpResponseHandler.generateResponse(
                    false,
                    "Enfant introuvable",
                    HttpStatus.NOT_FOUND,null,
                    null,"/api/enfants/"+id
            );
        }

        Agent agentExist = (enfantDetails.getAgentMatricule() != null) ? agentRepository.findById(enfantDetails.getAgentMatricule())
                .orElse(null):enfantExist.getAgent();

        NiveauEnfant niveauEnfantExist = (enfantDetails.getCode_niveau_enfant() != null) ? niveauEnfantRepository.findById(enfantDetails.getCode_niveau_enfant())
                .orElse(null): enfantExist.getNiveauEnfant();

        if (enfantDetails.getNom() != null) enfantExist.setNom(enfantDetails.getNom());
        if (enfantDetails.getPrenom() != null) enfantExist.setPrenom(enfantDetails.getPrenom());
        if (enfantDetails.getDateNaissance() != null) enfantExist.setDateNaissance(enfantDetails.getDateNaissance());

        enfantExist.setNiveauEnfant(niveauEnfantExist);
        enfantExist.setAgent(agentExist);

        enfantRepository.saveAndFlush(enfantExist);

        return HttpResponseHandler.generateResponse(
                true,
                "Enfant modifié avec succès",
                HttpStatus.OK,enfantExist,
                null,"/api/enfants/"+id
        );
    }

    public ResponseEntity<Object> deleteEnfant(Long id){

        Enfant enfantExist = enfantRepository.findById(id)
                .orElse(null);

        if(enfantExist == null){
            return HttpResponseHandler.generateResponse(
                    false,
                    "Enfant introuvable",
                    HttpStatus.NOT_FOUND,null,
                    null,"/api/enfants/"+id
            );
        }
        enfantRepository.delete(enfantExist);
        return HttpResponseHandler.generateResponse(
                true,
                "Enfant bien supprimé",
                HttpStatus.OK,null,
                null,"/api/enfants/"+id
        );
    }
}
