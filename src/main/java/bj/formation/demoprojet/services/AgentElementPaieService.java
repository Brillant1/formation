package bj.formation.demoprojet.services;

import bj.formation.demoprojet.entities.AgentElementPaie;
import bj.formation.demoprojet.repositories.AgentElementPaieRepository;

import java.util.List;

public class AgentElementPaieService {
    AgentElementPaieRepository agentElementPaieRepository;

    public AgentElementPaieService(AgentElementPaieRepository agentElementPaieRepository) {
        this.agentElementPaieRepository = agentElementPaieRepository;
    }

    public AgentElementPaie save(AgentElementPaie agentElementPaie){
        return  agentElementPaieRepository.save(agentElementPaie);
    }

    public List<AgentElementPaie> getAll(){
        return agentElementPaieRepository.findAll();
    }
}
