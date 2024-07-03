package org.example.customerdomain.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.customerdomain.enumeration.IdentityType;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdentityModel {
    @Enumerated(EnumType.STRING)
    IdentityType typeIdentity;
    String numberIdentity;
}
