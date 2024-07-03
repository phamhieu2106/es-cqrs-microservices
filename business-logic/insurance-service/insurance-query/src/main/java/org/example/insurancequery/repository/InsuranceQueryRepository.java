package org.example.insurancequery.repository;

import org.example.insurancedomain.entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceQueryRepository extends JpaRepository<InsuranceEntity,String> {
    boolean existsByInsuranceCode(String insuranceCode);
}
