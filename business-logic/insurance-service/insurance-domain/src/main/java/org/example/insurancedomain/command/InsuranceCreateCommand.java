package org.example.insurancedomain.command;

import lombok.Getter;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseCommand;


@Getter
@Setter
public class InsuranceCreateCommand extends BaseCommand {
    private String insuranceName;
    private String insuranceCode;
    private Double totalPaymentFeeAmount;
    private Double totalInsuranceTotalFeeAmount;

    public InsuranceCreateCommand(String insuranceName, Double totalInsuranceTotalFeeAmount
            , Double totalPaymentFeeAmount, String insuranceCode) {
        this.insuranceName = insuranceName;
        this.totalInsuranceTotalFeeAmount = totalInsuranceTotalFeeAmount;
        this.totalPaymentFeeAmount = totalPaymentFeeAmount;
        this.insuranceCode = insuranceCode;
    }

}
