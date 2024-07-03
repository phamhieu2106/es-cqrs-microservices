package org.example.insurancequery.controller.internal;

import lombok.RequiredArgsConstructor;
import org.example.insurancequery.service.InsuranceQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/insurances")
public class InternalInsuranceQueryController {
    private final InsuranceQueryService insuranceQueryService;

    @GetMapping("/count")
    public long getInsuranceQueryCount() {
        return insuranceQueryService.countInsuranceQuery();
    }

    @GetMapping("/is-exits-by-insurance-code/{insuranceCode}")
    public boolean isInsuranceCodeExitsByInsuranceCode(@PathVariable String insuranceCode) {
        return insuranceQueryService.exitsByInsuranceCode(insuranceCode);
    }
}
