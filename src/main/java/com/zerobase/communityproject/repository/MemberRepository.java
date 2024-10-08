package com.zerobase.communityproject.repository;

import com.zerobase.communityproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsById(String id);

    Member findById(String id);
}
