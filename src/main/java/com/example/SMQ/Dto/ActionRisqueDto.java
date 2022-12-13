package com.example.SMQ.Dto;

import com.example.SMQ.Model.Causes;
import com.example.SMQ.Model.Risque;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionRisqueDto {
    private int idActionRisque;
    private String libelle;
    private String responsable;
    private String cin;
    private String dateDecision;
    private Risque risque;
}
