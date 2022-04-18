package bees.HoneyTip;

import bees.HoneyTip.jwt.JwtTokenProvider;
import bees.HoneyTip.repository.MemberRepository;
import bees.HoneyTip.repository.MemoryMemberRepository;
import bees.HoneyTip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

}
