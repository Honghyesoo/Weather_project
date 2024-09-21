package zerobase.weather.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginDto {
    private String userId;
    private String password;
    private String token;
}
