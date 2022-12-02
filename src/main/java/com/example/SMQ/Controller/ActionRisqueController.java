package com.example.SMQ.Controller;

import com.example.SMQ.Dto.ActionRisqueDto;
import com.example.SMQ.Dto.ConsequancesDto;
import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Consequances;
import com.example.SMQ.Services.ActionRisqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/actionRisque")
@RestController
@AllArgsConstructor
public class ActionRisqueController {
    @Autowired
    private final ActionRisqueService actionRisqueService;

    @GetMapping("/actionRisques")
    public ResponseEntity<List<ActionRisqueDto>> getAllActionRisques() {
        List<ActionRisqueDto> actionRisques = actionRisqueService.getAllActionRisques();
        return new ResponseEntity<>(actionRisques, HttpStatus.OK);
    }

    @GetMapping(path = "/actionRisque/{idActionRisque}", produces = "application/json")
    public ResponseEntity<ActionRisqueDto> getActionRisque (@PathVariable(value = "idActionRisque") Integer idActionRisque) {
        return new ResponseEntity<>(actionRisqueService.getActionRisqueById(idActionRisque), HttpStatus.OK);
    }

    @PostMapping("/actionRisque")
    public ResponseEntity<ActionRisque> addActionRisque(@RequestBody ActionRisque actionRisque) {
        ActionRisque a = actionRisqueService.addActionRisque(actionRisque);
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }
    @PutMapping(consumes = "application/json", produces = "application/json", path = "/actionRisque/{idActionRisque}")
    public ActionRisque updateConsequance(@PathVariable Integer idActionRisque,@RequestBody ActionRisque reclamation) {
        ActionRisque baseReclamation = actionRisqueService.findActionRisqueById(idActionRisque);

        if (baseReclamation.getDateDecision() != reclamation.getDateDecision()) {
            baseReclamation.setDateDecision(reclamation.getDateDecision());
        }
        if (baseReclamation.getLibelle() != reclamation.getLibelle()) {
            baseReclamation.setLibelle(reclamation.getLibelle());
        }
        if (baseReclamation.getResponsable() != reclamation.getResponsable()) {
            baseReclamation.setResponsable(reclamation.getResponsable());
        }
        if (baseReclamation.getCin() != reclamation.getCin()) {
            baseReclamation.setCin(reclamation.getCin());
        }

        return  actionRisqueService.updateActionRisque(baseReclamation);
    }


    @DeleteMapping("/actionRisque/{idActionRisque}")
    public  void deleteActionRisque(@PathVariable(name = "idActionRisque") Integer idActionRisque) {
        String message = actionRisqueService.deleteActionRisque(idActionRisque);
    }
}
