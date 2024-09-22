package zerobase.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.dto.MemberDto;
import zerobase.weather.dto.MemberLoginDto;
import zerobase.weather.dto.PasswordDto;
import zerobase.weather.service.MemberService;
import zerobase.weather.token.TokenProvider;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/create/member")
    public ResponseEntity<?> createMember(@RequestBody MemberDto dto){
        System.out.println(dto);
        memberService.createMember(dto);
        return ResponseEntity.ok("회원가입이 성공했습니다.");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin (@RequestBody MemberLoginDto dto){
        MemberLoginDto result = memberService.signin(dto);
        // 토큰 생성
        String token = tokenProvider.generateToken(dto.getUserId());
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/delete/member")
    public ResponseEntity<?> deleteMember(@RequestParam(value = "id") Long id,
                             @RequestBody PasswordDto passwordDto) {
        memberService.deleteMember(id, passwordDto.getPassword());
        return ResponseEntity.ok("회원 삭제 완료.");
    }

    //관리자만 볼 수 있는 회원 목록
    @GetMapping("/read/member")
    public ResponseEntity<?> readMember(){
        return null;
    }
}
