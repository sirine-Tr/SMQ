package com.example.SMQ.Controller;

import com.example.SMQ.Dto.ConsequancesDto;
import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Model.Consequances;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Repository.ConsequancesRepository;
import com.example.SMQ.Repository.ReclamationRepository;
import com.example.SMQ.Services.ConsequancesService;
import com.example.SMQ.Services.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/consequance")
@RestController
@AllArgsConstructor

public class ConsequancesController {
    @Autowired
    private final ConsequancesService consequanceService;
    private final ConsequancesRepository consequanceRepository;
    @GetMapping("/consequances")
    public ResponseEntity<List<ConsequancesDto>> getAllConsequances(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ConsequancesDto> consequances = consequanceService.getAllConsequances(pageNumber, pageSize);
        return new ResponseEntity<>(consequances, HttpStatus.OK);
    }
    @GetMapping(path = "/consequance/{idConsequances}", produces = "application/json")
    public ResponseEntity<ConsequancesDto> getConsequance(@PathVariable(value = "idConsequances") Integer idConsequances) {
        return new ResponseEntity<>(consequanceService.getConsequanceById(idConsequances), HttpStatus.OK);
    }

    @PostMapping("/consequance")
    public ResponseEntity<Consequances> addConsequances(@RequestBody Consequances consequances) {
        Consequances c = consequanceService.addConsequance(consequances);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
    @PutMapping(consumes = "application/json", produces = "application/json", path = "/consequance/{idConsequances}")
    public Consequances updateConsequance(@PathVariable Integer idConsequances,@RequestBody Consequances reclamation) {
        Consequances baseReclamation = consequanceService.getConsequanceByIdNotDto(idConsequances);

        if (baseReclamation.getDateCreation() != reclamation.getDateCreation()) {
            baseReclamation.setDateCreation(reclamation.getDateCreation());
        }
        if (baseReclamation.getDescriptionConsequances() != reclamation.getDescriptionConsequances()) {
            baseReclamation.setDescriptionConsequances(reclamation.getDescriptionConsequances());
        }

        return  consequanceService.updateConsequance(baseReclamation);
    }

    @DeleteMapping("/consequance/{idConsequances}")
    public  void deleteConsequance(@PathVariable(name = "idConsequances") Integer idConsequances) {
        String message = consequanceService.deleteConsequances(idConsequances);
    }
}
