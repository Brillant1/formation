package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;


@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="t_niveau_enfant")
public class NiveauEnfant implements Serializable {
    @Id
    @Column(name = "nive_code", unique = true, nullable = false)
    private String code;

    @Column(name = "nive_libelle")
    private String libelle;

    @Column(name = "nive_age_limit")
    private  Integer ageLimit;

    @PrePersist
    public void generateCode(){
        this.code = "NIV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
