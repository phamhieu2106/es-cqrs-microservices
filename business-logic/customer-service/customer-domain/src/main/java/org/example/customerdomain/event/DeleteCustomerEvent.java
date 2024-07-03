package org.example.customerdomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteCustomerEvent extends BaseEvent {
    String customerId;
    Boolean isDelete = true;

    public DeleteCustomerEvent(Date date, String customerId, String createdBy) {
        super(date, createdBy);
        this.customerId = customerId;
    }
}
