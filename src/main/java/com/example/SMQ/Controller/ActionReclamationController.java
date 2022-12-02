package com.example.SMQ.Controller;

import com.example.SMQ.Dto.ActionReclamationDto;
import com.example.SMQ.Dto.ActionRisqueDto;
import com.example.SMQ.Model.ActionReclamation;
import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Services.ActionReclamationService;
import com.example.SMQ.Services.ActionRisqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RequestMapping(path = "api/actionReclamation")
@RestController
@AllArgsConstructor
public class ActionReclamationController {
    @Autowired
    private final ActionReclamationService actionReclamationService;

    @GetMapping("/actionReclamations")
    public ResponseEntity<List<ActionReclamationDto>> getAllActionReclamations() {
        List<ActionReclamationDto> actionReclamation = actionReclamationService.getAllActionReclamation();
        return new ResponseEntity<>(actionReclamation, HttpStatus.OK);
    }
    @GetMapping(path = "/actionReclamation/{idDecision}", produces = "application/json")
    public ResponseEntity<ActionReclamationDto> getActionReclamation (@PathVariable(value = "idDecision") Integer idDecision) {
        return new ResponseEntity<>(actionReclamationService.getActionReclamationById(idDecision), HttpStatus.OK);
    }

    @PostMapping("/actionReclamation")
    public ResponseEntity<ActionReclamation> addActionReclamation(@RequestBody ActionReclamation actionReclamation) {
        ActionReclamation a = actionReclamationService.addActionReclamation(actionReclamation);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/actionReclamation/{idDecision}")
    public ActionReclamation updateActionReclamation(@PathVariable Integer idDecision,@RequestBody ActionRisque reclamation) {
        ActionReclamation baseActionReclamation = actionReclamationService.findActionReclamationById(idDecision);

        if (baseActionReclamation.getDateDecision() != reclamation.getDateDecision()) {
            baseActionReclamation.setDateDecision(reclamation.getDateDecision());
        }
        if (baseActionReclamation.getLibelle() != reclamation.getLibelle()) {
            baseActionReclamation.setLibelle(reclamation.getLibelle());
        }
        if (baseActionReclamation.getResponsable() != reclamation.getResponsable()) {
            baseActionReclamation.setResponsable(reclamation.getResponsable());
        }
        if (baseActionReclamation.getCin() != reclamation.getCin()) {
            baseActionReclamation.setCin(reclamation.getCin());
        }

        return  actionReclamationService.updateActionReclamation(baseActionReclamation);
    }

    @DeleteMapping("/actionReclamation/{idDecision}")
    public  void deleteActionReclamation(@PathVariable(name = "idDecision") Integer idDecision) {
        String message = actionReclamationService.deleteActionReclamation(idDecision);
    }
}
