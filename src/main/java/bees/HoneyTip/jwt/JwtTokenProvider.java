package bees.HoneyTip.jwt;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.repository.MemberRepository;
import bees.HoneyTip.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String secretKey = "honeytipJwtKey";

    private long tokenValidTime = 1000L * 60 * 30;

    MemberService memberService;

    protected JwtTokenProvider() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Member member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail()); //payload info
        claims.put("roles", member.getRoles());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set expiration
                .signWith(SignatureAlgorithm.HS256, secretKey)  // set Algorithm, secret-key
                .compact();
    }
    // token에 저장된 회원 Email 추출
    public String getMemberEmailByToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // token 유효한지 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    // token에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = memberService.loadUserByUsername(this.getMemberEmailByToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
