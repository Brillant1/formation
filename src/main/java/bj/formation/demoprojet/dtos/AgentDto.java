package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.AgentMatriculeAlreadyExist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AgentDto {

        @NotEmpty(message = "Le matricule de l'agent est obligatoire")
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

        @NotEmpty(message = "Le nombre d'enfant est requis")
        Integer nbreEnfant;
}
