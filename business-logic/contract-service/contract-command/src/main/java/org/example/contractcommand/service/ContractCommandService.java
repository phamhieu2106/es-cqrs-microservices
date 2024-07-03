package org.example.contractcommand.service;

import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.DeleteContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.sharedlibrary.response.WrapperResponse;

public interface ContractCommandService {

    WrapperResponse create(CreateContractCommand command, String username);

    WrapperResponse update(UpdateContractCommand command, String username);

    WrapperResponse delete(DeleteContractCommand command, String username);

}
