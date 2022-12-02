package com.example.SMQ.Repository;

import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Consequances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsequancesRepository extends JpaRepository<Consequances, Integer> {
}
