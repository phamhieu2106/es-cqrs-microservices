package org.example.sharedlibrary;

import lombok.Data;
import org.example.sharedlibrary.enumerate.Role;
import org.example.sharedlibrary.enumerate.UserRole;

import java.util.Date;

@Data
public class UserModel {
    String id;
    String userCode;
    String username;
    String password;
    UserRole userRole;
    Role role = Role.ADMIN;
    Boolean softDelete = false;
    Date createdAt;
}
