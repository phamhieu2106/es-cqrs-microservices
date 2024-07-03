package org.example.customerdomain.view;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.enumeration.StatusCustomer;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(indexName = "customers")
public class CustomerView {

    @Id
    @Field(type = FieldType.Keyword)
    String id;

    @Field(type = FieldType.Keyword)
    String customerCode;

    @Field(type = FieldType.Text)
    String customerName;

    @Field(type = FieldType.Keyword)
    Gender gender;

    @Field(type = FieldType.Keyword)
    String phoneNumber;

    @Field(type = FieldType.Keyword)
    String email;

    @Field(type = FieldType.Date)
    Date dateOfBirth;

    @Field(type = FieldType.Nested)
    List<AddressModel> addressModels;

    @Field(type = FieldType.Keyword)
    String jobName;

    @Field(type = FieldType.Object)
    IdentityModel proof;

    @Field(type = FieldType.Keyword)
    StatusCustomer statusCustomer;

    @Field(type = FieldType.Boolean)
    Boolean softDelete = false;

    @Field(type = FieldType.Date)
    Date createdAt;

    @Field(type = FieldType.Keyword)
    String createdBy;
}
