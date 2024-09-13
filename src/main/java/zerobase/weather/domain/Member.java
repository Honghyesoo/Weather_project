package zerobase.weather.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerobase.weather.constant.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Member")
public class Member { // 회원가입
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String phNumber;

    @Enumerated(EnumType.STRING)
    private Role role;
}
