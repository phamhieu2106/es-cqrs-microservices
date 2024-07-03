package org.example.sharedlibrary.base;

public interface BaseConsumer<C,U,D> {

    void handleEvent(BaseEvent event);

    void handleCreateEvent(C event);

    void handleUpdateEvent(U event);

    void handleDeleteEvent(D event);
}
