package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Member;
import com.zerobase.communityproject.model.CustomUserDetails;
import com.zerobase.communityproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Member member = memberRepository.findById(id);

        if (member != null) {
            return new CustomUserDetails(member);
        }

        return null;
    }

}
