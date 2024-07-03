package org.example.contractcommand.repository;

import org.example.contractdomain.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    long countByAggregateId(String aggregateId);

    Optional<EventStoreEntity> findFirstByAggregateIdOrderByTimeStampDesc(String id);

}
