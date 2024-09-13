package zerobase.weather.mapper;

import zerobase.weather.domain.Member;
import zerobase.weather.dto.MemberDto;

public class MemberMapper {
    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .userId(member.getUserId())
                .password(member.getPassword())
                .phNumber(member.getPhNumber())
                .build();
    }
}
