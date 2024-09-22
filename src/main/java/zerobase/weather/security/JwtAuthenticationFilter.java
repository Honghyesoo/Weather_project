package zerobase.weather.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import zerobase.weather.token.TokenProvider;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("요청이 들어옴: {} {}", request.getMethod(), request.getRequestURI());

        String token = resolveTokenFromRequest(request);

        if (StringUtils.hasText(token)) {
            log.info("토큰이 존재함: {}", token);
        } else {
            log.warn("토큰이 존재하지 않음");
        }

        // 토큰 유효성 검증
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            log.info("토큰이 유효함");
            Authentication auth = tokenProvider.getAuthentication(token);
            log.info("인증 정보 설정: {}", auth.getName());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            log.warn("토큰이 유효하지 않거나 없음");
        }

        filterChain.doFilter(request, response);
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            log.info("헤더에 토큰 존재: {}", token);
            return token.substring(TOKEN_PREFIX.length());
        } else {
            log.warn("헤더에 토큰이 없거나 형식이 잘못됨");
        }
        return null;
    }
}
