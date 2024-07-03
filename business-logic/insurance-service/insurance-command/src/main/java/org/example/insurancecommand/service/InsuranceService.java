package org.example.insurancecommand.service;

import org.example.insurancedomain.command.InsuranceCreateCommand;
import org.example.insurancedomain.command.InsuranceDeleteCommand;
import org.example.insurancedomain.command.InsuranceUpdateCommand;
import org.example.sharedlibrary.response.WrapperResponse;

public interface InsuranceService {

    WrapperResponse create(InsuranceCreateCommand command);

    WrapperResponse update(InsuranceUpdateCommand command);

    WrapperResponse delete(InsuranceDeleteCommand command);
}
