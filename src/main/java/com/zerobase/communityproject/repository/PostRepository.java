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

  Boolean existsByTitleAndMember_Idx(String title, Long writer);

  void deleteByTitleAndMember_Idx(String title, Long writer);

  Optional<Post> findByTitleAndMember_Idx(String title, Long writer);

  Page<Post> findAllByMember_Id(String member_id, Pageable pageable);

  Page<Post> findAllByTitle(String title, Pageable pageable);
}
