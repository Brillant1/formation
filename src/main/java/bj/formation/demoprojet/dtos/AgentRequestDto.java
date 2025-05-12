package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.AgentMatriculeAlreadyExist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentRequestDto {
    @NotEmpty(message = "Le matricule est obligatoire")
    @Size(min = 5, max = 8, message = "Le matricule doit être d'au moins 4 et d'au plus 8 caractères.")
    @AgentMatriculeAlreadyExist
    String matricule;

    @NotEmpty(message = "Le nom est obligatoire")
    @Size(min = 3, message = "Minimum trois caractères pour le nom")
    String nom;

    @NotEmpty(message = "Le nom est obligatoire")
    @Size(min = 3, message = "Minimum trois caractères pour le prenom")
    String prenom;

    @Past(message = "La date de naissance doit être valide")
    LocalDate dateNaissance;

    @NotNull(message = "Le nombre d'enfant est requis")
    @Min(value = 0, message = "Une valeur négative n'espt pas autorisé")
    @Max(value = 6, message = "Pas plus de 6 enfants")
    Integer nbreEnfant;

    @NotNull(message = "Le code du grade est obligatoire")
    @NotEmpty(message = "Le code du grade ne peut pas être vide")
    String grade_code;

    List<EnfantRequestDto> enfants = new ArrayList<>();
}
