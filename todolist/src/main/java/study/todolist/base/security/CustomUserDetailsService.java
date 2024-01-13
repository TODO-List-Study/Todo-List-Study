package study.todolist.base.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.todolist.domain.member.dto.MemberDto;
import study.todolist.domain.member.entity.Member;
import study.todolist.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        MemberDto dto = MemberDto.builder().email(member.getEmail())
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getGrantedAuthorities()).build();
        return new CustomUserDetails(dto);
    }
}
