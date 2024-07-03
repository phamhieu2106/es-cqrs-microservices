package org.example.insurancecommand.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.insurancedomain.aggregate.InsuranceAggregate;
import org.example.insurancedomain.converter.InsuranceAggregateConverter;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date timeStamp;
    private String aggregateId;
    private String aggregateType;
    private Long version;

    @Column(length = 1000)
    @Convert(converter = InsuranceAggregateConverter.class)
    private InsuranceAggregate aggregateData;
}
