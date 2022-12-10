package com.example.SMQ.Services;

import com.example.SMQ.Model.PieceJointe;
import com.example.SMQ.Repository.PieceJointeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PieceJointeService {
    @Autowired
    PieceJointeRepository pieceJointeRepository;
    public List<PieceJointe> getAllPieceJointe() {
        return pieceJointeRepository.findAll();
    }
    public PieceJointe findByName(String name) {
        Optional<PieceJointe> optionalPieceJointe = (pieceJointeRepository.findAllByName(name));
        if (optionalPieceJointe.isPresent()) {
            return (optionalPieceJointe.get());
        }
        return null;
    }
    public PieceJointe getPieceJointeById(int idPieceJointe) {
        return pieceJointeRepository.findById(idPieceJointe).get();
    }
    public PieceJointe addPieceJointe(PieceJointe pieceJointe) {
        return pieceJointeRepository.save(pieceJointe);
    }
    public PieceJointe updatePieceJointe(PieceJointe pieceJointe) {
        return pieceJointeRepository.save(pieceJointe);
    }
    public void deletePieceJointe(int id) {
        if (pieceJointeRepository.findById(id).isPresent())
            pieceJointeRepository.deleteById(id);
    }
}
