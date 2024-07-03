package org.example.insurancecommand.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "insurance-query", path = "/internal/api/insurances")
public interface InsuranceServiceClient {

    @GetMapping("/count")
    long getInsuranceQueryCount();

    @GetMapping("/is-exits-by-insurance-code/{insuranceCode}")
    boolean isInsuranceCodeExitsByInsuranceCode(@PathVariable String insuranceCode);
}
