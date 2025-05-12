package bj.formation.demoprojet.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGradeDto {

    String code;

    @NotEmpty(message = "Renseigner le libelle du grade")
    String libelle;

    @NotNull(message = "Renseigner l'indice du grade")
    @Min(value = 1,message = "La valeur minimale est 1")
    Integer indice;
}
