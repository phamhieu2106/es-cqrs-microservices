package org.example.sharedlibrary;

public interface BaseEventHandler<C, U, D> {
    void handleCreate(C command);

    void handleUpdate(U command);

    void handleDelete(D command);
}
