package bj.formation.demoprojet.mappers;

import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.dtos.EnfantResponseDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.NiveauEnfant;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final NiveauEnfantRepository niveauEnfantRepository;
    private final AgentRepository agentRepository;

    public Enfant toEntity(EnfantRequestDto dto) {
        Enfant enfant = new Enfant();
        Agent agent =  agentRepository.findById(dto.getAgentMatricule())
                .orElseThrow(()-> new EntityNotFoundException("L'agent avec le matricule "+dto.getAgentMatricule() + " n'existe pas"));

        NiveauEnfant niveauEnfant = niveauEnfantRepository.findById(dto.getCode_niveau_enfant())
                .orElseThrow(()-> new EntityNotFoundException("Le niveau avec le code "+dto.getCode_niveau_enfant() + " n'existe pas"));

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
