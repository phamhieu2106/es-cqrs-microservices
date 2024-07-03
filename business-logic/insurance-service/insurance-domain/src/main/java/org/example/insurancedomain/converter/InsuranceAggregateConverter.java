package org.example.insurancedomain.converter;

import org.example.insurancedomain.aggregate.InsuranceAggregate;
import org.example.sharedlibrary.base.BaseAggregateConverter;

public class InsuranceAggregateConverter extends BaseAggregateConverter<InsuranceAggregate> {

    public InsuranceAggregateConverter() {
        super(InsuranceAggregate.class);
    }
}
