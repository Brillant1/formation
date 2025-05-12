package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.annotations.UniqueGrade;
import bj.formation.demoprojet.validators.OnCreate;
import bj.formation.demoprojet.validators.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record GradeDto(
        @NotEmpty(message = "Renseigner le code du grade")
        @UniqueGrade(groups = OnCreate.class)
        String code,

        @NotEmpty(message = "Renseigner le libelle du grade")
        String libelle,

        @NotNull(message = "Renseigner l'indice du grade")
        @Min(value = 1,message = "La valeur minimale est 1")
        Integer indice) {
}
