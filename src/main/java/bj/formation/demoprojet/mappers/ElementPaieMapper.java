package bj.formation.demoprojet.mappers;


import bj.formation.demoprojet.dtos.ElementPaieRequestDto;
import bj.formation.demoprojet.entities.ElementPaie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ElementPaieMapper {
    public ElementPaie toEntity(ElementPaieRequestDto elementPaieRequestDto){
        ElementPaie elementPaie = new ElementPaie();
        BeanUtils.copyProperties(elementPaieRequestDto, elementPaie);
        return elementPaie;
    }

    public  ElementPaieRequestDto toDto(ElementPaie elementPaie){
        ElementPaieRequestDto elementPaieRequestDto = new ElementPaieRequestDto();
        BeanUtils.copyProperties(elementPaie, elementPaieRequestDto);
        return elementPaieRequestDto;
    }
}
