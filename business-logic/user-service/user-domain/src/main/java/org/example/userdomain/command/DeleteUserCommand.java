package org.example.userdomain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.sharedlibrary.base.BaseCommand;

@Getter
@Setter
@AllArgsConstructor
public class DeleteUserCommand extends BaseCommand {
    String userId;
    String createdBy;

    public DeleteUserCommand(String userId) {
        this.userId = userId;
    }
}
