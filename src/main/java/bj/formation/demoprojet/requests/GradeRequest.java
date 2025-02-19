package bj.formation.demoprojet.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {
    private String code;
    private String libelle;
    private Integer indice;
}
