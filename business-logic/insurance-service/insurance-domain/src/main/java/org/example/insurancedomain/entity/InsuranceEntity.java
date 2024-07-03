package org.example.insurancedomain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class InsuranceEntity {
    @Id
    String id;
    String insuranceCode;
    String insuranceName;
    Double totalPaymentFeeAmount;
    Double totalInsuranceTotalFeeAmount;
    Boolean isDelete = false;
    Date createdAt = new Date();
}
