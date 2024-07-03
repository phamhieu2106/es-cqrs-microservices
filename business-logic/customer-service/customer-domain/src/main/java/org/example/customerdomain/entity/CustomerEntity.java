package org.example.customerdomain.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.customerdomain.converter.AddressModelConverter;
import org.example.customerdomain.converter.IdentityModelConverter;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.enumeration.StatusCustomer;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        indexes = {
                @Index(name = "idx_customer_code", columnList = "customerCode"),
                @Index(name = "idx_customer_name", columnList = "customerName"),
                @Index(name = "idx_customer_status", columnList = "statusCustomer"),
                @Index(name = "idx_customer_code_name_status",
                        columnList = "customerCode,customerName,statusCustomer"),
                @Index(name = "idx_customer_soft_delete", columnList = "softDelete"),
                @Index(name = "idx_customer_created_at", columnList = "createdAt")
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerEntity implements Serializable {
    @Id
    String id;
    String customerCode;
    String customerName;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String phoneNumber;
    String email;
    Date dateOfBirth;
    @Convert(converter = AddressModelConverter.class)
    @Column(length = 10000)
    List<AddressModel> addressModels;
    String jobName;
    @Convert(converter = IdentityModelConverter.class)
    @Column(length = 10000)
    IdentityModel proof;
    @Enumerated(EnumType.STRING)
    StatusCustomer statusCustomer;
    Boolean softDelete = false;
    Date createdAt;
    String createdBy;
}
