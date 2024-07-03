package org.example.insuranceflow.repository;

import org.example.insurancedomain.entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceEntityRepository extends JpaRepository<InsuranceEntity, String> {
}
