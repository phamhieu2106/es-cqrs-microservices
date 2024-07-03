package org.example.insurancecommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.insurancecommand.handler.InsuranceCommandHandler;
import org.example.insurancecommand.service.InsuranceService;
import org.example.insurancedomain.command.InsuranceCreateCommand;
import org.example.insurancedomain.command.InsuranceDeleteCommand;
import org.example.insurancedomain.command.InsuranceUpdateCommand;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceCommandHandler handler;
    @Override
    public WrapperResponse create(InsuranceCreateCommand command) {
        try {
            handler.handle(command);
        }catch (RuntimeException e){
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), null
        );
    }

    @Override
    public  WrapperResponse  update(InsuranceUpdateCommand command) {
        try {
            handler.handle(command);
        }catch (RuntimeException e){
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), null
        );
    }

    @Override
    public  WrapperResponse delete(InsuranceDeleteCommand command) {
        try {
            handler.handle(command);
        }catch (RuntimeException e){
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), null
        );
    }
}
