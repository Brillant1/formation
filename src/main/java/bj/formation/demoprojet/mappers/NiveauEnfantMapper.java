package bj.formation.demoprojet.mappers;


import bj.formation.demoprojet.dtos.NiveauEnfantDto;
import bj.formation.demoprojet.entities.NiveauEnfant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NiveauEnfantMapper {
    public NiveauEnfant toEntity(NiveauEnfantDto niveauEnfantDto){
        NiveauEnfant niveauEnfant = new NiveauEnfant();
        BeanUtils.copyProperties(niveauEnfantDto, niveauEnfant);
        return niveauEnfant;
    }

    public NiveauEnfantDto toDto(NiveauEnfant niveauEnfant){
        NiveauEnfantDto niveauEnfantDto = new NiveauEnfantDto("", null);
        BeanUtils.copyProperties(niveauEnfant, niveauEnfantDto);
        return niveauEnfantDto;
    }
}
