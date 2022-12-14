package com.example.SMQ.Dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PieceJointeDto {
    private int idPieceJointe ;
    private String name;
    private String type;
    private byte[] file;
}
