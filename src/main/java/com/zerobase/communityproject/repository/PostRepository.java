package com.zerobase.communityproject.repository;

import com.zerobase.communityproject.entity.Post;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  Page<Post> findFirstByOrderByIdDesc(Pageable pageable);

  Boolean existsByTitleAndWriterId(String title, Long writerId);

  void deleteByTitleAndWriter(String title, String writer);

  Optional<Post> findByTitleAndWriterId(String title, Long writerIdx);

  Page<Post> findAllByWriterId(Long writerIdx, Pageable pageable);

  Page<Post> findAllByTitle(String title, Pageable pageable);
}
