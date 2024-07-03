package org.example.insurancequery.service;

import org.example.sharedlibrary.response.WrapperResponse;

public interface InsuranceQueryService {
    WrapperResponse findAllInsuranceQueries();

    WrapperResponse findInsuranceQuery(String insuranceId);

    long countInsuranceQuery();

    boolean exitsByInsuranceCode(String insuranceCode);
}
