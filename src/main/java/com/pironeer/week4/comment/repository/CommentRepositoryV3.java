package com.pironeer.week4.comment.repository;

import com.pironeer.week4.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryV3 {
    Optional<Comment> findById(Long id);

    void save(Comment comment);

    List<Comment> findParentByTopicId(Long topicId);

    void deleteByParentId(Long id);

    void deleteById(Long id);

    List<Comment> findByParentId(Long parentId);

    void deleteByTopicId(Long id);
}
