package org.example.userdomain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseEvent;
import org.example.userdomain.aggregate.UserAggregate;
import org.example.userdomain.converter.UserAggregateConverter;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventStoreEntity extends BaseEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date timeStamp;
    private String aggregateId;
    private String aggregateType;
    private Long version;
    @Column(length = 1000)
    @Convert(converter = UserAggregateConverter.class)
    private UserAggregate aggregateData;
    private String createdBy;
}
