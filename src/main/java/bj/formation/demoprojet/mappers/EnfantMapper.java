package bj.formation.demoprojet.mappers;

import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.dtos.EnfantResponseDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
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
    private NiveauEnfantRepository niveauEnfantRepository;

    public Enfant toEntity(EnfantRequestDto dto, NiveauEnfant niveauEnfant, Agent agent) {
        Enfant enfant = new Enfant();
        BeanUtils.copyProperties(dto, enfant);
        enfant.setNiveauEnfant(niveauEnfant);
        enfant.setAgent(agent);

        return enfant;
    }

    public EnfantResponseDto toDto(EnfantResponseDto entity){
        EnfantResponseDto enfantResponseDto = new EnfantResponseDto();
        BeanUtils.copyProperties(entity, enfantResponseDto);
        enfantResponseDto.setNiveauEnfant(entity.getNiveauEnfant());
        enfantResponseDto.setAgent(entity.getAgent());
        return enfantResponseDto;
    }


}
