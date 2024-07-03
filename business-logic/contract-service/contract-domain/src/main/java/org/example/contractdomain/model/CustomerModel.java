package org.example.contractdomain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.enumerate.Gender;
import org.example.sharedlibrary.enumerate.StatusCustomer;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerModel {
    String customerCode;
    String customerName;
    Gender gender;
    String phoneNumber;
    String email;
    StatusCustomer statusCustomer;
}
