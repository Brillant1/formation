package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.NiveauEnfant;

import java.time.LocalDate;


public record EnfantDto(
        String nom,
        String prenom,
        LocalDate dateNaissance,
        NiveauEnfant niveauEnfant,
        Agent agent) { }
