package com.example.SMQ.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_Risque")
public class Risque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRisque;
    private LocalDateTime dateDetection;
    private String type;
    private String description;
    private String lieuOuPromotion;
    private String acteur;
    private String niveau;
    private String evolution;

    @OneToMany(mappedBy = "risque")
    @JsonIgnore
    private List<ActionRisque> actionRisque;
}
