package org.example.customerdomain.aggregate;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.customerdomain.command.CreateCustomerCommand;
import org.example.customerdomain.command.DeleteCustomerCommand;
import org.example.customerdomain.command.UpdateCustomerCommand;
import org.example.customerdomain.enumeration.Gender;
import org.example.customerdomain.enumeration.StatusCustomer;
import org.example.customerdomain.event.CreateCustomerEvent;
import org.example.customerdomain.event.DeleteCustomerEvent;
import org.example.customerdomain.event.UpdateCustomerEvent;
import org.example.customerdomain.model.AddressModel;
import org.example.customerdomain.model.IdentityModel;
import org.example.sharedlibrary.base.BaseAggregate;
import org.example.sharedlibrary.base.BaseAggregateApply;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.sharedlibrary.constant.GenerateConstant;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerAggregate extends BaseAggregate
        implements BaseAggregateApply<CreateCustomerCommand, UpdateCustomerCommand, DeleteCustomerCommand> {

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
    String createdBy;


    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyCreate(CreateCustomerCommand command) {
        this.customerId = GenerateConstant.generateId();
        this.customerName = command.getCustomerName();
        this.gender = command.getGender();
        this.phoneNumber = command.getPhoneNumber();
        this.email = command.getEmail();
        this.dateOfBirth = command.getDateOfBirth();
        this.addressModels = command.getAddressModels();
        this.jobName = command.getJobName();
        this.proof = command.getProof();
        this.statusCustomer = StatusCustomer.POTENTIAL;
        this.createdBy = command.getCreatedBy();

        return (E) new CreateCustomerEvent(
                new Date(),
                this.customerId,
                this.customerCode,
                this.customerName,
                this.gender,
                this.phoneNumber,
                this.email,
                this.dateOfBirth,
                this.addressModels,
                this.jobName,
                this.proof,
                this.statusCustomer,
                this.createdBy
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyUpdate(UpdateCustomerCommand command) {
        CustomerAggregate updatedAggregate = new CustomerAggregate(
                this.customerId,
                this.customerCode,
                command.getCustomerName(),
                command.getGender(),
                command.getPhoneNumber(),
                command.getEmail(),
                command.getDateOfBirth(),
                command.getAddressModels(),
                command.getJobName(),
                command.getProof(),
                command.getStatusCustomer(),
                false,
                this.createdBy
        );

        return (E) new UpdateCustomerEvent(
                new Date(),
                updatedAggregate.getCustomerId(),
                updatedAggregate.getCustomerCode(),
                updatedAggregate.getCustomerName(),
                updatedAggregate.getGender(),
                updatedAggregate.getPhoneNumber(),
                updatedAggregate.getEmail(),
                updatedAggregate.getDateOfBirth(),
                updatedAggregate.getAddressModels(),
                updatedAggregate.getJobName(),
                updatedAggregate.getProof(),
                updatedAggregate.getStatusCustomer(),
                this.createdBy
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends BaseEvent> E applyDelete(DeleteCustomerCommand command) {
        this.customerId = command.getCustomerId();
        this.softDelete = true;
        this.createdBy = command.getCreatedBy();
        return (E) new DeleteCustomerEvent(
                new Date(),
                this.customerId,
                this.createdBy
        );
    }


}
