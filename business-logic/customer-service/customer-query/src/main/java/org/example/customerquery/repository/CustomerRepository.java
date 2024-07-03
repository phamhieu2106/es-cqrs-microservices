package org.example.customerquery.repository;

import org.example.customerdomain.entity.CustomerEntity;
import org.example.customerdomain.model.IdentityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    boolean existsByCustomerCode(String code);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsCustomerByProof(IdentityModel proof);

    boolean existsByPhoneNumberAndIdIsNot(String phoneNumber, String customerId);

    boolean existsByEmailAndIdIsNot(String email, String customerId);

    boolean existsCustomerByProofAndIdIsNot(IdentityModel proof, String customerId);
}
