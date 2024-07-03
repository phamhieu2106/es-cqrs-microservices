package org.example.insurancecommand.controller;

import lombok.RequiredArgsConstructor;
import org.example.insurancecommand.service.InsuranceService;
import org.example.insurancedomain.command.InsuranceCreateCommand;
import org.example.insurancedomain.command.InsuranceDeleteCommand;
import org.example.insurancedomain.command.InsuranceUpdateCommand;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping("/create")
    public WrapperResponse createInsurance(@RequestBody InsuranceCreateCommand command) {
        return insuranceService.create(command);
    }

    @PostMapping("/update/{insuranceId}")
    public WrapperResponse updateInsurance(@PathVariable String insuranceId,
            @RequestBody InsuranceUpdateCommand command) {
        command.setInsuranceId(insuranceId);
        return insuranceService.update(command);
    }

    @PostMapping("/delete/{insuranceId}")
    public WrapperResponse updateInsurance(@PathVariable String insuranceId) {
        InsuranceDeleteCommand command = new InsuranceDeleteCommand(insuranceId);
        return insuranceService.delete(command);
    }

}
