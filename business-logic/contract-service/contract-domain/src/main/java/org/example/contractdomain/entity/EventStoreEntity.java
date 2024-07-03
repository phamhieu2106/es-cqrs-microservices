package org.example.contractdomain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.contractdomain.aggregate.ContractAggregate;
import org.example.contractdomain.converter.ContractAggregateConverter;
import org.example.sharedlibrary.base.BaseEvent;

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
    @Convert(converter = ContractAggregateConverter.class)
    private ContractAggregate aggregateData;
    private String createdBy;

}
