package org.example.contractdomain.converter;

import org.example.contractdomain.aggregate.ContractAggregate;
import org.example.sharedlibrary.base.BaseAggregateConverter;

public class ContractAggregateConverter extends BaseAggregateConverter<ContractAggregate> {

    public ContractAggregateConverter() {
        super(ContractAggregate.class);
    }
}
