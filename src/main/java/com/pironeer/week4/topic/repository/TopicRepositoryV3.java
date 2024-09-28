package com.pironeer.week4.topic.repository;

import com.pironeer.week4.topic.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicRepositoryV3 {
    void save(Topic topic);

    Optional<Topic> findById(Long id);

    List<Topic> findAll();

    List<Topic> findByCursorId(Long cursorId);

    void deleteById(Long id);
}
