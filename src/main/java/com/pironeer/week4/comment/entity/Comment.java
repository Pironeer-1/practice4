package com.pironeer.week4.comment.entity;

import com.pironeer.week4.comment.dto.request.CommentUpdateRequest;
import com.pironeer.week4.topic.entity.Topic;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
    @Column
    private String content;
    @Column
    @CreatedDate
    private LocalDateTime createdAt;
    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Comment(
            Long id,
            Topic topic,
            Comment parentComment,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.topic = topic;
        this.parentComment = parentComment;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment update(CommentUpdateRequest request) {
        this.content = request.content();
        return this;
    }
}
