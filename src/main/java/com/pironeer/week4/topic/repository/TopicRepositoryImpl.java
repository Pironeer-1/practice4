package com.pironeer.week4.topic.repository;

import com.pironeer.week4.topic.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TopicRepositoryImpl implements TopicRepositoryV3 {
    private final TopicJpaRepositoryV3 topicJpaRepositoryV3;

    @Override
    public void save(Topic topic) {
        topicJpaRepositoryV3.save(topic);
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicJpaRepositoryV3.findById(id);
    }

    @Override
    public List<Topic> findAll() {
        return topicJpaRepositoryV3.findAll();
    }

    @Override
    public List<Topic> findByCursorId(Long cursorId) {
        return topicJpaRepositoryV3.findByCursorId(cursorId);
    }

    @Override
    public void deleteById(Long id) {
        topicJpaRepositoryV3.deleteById(id);
    }
}
