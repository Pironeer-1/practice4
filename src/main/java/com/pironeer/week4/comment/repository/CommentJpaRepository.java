package com.pironeer.week4.comment.repository;

import com.pironeer.week4.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment c WHERE c.topic.id=:topicId AND c.parentComment.id IS NULL")
    List<Comment> findParentByTopicId(@Param("topicId") Long topicId);

    @Modifying
    @Query(value = "DELETE FROM Comment c WHERE c.parentComment.id=:id")
    void deleteByParentId(@Param("id") Long id);

    @Query(value = "SELECT c FROM Comment c WHERE c.parentComment.id=:parentId")
    List<Comment> findByParentId(@Param("parentId") Long parentId);

    @Modifying
    @Query(value = "DELETE FROM Comment c WHERE c.topic.id=:topicId")
    void deleteByTopicId(@Param("topicId") Long topicId);
}
