package org.example.customerdomain.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;
import org.example.sharedlibrary.base.BaseCommand;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerCommand extends BaseCommand {
    String customerName;
    Gender gender;
    String phoneNumber;
    String email;
    Date dateOfBirth;
    List<AddressModel> addressModels;
    String jobName;
    IdentityModel proof;
    String createdBy;
}
