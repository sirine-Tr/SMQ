package com.example.SMQ.Services;

import com.example.SMQ.Dto.ActionRisqueDto;
import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Dto.RisqueDto;
import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Model.Risque;
import com.example.SMQ.Repository.ActionRisqueRepository;
import com.example.SMQ.Repository.RisqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class RisqueService {
    private final RisqueRepository risqueRepository;
    private final ActionRisqueRepository actionRisqueRepository;
    private final ActionRisqueService actionRisqueService;
    public Risque addRisque(Risque risque) {
        return risqueRepository.save(risque);
    }
    public RisqueDto getRisque(Integer idRisque) {
        Optional<Risque> optionalRisque = risqueRepository.findById(idRisque);
        if (optionalRisque.isPresent()) {
            return mapEntityToDto(optionalRisque.get());
        }
        return null;
    }
    /*  public List<RisqueDto> getAllRisque() {
          List<RisqueDto> risqueDtos = new ArrayList<>();
          List<Risque> risques = risqueRepository.findAll();
          risques.stream().forEach(risque -> {
              RisqueDto risqueDto = mapEntityToDto(risque);
              risqueDtos.add(risqueDto);
          });
          return risqueDtos;
      }*/
    public List<RisqueDto> getAllRisque(int pageNumber, int pageSize) {
        PageRequest pages = PageRequest.of(pageNumber, pageSize);
        List<RisqueDto> risqueDtos = new ArrayList<>();
        List<Risque> risques = risqueRepository.findAll(pages).getContent();
        risques.stream().forEach(risque -> {
            RisqueDto risqueDto = mapEntityToDto(risque);
            risqueDtos.add(risqueDto);
        });
        return risqueDtos;
    }
    public Risque getRisqueById(int id) {
        return risqueRepository.findById(id).get();
    }

    public Risque updateRisque(Risque risque) {
        return risqueRepository.save(risque);
    }

    public String deleteRisque(int id) {
        if (risqueRepository.findById(id).isPresent()){
            risqueRepository.deleteById(id);
            return "Risque with id: " + id + " deleted successfully!";
        }
        return null;
    }

    public RisqueDto mapEntityToDto(Risque center) {
        RisqueDto centerDto = new RisqueDto();
        centerDto.setIdRisque(center.getIdRisque());
        centerDto.setDateDetection(center.getDateDetection());
        centerDto.setType(center.getType());
        centerDto.setDescription(center.getDescription());
        centerDto.setActeur(center.getActeur());
        centerDto.setNiveau(center.getNiveau());
        centerDto.setEvolution(center.getEvolution());
        centerDto.setLieuOuPromotion(center.getLieuOuPromotion());
        List<ActionRisqueDto> departementDtos =new ArrayList<>();
        List<ActionRisque> departements =center.getActionRisque();
        departements.stream().forEach(departement -> {
            ActionRisqueDto departementDto = actionRisqueService.mapEntityToDto(departement);
            departementDtos.add(departementDto);
        });


        centerDto.setActionRisque(departementDtos);


        return centerDto;
    }


}
