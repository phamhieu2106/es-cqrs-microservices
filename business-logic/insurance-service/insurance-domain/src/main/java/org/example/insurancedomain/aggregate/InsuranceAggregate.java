package org.example.insurancedomain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.insurancedomain.command.InsuranceCreateCommand;
import org.example.insurancedomain.command.InsuranceDeleteCommand;
import org.example.insurancedomain.command.InsuranceUpdateCommand;
import org.example.insurancedomain.event.InsuranceCreateEvent;
import org.example.insurancedomain.event.InsuranceDeleteEvent;
import org.example.insurancedomain.event.InsuranceUpdateEvent;
import org.example.sharedlibrary.base.BaseAggregate;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class InsuranceAggregate extends BaseAggregate {
    private String insuranceId;
    private String insuranceCode;
    private String insuranceName;
    private Double totalPaymentFeeAmount;
    private Double totalInsuranceTotalFeeAmount;
    private Boolean isDelete = false;

    public InsuranceCreateEvent apply(InsuranceCreateCommand command) {
        this.insuranceCode = command.getInsuranceCode();
        this.insuranceId = generateId();
        this.insuranceName = command.getInsuranceName();
        this.totalPaymentFeeAmount = command.getTotalPaymentFeeAmount();
        this.totalInsuranceTotalFeeAmount = command.getTotalInsuranceTotalFeeAmount();

        return new InsuranceCreateEvent(
                new Date(),
                this.insuranceId,
                this.insuranceCode,
                this.insuranceName,
                this.totalPaymentFeeAmount,
                this.totalInsuranceTotalFeeAmount
        );
    }

    public InsuranceUpdateEvent apply(InsuranceUpdateCommand command) {
        this.insuranceId = command.getInsuranceId();
        this.insuranceCode = command.getInsuranceCode();
        this.insuranceName = command.getInsuranceName();
        this.totalPaymentFeeAmount = command.getTotalPaymentFeeAmount();
        this.totalInsuranceTotalFeeAmount = command.getTotalInsuranceTotalFeeAmount();

        return new InsuranceUpdateEvent(
                new Date(),
                this.insuranceId,
                this.insuranceCode,
                this.insuranceName,
                this.totalPaymentFeeAmount,
                this.totalInsuranceTotalFeeAmount
        );
    }

    public InsuranceDeleteEvent apply(InsuranceDeleteCommand command) {
        this.insuranceId = command.getInsuranceId();
        this.isDelete = true;

        return new InsuranceDeleteEvent(
                new Date(),
                this.insuranceId
        );
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


}
