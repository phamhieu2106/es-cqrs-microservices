package org.example.contractdomain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsuranceModel {
    String insuranceName;
    Double totalPaymentFeeAmount;
    Double totalInsuranceTotalFeeAmount;
}
