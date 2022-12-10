package com.example.SMQ.Repository;

import com.example.SMQ.Model.Consequances;
import com.example.SMQ.Model.PieceJointe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PieceJointeRepository extends JpaRepository<PieceJointe, Integer> {
    Optional<PieceJointe> findAllByName(String name);
}
