package org.example.sharedlibrary.base;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEvent {
    private Date timestamp;
    private String createdBy;

    public BaseEvent(Date timestamp, String createdBy) {
        this.timestamp = timestamp;
        this.createdBy = createdBy;
    }

    public BaseEvent() {

    }
}
