package org.example.insurancedomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsuranceCreateEvent extends BaseEvent {
    Date timestamp;
    String insuranceCode;
    String insuranceId;
    String insuranceName;
    Double totalPaymentFeeAmount;
    Double totalInsuranceTotalFeeAmount;
    Boolean isDelete = false;

    public InsuranceCreateEvent(Date timestamp, String insuranceId, String insuranceCode,String insuranceName
            , Double totalPaymentFeeAmount, Double totalInsuranceTotalFeeAmount) {
        super(timestamp);
        this.insuranceCode = insuranceCode;
        this.insuranceId = insuranceId;
        this.insuranceName = insuranceName;
        this.totalPaymentFeeAmount = totalPaymentFeeAmount;
        this.totalInsuranceTotalFeeAmount = totalInsuranceTotalFeeAmount;
    }
}