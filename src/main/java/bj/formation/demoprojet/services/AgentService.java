package bj.formation.demoprojet.services;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentGrade;
import bj.formation.demoprojet.entities.Grade;

import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.AgentMapper;
import bj.formation.demoprojet.mappers.GradeMapper;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.GradeRepository;
import bj.formation.demoprojet.requests.AgentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentGradeService agentGradeService;
    private final GradeRepository gradeRepository;
    private final AgentMapper agentMapper;
    private final GradeMapper gradeMapper;



    public ResponseEntity<?> save(AgentRequest request) {
        if (request.getMatricule() != null && !request.getMatricule().isEmpty()){
            Agent oldAgent = agentRepository.findById(request.getMatricule())
                    .orElse(null);
            if (oldAgent == null) {
                Grade grade = gradeRepository.findById(request.getGrade().code())
                        .orElse(null);
                if (grade != null) {
                    Agent agent = agentMapper.toAgent(request);
                    agent = agentRepository.save(agent);
                    AgentGrade agentGrade = new AgentGrade();
                    agentGrade.setAgent(agent);
                    agentGrade.setGrade(gradeMapper.toGrade(request.getGrade()));
                    agentGrade.setDateDebut(LocalDate.now());
                    agentGradeService.save(agentGrade);
                    return HttpResponseHandler.generateResponse(
                            false,
                            "L'agent a été créé avec succès",
                            HttpStatus.CREATED,null,
                            "/api/agents"
                    );
                } else {
                    return HttpResponseHandler.generateResponse(
                            false,
                            "Le grade fourni est introuvable",
                            HttpStatus.NOT_FOUND,null,
                            "/api/agents"
                    );

                }
            } else {
                return HttpResponseHandler.generateResponse(
                        false,
                        "L'agent avec ce matricule existe déjà",
                        HttpStatus.MULTI_STATUS,null,
                        "/api/agents"
                );
            }
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le matricule est obligatoire",
                    HttpStatus.MULTI_STATUS,null,
                    "/api/agents"
            );
        }
    }

    public ResponseEntity<Object> getAll(){
        List<Agent> agents = agentRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents",
                HttpStatus.OK,
                agents,
                "api/agents"
        );
    }


    public ResponseEntity<Object> updateAgent(String matricule, Agent agentDetails){
        Agent agent = agentRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Agent not found with matricule " + matricule));
        agent.setNom(agentDetails.getNom());
        agent.setPrenom(agentDetails.getPrenom());
        agent.setIndice(agentDetails.getIndice());
        agent.setSalaireBase(agentDetails.getSalaireBase());
        agent.setAllocationFamiliale(agentDetails.getAllocationFamiliale());
        agent.setNbreEnfant(agentDetails.getNbreEnfant());
        agent.setActif(agentDetails.getActif());
        agentRepository.saveAndFlush(agent);
        return HttpResponseHandler.generateResponse(
                true,
                "Agent modifié avec succès",
                HttpStatus.OK,
                agent,
                "api/agents"
        );
    }

    public ResponseEntity<?> findAgentByMatricule(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElse(null);

        if(agent != null){
            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent a été récupéré",
                    HttpStatus.OK,agent,
                    "/api/agents/"+matricule
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/agents/"+matricule
            );
        }
    }

    public ResponseEntity<Object> deleteAgent(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElse(null);

        if(agent != null){
            agentRepository.delete(agent);
            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent supprimé avec succès",
                    HttpStatus.OK,null,
                    "/api/agents/"+matricule
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/agents/"+matricule
            );
        }
    }



}
