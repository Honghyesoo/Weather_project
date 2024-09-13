package zerobase.weather.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String userId;
    private String password;
    private String phNumber;
}
