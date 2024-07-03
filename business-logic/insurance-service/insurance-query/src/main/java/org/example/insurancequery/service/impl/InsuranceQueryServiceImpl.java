package org.example.insurancequery.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.example.insurancedomain.entity.InsuranceEntity;
import org.example.insurancequery.repository.InsuranceQueryRepository;
import org.example.insurancequery.service.InsuranceQueryService;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InsuranceQueryServiceImpl implements InsuranceQueryService {

    private final InsuranceQueryRepository insuranceQueryRepository;

    @Override
    public WrapperResponse findAllInsuranceQueries() {
        try {
            List<InsuranceEntity> list = insuranceQueryRepository.findAll();
            return new WrapperResponse().success(HttpStatus.OK.getReasonPhrase(), list);
        }catch (Exception e){
            return new WrapperResponse().fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public WrapperResponse findInsuranceQuery(String insuranceId) {
        try {
            Optional<InsuranceEntity> optional = insuranceQueryRepository.findById(insuranceId);
            if(optional.isEmpty()){
                return new WrapperResponse().fail(HttpStatus.NOT_FOUND.getReasonPhrase()
                        , HttpStatus.NOT_FOUND);
            }
            return new WrapperResponse().success(HttpStatus.OK.getReasonPhrase(), optional.get());
        }catch (Exception e){
            return new WrapperResponse().fail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public long countInsuranceQuery() {
        return insuranceQueryRepository.count();
    }

    @Override
    public boolean exitsByInsuranceCode(String insuranceCode) {
        return insuranceQueryRepository.existsByInsuranceCode(insuranceCode);
    }
}
