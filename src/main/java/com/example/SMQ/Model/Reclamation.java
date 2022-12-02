package com.example.SMQ.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_Reclamation")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","causes","consequances"})
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idReclamation;
    private LocalDateTime dateDetection;
    private String type;
    private String gravite;
    private String description;
    private String lieuOuPromotion;
    private String acteur;

    @OneToMany(mappedBy = "reclamation")
    @JsonIgnore
    private List<Causes> causes;

    @OneToMany(mappedBy = "reclamation")
    @JsonIgnore
    private List<Consequances> consequances;


}
