package org.example.customerdomain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressModel {
    String houseNumber;
    String streetName;
    String wardName;
    String districtName;
    String city;
    String national;
}
