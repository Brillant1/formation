package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.ElementPaieRequestDto;
import bj.formation.demoprojet.entities.ElementPaie;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.ElementPaieMapper;
import bj.formation.demoprojet.repositories.ElementPaieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementPaieService {
    private  final ElementPaieRepository elementPaieRepository;
    private final ElementPaieMapper elementPaieMapper;
    private final HttpServletRequest requestUrl;

    public ResponseEntity<Object> saveElementPaie(ElementPaieRequestDto request) {

        ElementPaie elementPaie = elementPaieMapper.toEntity(request);
        elementPaieRepository.saveAndFlush(elementPaie);

        return HttpResponseHandler.generateResponse(
                true,
                "L'élement a été créé avec succès",
                HttpStatus.CREATED,elementPaie, null,
                requestUrl.getRequestURI()

        );
    }


    public ResponseEntity<Object> getAll(){
        List<ElementPaie> elementPaies = elementPaieRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "Liste des élément de paie",
                HttpStatus.OK,elementPaies, null,
                requestUrl.getRequestURI()
        );
    }

    public ResponseEntity<Object> findElementPaieById(String code){
        ElementPaie elementPaie = elementPaieRepository.findById(code)
                .orElseThrow( () -> new EntityNotFoundException("L'élément paie avec le code "+code+" n'existe pas"));


        return HttpResponseHandler.generateResponse(
                true,
                "L'element paie a été récupéré",
                HttpStatus.OK,elementPaie, null,
                requestUrl.getRequestURI()
        );
    }

    public ResponseEntity<Object> updateElementPaie(String code, ElementPaieRequestDto elementPaieRequestDto){

            ElementPaie elementPaieExist = elementPaieRepository.findById(code)
                    .orElseThrow( () -> new EntityNotFoundException("L'élément paie avec le code "+code+" n'existe pas"));

            if( elementPaieRequestDto.getLibelle() != null ) elementPaieExist.setLibelle(elementPaieRequestDto.getLibelle());
            elementPaieRepository.saveAndFlush(elementPaieExist);
            return HttpResponseHandler.generateResponse(
                    true,
                    "L'element paie a été modifié avec succès",
                    HttpStatus.OK,elementPaieExist, null,
                    "/api/element-paies/"+code
            );
    }

    public ResponseEntity<Object> deleteElementPaie(String code){

        ElementPaie elementPaieExist = elementPaieRepository.findById(code)
                .orElseThrow( () -> new EntityNotFoundException("L'élément paie avec le code "+code+" n'existe pas"));

        elementPaieRepository.delete(elementPaieExist);
        return HttpResponseHandler.generateResponse(
                true,
                "L'element paie a été supprimé avec succès",
                HttpStatus.OK,null, null,
                requestUrl.getRequestURI()
        );
    }
}
