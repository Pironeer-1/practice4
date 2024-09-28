package com.pironeer.week4.comment.service;

import com.pironeer.week4.comment.dto.request.CommentCreateRequest;
import com.pironeer.week4.comment.dto.request.CommentUpdateRequest;
import com.pironeer.week4.comment.dto.response.CommentResponse;
import com.pironeer.week4.comment.mapper.CommentMapper;
import com.pironeer.week4.comment.repository.CommentRepository;
import com.pironeer.week4.comment.entity.Comment;
import com.pironeer.week4.comment.repository.CommentRepositoryV3;
import com.pironeer.week4.topic.entity.Topic;
import com.pironeer.week4.topic.repository.TopicRepositoryV3;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TopicRepositoryV3 topicRepository;
    private final CommentRepositoryV3 commentRepository;

    public void save(CommentCreateRequest request) {
        Topic topic = topicRepository.findById(request.topicId())
                .orElseThrow(() -> new RuntimeException("TOPIC NOT FOUND"));
        Comment parentComment = null;
        if (request.parentCommentId() != null) {
            parentComment = commentRepository.findById(request.parentCommentId())
                    .orElseThrow(() -> new RuntimeException("COMMENT NOT FOUND"));
        }
        commentRepository.save(CommentMapper.from(request, topic, parentComment));
    }

    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("COMMENT NOT FOUND"));
        return CommentResponse.of(comment);
    }

    public List<CommentResponse> findByTopicId(Long topicId) {
        // 부모 댓글 객체 조회
        List<Comment> comments = commentRepository.findParentByTopicId(topicId);
        List<CommentResponse> result = new CopyOnWriteArrayList<>();
        // 자식 댓글 객체 리스트 조회
        for (Comment comment : comments) {
            List<CommentResponse> child = findChild(comment.getId());
            CommentResponse commentResponse = CommentResponse.of(comment, child);
            result.add(commentResponse);
        }
        return result;
    }

    @Transactional
    public CommentResponse update(CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("COMMENT NOT FOUND"));
        commentRepository.save(comment.update(request));
        return CommentResponse.of(comment);
    }

    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteByParentId(id);
        commentRepository.deleteById(id);
    }

    private List<CommentResponse> findChild(Long parentId) {
        List<CommentResponse> result = new CopyOnWriteArrayList<>();
        List<Comment> comments = commentRepository.findByParentId(parentId);
        if (comments == null) {
            return null;
        }
        for (Comment comment : comments) {
            List<CommentResponse> child = findChild(comment.getId());
            result.add(CommentResponse.of(comment, child));
        }
        return result;
    }
}
