package org.example.customerquery.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.customerdomain.model.IdentityModel;
import org.example.customerquery.repository.CustomerRepository;
import org.example.customerquery.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public boolean existsByCustomerCode(String customerCode) {
        return repository.existsByCustomerCode(customerCode);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return repository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existsCustomerByProof(IdentityModel proof) {
        return repository.existsCustomerByProof(proof);
    }

    @Override
    public boolean existsByPhoneNumberAndIdIsNot(String customerId, String phoneNumber) {
        return repository.existsByPhoneNumberAndIdIsNot(phoneNumber, customerId);
    }

    @Override
    public boolean existsByEmailAndIdIsNot(String customerId, String email) {
        return repository.existsByEmailAndIdIsNot(email, customerId);
    }

    @Override
    public boolean existsCustomerByProofAndIdIsNot(IdentityModel proof, String customerId) {
        return repository.existsCustomerByProofAndIdIsNot(proof, customerId);
    }
}
