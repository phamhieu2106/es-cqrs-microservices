package org.example.insurancecommand.repository;

import org.example.insurancecommand.entity.EventStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStoreEntity, String> {
    Optional<EventStoreEntity> findFirstByAggregateIdOrderByTimeStampDesc(String aggregateId);

    long countByAggregateId(String aggregateId);


    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO event_store (id,aggregate_id, aggregate_type, aggregate_data, version, time_stamp, created_by) \
            VALUES (:id,:aggregateId, :aggregateType, :aggregateData, :version, :timeStamp, :createdBy)""", nativeQuery = true)
    void insertEvent(String id, String aggregateId, String aggregateType, String aggregateData, long version, Date timeStamp);
}
