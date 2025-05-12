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
public class EnfantRequestDto{

        @NotEmpty(message = "Le nom est obligatoire")
        @Size(min = 3, message = "Minimum trois caractères pour le nom")
        String nom;

        @NotEmpty(message = "Le nom est obligatoire")
        @Size(min = 3, message = "Minimum trois caractères pour le nom")
        String prenom;

        @NotNull(message = "La date de naissance est obligatoire")
        @Past(message = "La date de naissance doit être valide")
        LocalDate dateNaissance;

        @NotEmpty(message = "Veillez renseigner le niveau de l'enfant")
        String code_niveau_enfant;

        @NotEmpty(message = "Veillez renseigner l'agent à qui appartient l'enfant")
        String agentMatricule;
}
