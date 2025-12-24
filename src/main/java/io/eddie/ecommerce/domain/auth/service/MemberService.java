package io.eddie.ecommerce.domain.auth.service;

import io.eddie.ecommerce.domain.auth.mapper.AuthMapper;
import io.eddie.ecommerce.domain.auth.model.dto.MemberSaveRequest;
import io.eddie.ecommerce.domain.auth.model.entity.Member;
import io.eddie.ecommerce.domain.auth.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member save(MemberSaveRequest request) {

        String password = request.password();
        String encode = passwordEncoder.encode(password);

        Member member = Member.builder()
                .username(request.username())
                .password(encode)
                .email(request.email())
                .build();
        return memberJpaRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberJpaRepository.findByUsername(username);

        Member foundMember = memberOptional.orElseThrow(
                () -> new UsernameNotFoundException("Member not found")
        );

        return AuthMapper.toDetails(foundMember);
    }

}
