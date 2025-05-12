package bj.formation.demoprojet.mappers;

import bj.formation.demoprojet.dtos.AgentElementPaieDto;
import bj.formation.demoprojet.dtos.AgentElementPaieRequestDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentElementPaie;
import bj.formation.demoprojet.entities.ElementPaie;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.ElementPaieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentElementPaieMapper {
    private final AgentRepository agentRepository;
    private final ElementPaieRepository elementPaieRepository;

    public AgentElementPaie toEntity(AgentElementPaieRequestDto request){
        AgentElementPaie agentElementPaie = new AgentElementPaie();
        BeanUtils.copyProperties(request, agentElementPaie);
        return  agentElementPaie;
    }
}
