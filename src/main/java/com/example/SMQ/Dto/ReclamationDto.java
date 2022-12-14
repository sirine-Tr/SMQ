package com.example.SMQ.Dto;

import com.example.SMQ.Model.Causes;
import com.example.SMQ.Model.Consequances;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDto {
    private int idReclamation;
    private String dateDetection;
    private String type;
    private String gravite;
    private String description;
    private String lieuOuPromotion;
    private String acteur;
    private List<Causes> causes;
    private List<Consequances> consequances;
}
