package org.example.customerquery.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.enumeration.StatusCustomer;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CustomerResponse {
    String id;
    String customerCode;
    String customerName;
    Gender gender;
    String phoneNumber;
    String email;
    Date dateOfBirth;
    List<AddressModel> addressModels;
    IdentityModel proof;
    StatusCustomer statusCustomer;
}
