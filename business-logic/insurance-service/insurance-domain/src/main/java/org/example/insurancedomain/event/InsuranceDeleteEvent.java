package org.example.insurancedomain.event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.base.BaseEvent;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsuranceDeleteEvent extends BaseEvent {
    Date timestamp;
    String insuranceId;
    Boolean isDelete = true;

    public InsuranceDeleteEvent(Date timestamp, String insuranceId) {
        super(timestamp);
        this.insuranceId = insuranceId;
    }
}
