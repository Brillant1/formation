package bj.formation.demoprojet.controllers;


import bj.formation.demoprojet.dtos.AgentElementPaieRequestDto;
import bj.formation.demoprojet.services.AgentElementPaieService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/agent-element-paies")
@RequiredArgsConstructor
public class AgentElementPaieController {
    private final AgentElementPaieService agentElementPaieService;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody AgentElementPaieRequestDto request){
        return  agentElementPaieService.save(request);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return agentElementPaieService.getAll();
    }
}
