package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.NiveauEnfantDto;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.NiveauEnfantMapper;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class NiveauEnfantService {
    private final NiveauEnfantRepository niveauEnfantRepository;
    private final NiveauEnfantMapper niveauEnfantMapper;


    public ResponseEntity<Object> saveNiveauEnfant(NiveauEnfantDto niveauEnfantDto){
        NiveauEnfant niveauEnfant = niveauEnfantMapper.toEntity(niveauEnfantDto);
        niveauEnfant = niveauEnfantRepository.save(niveauEnfant);

        return HttpResponseHandler.generateResponse(
                true,
                "Le niveau a été créé avec succès",
                HttpStatus.CREATED,niveauEnfant,
                "/api/niveau-enfants"
        );
    }

    public ResponseEntity<Object>  getAll(){

        List<NiveauEnfant> niveaux = niveauEnfantRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "List des niveaux",
                HttpStatus.OK,
                niveaux,
                "api/niveaux"
        );
    }

    public ResponseEntity<Object>  findNiveauEnfantByCode(String code){
        NiveauEnfant niveau = niveauEnfantRepository.findById(code)
                .orElse(null);

        if(niveau != null){
            return HttpResponseHandler.generateResponse(
                    true,
                    "Le niveau a été récupéré",
                    HttpStatus.OK,niveau,
                    "/api/niveaux/"+code
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le niveau n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/niveaux/"+code
            );
        }
    }

    public ResponseEntity<Object> updateNiveauEnfant(String code, NiveauEnfantDto niveauEnfantDetails){
        NiveauEnfant niveauEnfant = niveauEnfantRepository.findById(code)
                .orElse(null);
        if(niveauEnfant != null){
            niveauEnfant = niveauEnfantMapper.toEntity(niveauEnfantDetails);
            niveauEnfantRepository.save(niveauEnfant);
            return HttpResponseHandler.generateResponse(
                    false,
                    "Niveau modifié avec succès",
                    HttpStatus.OK,niveauEnfant,
                    "/api/niveau-enfants"
            );
        }

        return HttpResponseHandler.generateResponse(
                false,
                "Le niveau avec ce code n'existe pas",
                HttpStatus.NOT_FOUND,null,
                "/api/niveau-enfants/"+code
        );
    }

    public ResponseEntity<Object>  deleteNiveauEnfant(String code){
        NiveauEnfant niveauEnfant = niveauEnfantRepository.findById(code)
                .orElse(null);

        if(niveauEnfant != null){
            niveauEnfantRepository.delete(niveauEnfant);
            return HttpResponseHandler.generateResponse(
                    true,
                    "Le niveau a été supprimé avec succès",
                    HttpStatus.OK,null,
                    "/apie niveau-enfants/"+code
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le niveau n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/niveau-enfants/"+code
            );
        }
    }


}
