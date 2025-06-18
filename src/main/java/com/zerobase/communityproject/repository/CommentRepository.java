package com.zerobase.communityproject.repository;

import com.zerobase.communityproject.entity.Comment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Optional<Comment> findCommentByWriterIdAndPostIdAndCreatedDate(String writer_id, Long post_id,
      LocalDateTime createdDate);

  List<Comment> findAllByPostId(Long postId);
}
