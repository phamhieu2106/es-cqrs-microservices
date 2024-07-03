package org.example.customercommand.repository;

import org.example.customerdomain.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    long countByAggregateId(String aggregateId);

    Optional<EventStoreEntity> findFirstByAggregateIdOrderByTimeStampDesc(String id);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO event_store_entity (id,aggregate_id, aggregate_type, aggregate_data, version, time_stamp, created_by) \
            VALUES (:id,:aggregateId, :aggregateType, :aggregateData, :version, :timeStamp, :createdBy)""", nativeQuery = true)
    void insertEvent(String id, String aggregateId, String aggregateType, String aggregateData, Long version, Date timeStamp, String createdBy);

}
