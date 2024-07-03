package org.example.userdomain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseCommand;
import org.example.sharedlibrary.enumerate.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserCommand extends BaseCommand {
    String userId;
    String password;
    UserRole userRole;
    String createdBy;
}
