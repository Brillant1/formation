package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.ElementPaieWithCodeNotFound;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class AgentElementPaieDto {
    @NotNull(message = "Le montant est obligatoire")
    @Min(value = 0, message = "Le montant doit être un entier positif")
    Integer montant;

    @NotEmpty(message = "Le code de l'element de paie est obligatoire")
    @ElementPaieWithCodeNotFound
    String elementPaieCode;
}
