package org.example.customerdomain.command;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseCommand;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteCustomerCommand extends BaseCommand {
    String customerId;
    Boolean softDelete = true;
    String createdBy;

    public DeleteCustomerCommand(String customerId, String createdBy) {
        this.customerId = customerId;
        this.createdBy = createdBy;
    }
}
