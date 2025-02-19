package bj.formation.demoprojet.mappers;

import bj.formation.demoprojet.entities.Agent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import bj.formation.demoprojet.dtos.EnfantDto;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.requests.EnfantRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EnfantMapper {

    private final AgentMapper agentMapper;
    private final NiveauEnfantMapper niveauEnfantMapper;

    public Enfant deDto(EnfantRequest dto) {
        Enfant entite = new Enfant();
        BeanUtils.copyProperties(dto, entite);
        entite.setNiveauEnfant(niveauEnfantMapper.toEntity(dto.getNiveauEnfant()));
        entite.setAgent(agentMapper.fromDtoToAgent(dto.getAgent()));
        return entite;
    }
}
