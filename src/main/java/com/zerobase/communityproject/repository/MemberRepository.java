package com.zerobase.communityproject.repository;

import com.zerobase.communityproject.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Boolean existsById(String id);

  Optional<Member> findById(String id);

  void deleteMemberById(String id);
}
