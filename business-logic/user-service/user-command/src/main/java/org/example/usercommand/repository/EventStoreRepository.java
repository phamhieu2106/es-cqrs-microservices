package org.example.usercommand.repository;

import org.example.userdomain.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    long countByAggregateId(String aggregateId);

    Optional<EventStoreEntity> findFirstByAggregateIdOrderByTimeStampDesc(String aggregateId);
}
