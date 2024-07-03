package org.example.userdomain.converter;

import org.example.sharedlibrary.base.BaseAggregateConverter;
import org.example.userdomain.aggregate.UserAggregate;

public class UserAggregateConverter extends BaseAggregateConverter<UserAggregate> {

    public UserAggregateConverter() {
        super(UserAggregate.class);
    }
}
