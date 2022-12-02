package com.example.SMQ.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionReclamationDto {
    private int idDecision;
    private String libelle;
    private String responsable;
    private String cin;
    private LocalDateTime dateDecision;
    private CausesDto causesDto;
}
