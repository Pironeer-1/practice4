package com.pironeer.week4.topic.repository;

import com.pironeer.week4.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicJpaRepositoryV1 extends JpaRepository<Topic, Long> {
    @Query(value = "SELECT * FROM topic WHERE id >= :cursorId", nativeQuery = true)
    List<Topic> findByCursorId(@Param("cursorId") Long cursorId);
}
