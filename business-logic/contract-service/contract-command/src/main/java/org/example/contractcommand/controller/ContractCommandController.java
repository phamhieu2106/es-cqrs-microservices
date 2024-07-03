package org.example.contractcommand.controller;

import lombok.RequiredArgsConstructor;
import org.example.contractcommand.service.ContractCommandService;
import org.example.contractdomain.command.CreateContractCommand;
import org.example.contractdomain.command.UpdateContractCommand;
import org.example.sharedlibrary.HeaderConstant;
import org.example.sharedlibrary.response.WrapperResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractCommandController {

    private final ContractCommandService service;

    @PostMapping("/create")
    public WrapperResponse create(@RequestBody CreateContractCommand command,
                                  @RequestHeader(value = HeaderConstant.REQUEST_HEADER, defaultValue = "anonymous") String username) {
        return service.create(command, username);
    }

    @PostMapping("/update/{contractId}")
    public WrapperResponse update(@RequestBody UpdateContractCommand command,
                                  @RequestHeader(value = HeaderConstant.REQUEST_HEADER, defaultValue = "anonymous") String username
            , @PathVariable String contractId) {
        return service.update(command, username);
    }
}
