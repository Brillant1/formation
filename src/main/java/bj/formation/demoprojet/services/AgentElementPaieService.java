package bj.formation.demoprojet.services;

import bj.formation.demoprojet.dtos.AgentElementPaieDto;
import bj.formation.demoprojet.dtos.AgentElementPaieRequestDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentElementPaie;
import bj.formation.demoprojet.entities.ElementPaie;
import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.repositories.AgentElementPaieRepository;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.ElementPaieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentElementPaieService {
    private final AgentElementPaieRepository agentElementPaieRepository;
    private final AgentRepository agentRepository;
    private final ElementPaieRepository elementPaieRepository;

    public ResponseEntity<Object> save(AgentElementPaieRequestDto request){
        AgentElementPaie agentElementPaie= new AgentElementPaie();

        for(AgentElementPaieDto elementPaieDto : request.getElementPaieList() ){
            if(agentElementPaieRepository.findByAgent_MatriculeAndElementPaie_Code(request.getAgentMatricule(), elementPaieDto.getElementPaieCode()) != null){
                ElementPaie elementPaieExist = elementPaieRepository.findById(elementPaieDto.getElementPaieCode()).orElse(null);
                Agent agentExist = agentRepository.findById(request.getAgentMatricule()).orElse(null);
                agentElementPaie.setElementPaie(elementPaieExist);
                agentElementPaie.setAgent(agentExist);
                agentElementPaie.setMontant(elementPaieDto.getMontant());
                agentElementPaie.setDateDebut(LocalDate.now());
                agentElementPaieRepository.saveAndFlush(agentElementPaie);
            }
        }

        return HttpResponseHandler.generateResponse(
                true,
                "Accessoire ajouté à l'agent",
                HttpStatus.OK,agentElementPaie,
                null,
                "/api/agent-element-paies"
        );
    }

    public ResponseEntity<Object> getAll(){

        List<AgentElementPaie> agentElementPaies = agentElementPaieRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "Liste des accessoires de l'agent",
                HttpStatus.OK,agentElementPaies,
                null,
                "/api/agent-element-paies"
        );
    }
}
