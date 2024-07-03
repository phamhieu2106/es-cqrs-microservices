package org.example.customerdomain.converter;

import org.example.customerdomain.model.IdentityModel;
import org.example.sharedlibrary.base.BaseAggregateConverter;

public class IdentityModelConverter extends BaseAggregateConverter<IdentityModel> {

    public IdentityModelConverter() {
        super(IdentityModel.class);
    }
}
