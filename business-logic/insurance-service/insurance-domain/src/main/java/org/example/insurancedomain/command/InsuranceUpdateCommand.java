package org.example.insurancedomain.command;

import lombok.Getter;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseCommand;

@Getter
@Setter
public class InsuranceUpdateCommand extends BaseCommand {
    private String insuranceId;
    private String insuranceCode;
    private String insuranceName;
    private Double totalPaymentFeeAmount;
    private Double totalInsuranceTotalFeeAmount;

    public InsuranceUpdateCommand(String insuranceId, Double totalInsuranceTotalFeeAmount, Double totalPaymentFeeAmount, String insuranceName, String insuranceCode) {
        this.insuranceId = insuranceId;
        this.totalInsuranceTotalFeeAmount = totalInsuranceTotalFeeAmount;
        this.totalPaymentFeeAmount = totalPaymentFeeAmount;
        this.insuranceName = insuranceName;
        this.insuranceCode = insuranceCode;
    }

    public InsuranceUpdateCommand() {
    }

}
