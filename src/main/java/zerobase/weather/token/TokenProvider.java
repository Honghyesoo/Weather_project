package zerobase.weather.token;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import zerobase.weather.service.MemberService;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private  static final long TOKEN_EXPIRE_TIME= 1000 * 60 * 60; //1시간
    private final MemberService memberService;

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

    public Authentication getAuthentication(String jwt){
        UserDetails userDetails = memberService.loadUserByUsername(this.getUsername(jwt));
        return  new UsernamePasswordAuthenticationToken(
                userDetails,"",userDetails.getAuthorities()
        );
    }

    public String getUsername(String token){
        return this.parseClaims(token).getSubject();
    }

    public Boolean validateToken(String token){
        if (!StringUtils.hasText(token))
            return false;
        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token){
        try{
            return  Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }

    }

}
