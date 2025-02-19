package bj.formation.demoprojet.services;

import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.Generation;
import bj.formation.demoprojet.repositories.GenerationRepository;

import java.util.List;

public class GenerationService {
    GenerationRepository generationRepository;

    public GenerationService(GenerationRepository generationRepository) {
        this.generationRepository = generationRepository;
    }

    public Generation save(Generation generation){
        return  generationRepository.save(generation);
    }

    public List<Generation> getAll(){
        return generationRepository.findAll();
    }
}
