package bj.formation.demoprojet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_element_paie")
public class ElementPaie implements Serializable {
    @Id
    @Column(name = "elementPaie_code", unique = true, nullable = false)
    private String code;

    @Column(name = "element_paie_libelle")
    private String libelle;




}
