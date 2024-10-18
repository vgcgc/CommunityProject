package com.zerobase.communityproject.repository;

import com.zerobase.communityproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentByWriterIdAndPostIdAndCreatedAt(Long writerId, Long postId, LocalDateTime createdAt);

    List<Comment> findAllByPostId(Long postId);
}
