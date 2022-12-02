package com.example.SMQ.Dto;

import com.example.SMQ.Model.ActionRisque;
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
public class RisqueDto {
    private int idRisque;
    private LocalDateTime dateDetection;
    private String type;
    private String description;
    private String lieuOuPromotion;
    private String acteur;
    private String niveau;
    private String evolution;
    private List<ActionRisqueDto> actionRisque;
}
