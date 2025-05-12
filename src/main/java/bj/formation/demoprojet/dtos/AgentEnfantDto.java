package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.AgentMatriculeAlreadyExist;
import bj.formation.demoprojet.annotations.AgentWithMatriculeNotFound;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentEnfantDto {
    @NotEmpty(message = "Le matricule de l'agent est obligatoire")
    @NotNull(message = "Le matricule de l'agent est obligatoire")
    @AgentWithMatriculeNotFound
    String agentMatricule;


    @NotEmpty(message = "La liste des enfants ne peut pas être vide")
    @Valid
    List<EnfantDto> enfantDtoList;
}
