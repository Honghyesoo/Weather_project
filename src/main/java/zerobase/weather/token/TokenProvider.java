package zerobase.weather.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private  static final long TOKEN_EXPIRE_TIME= 1000 * 60 * 60; //1시간

    private Key secretKey; // HS512를 위한 충분히 강력한 키 생성

    @PostConstruct // 주로 초기화작업을 수행하는 어노테이션
    public void init(){
        // 안전하고 충분히 긴 키 생성
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        System.out.println("Generated Secret Key: " + secretKey);
    }

    public String generateToken(String userId){
        return Jwts.builder()
                .claim("userId",userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();
    }



}
