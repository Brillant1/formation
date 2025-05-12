package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.AgentWithMatriculeNotFound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentElementPaieRequestDto {

    @NotEmpty(message = "La liste des éléments ne peut pas être vide")
    @Valid
    List<AgentElementPaieDto> elementPaieList;

    @NotEmpty(message = "Le matricule de l'agent est obligatoire")
    @AgentWithMatriculeNotFound
    String agentMatricule;
}
