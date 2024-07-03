package org.example.customerdomain.converter;

import org.example.customerdomain.aggregate.CustomerAggregate;
import org.example.sharedlibrary.base.BaseAggregateConverter;

public class CustomerAggregateConverter extends BaseAggregateConverter<CustomerAggregate> {

    public CustomerAggregateConverter() {
        super(CustomerAggregate.class);
    }
}
