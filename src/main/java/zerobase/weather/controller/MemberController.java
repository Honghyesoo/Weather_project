package zerobase.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.dto.MemberDto;
import zerobase.weather.dto.PasswordDto;
import zerobase.weather.service.MemberSerivce;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberSerivce memberSerivce;

    @PostMapping("/create/member")
    public void createMember(@RequestBody MemberDto dto){
        System.out.println(dto);
        memberSerivce.createMember(dto);
    }

    @DeleteMapping("/delete/member")
    public void deleteMember(@RequestParam(value = "id") Long id,
                             @RequestBody PasswordDto passwordDto) {
        memberSerivce.deleteMember(id, passwordDto.getPassword());
    }

    //관리자만 볼 수 있는 회원 목록
    @GetMapping("/read/member")
    public void readMember(){

    }
}
