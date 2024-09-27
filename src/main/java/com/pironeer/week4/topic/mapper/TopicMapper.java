package com.pironeer.week4.topic.mapper;

import com.pironeer.week4.topic.dto.request.TopicCreateRequest;
import com.pironeer.week4.topic.entity.Topic;

import java.time.LocalDateTime;

public class TopicMapper {
    public static Topic from(TopicCreateRequest request) {
        LocalDateTime now = LocalDateTime.now();
        return Topic.builder()
                .title(request.title())
                .content(request.content())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
