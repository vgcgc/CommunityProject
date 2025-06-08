package com.zerobase.communityproject.jwt;

import com.zerobase.communityproject.entity.Refresh;
import com.zerobase.communityproject.repository.RefreshRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;
  private final RefreshRepository refreshRepository;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {

    String username = obtainUsername(req);
    String password = obtainPassword(req);

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        username, password, null);

    return authenticationManager.authenticate(authToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain, Authentication authentication) {

    String username = authentication.getName();

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    String role = auth.getAuthority();

    // 토큰 생성
    String access = jwtUtil.createJwt("access", username, role, 600000L);
    String refresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

    // Refresh 토큰 저장
    addRefreshEntity(username, refresh, 86400000L);

    // 응답 설정
    res.setHeader("access", access);
    res.addCookie(createCookie("refresh", refresh));
    res.setStatus(HttpStatus.OK.value());
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      AuthenticationException failed) {
    res.setStatus(401);
  }

  private void addRefreshEntity(String username, String refresh, Long expiredMs) {

    Date date = new Date(System.currentTimeMillis() + expiredMs);

    Refresh refreshEntity = new Refresh();
    refreshEntity.setUsername(username);
    refreshEntity.setRefresh(refresh);
    refreshEntity.setExpiration(date.toString());

    refreshRepository.save(refreshEntity);
  }

  private Cookie createCookie(String key, String value) {

    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(24 * 60 * 60);
    cookie.setHttpOnly(true);

    return cookie;
  }
}
