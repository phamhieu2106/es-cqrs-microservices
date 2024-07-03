package org.example.insurancequery.controller;

import lombok.RequiredArgsConstructor;
import org.example.insurancequery.service.InsuranceQueryService;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurances")
public class InsuranceQueryController {
    private final InsuranceQueryService insuranceQueryService;

    @GetMapping
    public WrapperResponse findInsurances() {
        return insuranceQueryService.findAllInsuranceQueries();
    }

    @GetMapping("/detail/{insuranceId}")
    public WrapperResponse findInsurances(@PathVariable String insuranceId) {
        return insuranceQueryService.findInsuranceQuery(insuranceId);
    }
}
