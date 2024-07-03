package org.example.userdomain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.sharedlibrary.base.BaseCommand;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateUserCommand extends BaseCommand {
    String username;
    String password;
    String createdBy;
}
