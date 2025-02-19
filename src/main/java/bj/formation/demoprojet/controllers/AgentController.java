package bj.formation.demoprojet.controllers;



import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.requests.AgentRequest;
import bj.formation.demoprojet.services.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agents")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AgentRequest request){
        return agentService.save(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllAgents() {
        return agentService.getAll();

    }

    @GetMapping("/{matricule}")
    public ResponseEntity<?> getAgentByMatricule(@PathVariable String matricule){
        return  agentService.findAgentByMatricule(matricule);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Object> updateAgent(@PathVariable String matricule, @RequestBody Agent agentDetails) {
        return agentService.updateAgent(matricule, agentDetails);
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Object> deleteAgent(@PathVariable String matricule){
        try{
            agentService.deleteAgent(matricule);
            return ResponseEntity.ok("Agent supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
