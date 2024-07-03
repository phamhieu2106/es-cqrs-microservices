package org.example.contractcommand.service;

import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.DeleteContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.sharedlibrary.BaseEventHandler;

public interface ContractHandlerService extends BaseEventHandler<CreateContractCommand, UpdateContractCommand, DeleteContractCommand> {
}
