package org.example.insurancedomain.command;

import lombok.Getter;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseCommand;

@Getter
@Setter
public class InsuranceDeleteCommand extends BaseCommand {
    private String insuranceId;

    public InsuranceDeleteCommand(String insuranceId) {
        this.insuranceId = insuranceId;
    }
}
