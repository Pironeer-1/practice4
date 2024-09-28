package com.pironeer.week4.topic.repository;

import com.pironeer.week4.topic.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicJpaRepositoryV1 extends JpaRepository<Topic, Long> {
}
