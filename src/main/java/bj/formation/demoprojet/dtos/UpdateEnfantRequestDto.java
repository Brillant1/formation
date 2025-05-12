package bj.formation.demoprojet.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEnfantRequestDto {

    @NotNull
    Long id;

    String nom;

    String prenom;

    @Past(message = "La date de naissance doit être valide")
    LocalDate dateNaissance;

    @NotEmpty(message = "Veillez renseigner le niveau de l'enfant")
    String code_niveau_enfant;

    @NotEmpty(message = "Veillez renseigner l'agent à qui appartient l'enfant")
    String agentMatricule;
}
