package org.example.contractcommand.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.contractcommand.service.ContractCommandService;
import org.example.contractcommand.service.ContractHandlerService;
import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.DeleteContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractCommandServiceImpl implements ContractCommandService {

    private final ContractHandlerService handlerService;

    @Override
    public WrapperResponse create(CreateContractCommand command, String username) {
        try {
            command.setCreatedBy(username);
            handlerService.handleCreate(command);
        } catch (Exception e) {
            return new WrapperResponse().fail(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new WrapperResponse().success(
                HttpStatus.OK.getReasonPhrase(), HttpStatus.OK
        );
    }

    @Override
    public WrapperResponse update(UpdateContractCommand command, String username) {
        return null;
    }

    @Override
    public WrapperResponse delete(DeleteContractCommand command, String username) {
        return null;
    }
}
