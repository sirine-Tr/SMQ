package com.example.SMQ.Services;

import com.example.SMQ.Dto.CausesDto;
import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Model.Causes;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Repository.CausesRepository;
import com.example.SMQ.Repository.ConsequancesRepository;
import com.example.SMQ.Repository.ReclamationRepository;
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
public class ReclamationService {
    private final ReclamationRepository reclamationRepository;
    private final CausesRepository causesRepository;
    private final ConsequancesRepository consequancesRepository;

    /*public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }*/
//work
    public List<ReclamationDto> getAllReclamations(int pageNumber, int pageSize) {
        PageRequest pages = PageRequest.of(pageNumber, pageSize);
        List<ReclamationDto> reclamationDtos = new ArrayList<>();
        List<Reclamation> reclamations = reclamationRepository.findAll(pages).getContent();
        reclamations.stream().forEach(reclamation -> {
            ReclamationDto causesDto = mapEntityToDto(reclamation);
            reclamationDtos.add(causesDto);
        });
        return reclamationDtos;
    }

   /* public Reclamation findByName(String name) {
        Optional<Reclamation> optionalReclamation = (reclamationRepository.findAllByName(name));
        if (optionalReclamation.isPresent()) {
            return (optionalReclamation.get());
        }
        return null;
    }*/
//work
    public Reclamation getReclamationById(int id) {
        return reclamationRepository.findById(id).get();
    }

    public Reclamation addReclamation(Reclamation reclamation) {

        return reclamationRepository.save(reclamation);
    }

    public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public String deleteReclamation(int id) {
        if (reclamationRepository.findById(id).isPresent()){
            reclamationRepository.deleteById(id);
        return "Reclamation with id: " + id + " deleted successfully!";
    }
        return null;
    }

    public ReclamationDto mapEntityToDto(Reclamation reclamation) {
        ReclamationDto reclamationDto = new ReclamationDto();
        reclamationDto.setIdReclamation(reclamation.getIdReclamation());
        reclamationDto.setDateDetection(reclamation.getDateDetection());
        reclamationDto.setType(reclamation.getType());
        reclamationDto.setGravite(reclamation.getGravite());
        reclamationDto.setDescription(reclamation.getDescription());
        reclamationDto.setLieuOuPromotion(reclamation.getLieuOuPromotion());
        reclamationDto.setActeur(reclamation.getActeur());
        reclamationDto.setCauses(reclamation.getCauses());
        reclamationDto.setConsequances(reclamation.getConsequances());
        return reclamationDto;
    }
}
