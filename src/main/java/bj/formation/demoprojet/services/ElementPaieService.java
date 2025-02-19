package bj.formation.demoprojet.services;

import bj.formation.demoprojet.entities.ElementPaie;
import bj.formation.demoprojet.repositories.ElementPaieRepository;

import java.util.List;

public class ElementPaieService {
    ElementPaieRepository elementPaieRepository;

    public ElementPaieService(ElementPaieRepository elementPaieRepository) {
        this.elementPaieRepository = elementPaieRepository;
    }

    public ElementPaie save(ElementPaie elementPaie){
        return  elementPaieRepository.save(elementPaie);
    }

    public List<ElementPaie> getAll(){
        return elementPaieRepository.findAll();
    }
}
