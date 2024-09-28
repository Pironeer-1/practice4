package com.pironeer.week4.comment.repository;

import com.pironeer.week4.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryV3 {
    // 여기는 Querydsl 을 사용하지 않는 코드로 작성하겠습니다.
    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Optional<Comment> findById(Long id) {
        return commentJpaRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public List<Comment> findParentByTopicId(Long topicId) {
        return commentJpaRepository.findParentByTopicId(topicId);
    }

    @Override
    public void deleteByParentId(Long id) {
        commentJpaRepository.deleteByParentId(id);
    }

    @Override
    public void deleteById(Long id) {
        commentJpaRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByParentId(Long parentId) {
        return commentJpaRepository.findByParentId(parentId);
    }

    @Override
    public void deleteByTopicId(Long topicId) {
        commentJpaRepository.deleteByTopicId(topicId);
    }
}
