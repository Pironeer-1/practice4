package com.pironeer.week4.comment.mapper;

import com.pironeer.week4.comment.dto.request.CommentCreateRequest;
import com.pironeer.week4.comment.entity.Comment;
import com.pironeer.week4.topic.entity.Topic;

import java.time.LocalDateTime;

public class CommentMapper {
    public static Comment from(CommentCreateRequest request, Topic topic, Comment parentComment) {
        LocalDateTime now = LocalDateTime.now();
        return Comment.builder()
                .topic(topic)
                .parentComment(parentComment)
                .content(request.content())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
