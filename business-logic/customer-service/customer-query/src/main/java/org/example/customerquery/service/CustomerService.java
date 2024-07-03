package org.example.customerquery.service;

import org.example.customerdomain.model.IdentityModel;

public interface CustomerService {


    boolean existsByCustomerCode(String customerCode);

    long count();

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsCustomerByProof(IdentityModel proof);

    boolean existsByPhoneNumberAndIdIsNot(String customerId, String phoneNumber);

    boolean existsByEmailAndIdIsNot(String customerId, String email);

    boolean existsCustomerByProofAndIdIsNot(IdentityModel proof, String customerId);
}
