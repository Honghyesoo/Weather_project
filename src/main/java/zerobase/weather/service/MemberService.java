package zerobase.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zerobase.weather.domain.Member;
import zerobase.weather.dto.MemberDto;
import zerobase.weather.dto.MemberLoginDto;
import zerobase.weather.exception.MemberException;
import zerobase.weather.mapper.MemberMapper;
import zerobase.weather.repository.MemberRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + userId));
    }

    // 이미 존재하는 회원 체크
    public MemberDto createMember(MemberDto dto){
         memberRepository.findByUserId(dto.getUserId())
                 .ifPresent((member -> {
                     throw  new MemberException("아이디가 존재합니다");
                 }));

        // 새로운 회원 생성
        Member member = new Member();
        member.setUserId(dto.getUserId());
        member.setPassword(dto.getPassword());
        member.setPhNumber(dto.getPhNumber());
        Member entity = memberRepository.save(member);

        // 저장된 회원 엔티티를 DTO로 변환
        return MemberMapper.toDto(entity);
    }
    public void deleteMember(Long id, String password) {
        int deletedCount = memberRepository.deleteMemberByIdAndPassword(id, password);
        if (deletedCount == 0) {
            throw new RuntimeException("회원 삭제에 실패했습니다. ID 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 로그인
    public MemberLoginDto signin( MemberLoginDto dto) {
        Member user = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(()-> new RuntimeException("존재하지 않은 ID 입니다."));
        if (!Objects.equals(user.getPassword(), dto.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return MemberLoginDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .build();
    }
}
