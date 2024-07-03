package org.example.userdomain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.sharedlibrary.enumerate.Role;
import org.example.sharedlibrary.enumerate.UserRole;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    String id;
    String userCode;
    String username;
    String password;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
    @Enumerated(EnumType.STRING)
    Role role = Role.ADMIN;
    Boolean softDelete = false;
    Date createdAt;
    
}
