package org.example.sharedlibrary.base;

public interface BaseAggregateApply<C extends BaseCommand, U extends BaseCommand, D extends BaseCommand> {

    <E extends BaseEvent> E applyCreate(C command);

    <E extends BaseEvent> E applyUpdate(U command);

    <E extends BaseEvent> E applyDelete(D command);

}