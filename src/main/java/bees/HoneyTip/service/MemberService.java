package bees.HoneyTip.service;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.repository.MemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    /**
     * 회원 가입
     */
    public String join(Member member){
        return member.getId();
    }
}
