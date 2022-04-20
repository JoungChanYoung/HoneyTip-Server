package bees.HoneyTip.jwt;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String secretKey = "secret";

    private long tokenValidTime = 1000L * 60 * 60;

    protected JwtTokenProvider() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(Member member) {
        Claims claims = Jwts.claims().setSubject(member.getEmail()); //payload info
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set expiration
                .signWith(SignatureAlgorithm.HS256, secretKey)  // set Algorithm, secret-key
                .compact();
    }
}
