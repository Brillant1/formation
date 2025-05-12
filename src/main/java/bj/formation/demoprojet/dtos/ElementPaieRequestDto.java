package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.UniqueElementPaieCode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementPaieRequestDto {

    @NotEmpty(message = "Le code est obligatoire")
    @Size(min = 2, message = "Minimum trois caractères pour le nom")
    @UniqueElementPaieCode
    String code;

    @NotEmpty(message = "Le libelle est obligatoire")
    String libelle;
}
