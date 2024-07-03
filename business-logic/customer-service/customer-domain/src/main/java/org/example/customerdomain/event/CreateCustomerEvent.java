package org.example.customerdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.enumeration.StatusCustomer;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerEvent extends BaseEvent {
    String customerId;
    String customerCode;
    String customerName;
    Gender gender;
    String phoneNumber;
    String email;
    Date dateOfBirth;
    List<AddressModel> addressModels;
    String jobName;
    IdentityModel proof;
    StatusCustomer statusCustomer;
    Boolean softDelete = false;

    public CreateCustomerEvent(Date date, String customerId, String customerCode, String customerName, Gender gender
            , String phoneNumber, String email, Date dateOfBirth, List<AddressModel> addressModels, String jobName
            , IdentityModel proof, StatusCustomer statusCustomer, String createdBy) {
        super(date, createdBy);
        this.customerId = customerId;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.addressModels = addressModels;
        this.jobName = jobName;
        this.proof = proof;
        this.statusCustomer = statusCustomer;
    }
}
