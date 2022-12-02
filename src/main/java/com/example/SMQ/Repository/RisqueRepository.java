package com.example.SMQ.Repository;

import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Risque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RisqueRepository extends JpaRepository<Risque, Integer> {
}
