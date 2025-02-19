package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;
import java.util.UUID;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_grade")
public class Grade implements Serializable {
    @Id
    @Column(name = "grad_code", unique = true, nullable = false)
    private  String code;

    @Column(name = "grad_libelle")
    private String libelle;

    @Column(name = "grad_indice")
    private Integer indice;

    @PrePersist
    public void generateCode() {
        this.code = "GRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

