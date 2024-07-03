package org.example.customerdomain.converter;

import org.example.customerdomain.model.AddressModel;
import org.example.sharedlibrary.base.BaseAggregateConverter;

public class AddressModelConverter extends BaseAggregateConverter<AddressModel> {

    public AddressModelConverter() {
        super(AddressModel.class);
    }
}
