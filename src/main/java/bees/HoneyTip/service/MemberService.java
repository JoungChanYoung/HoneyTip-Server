package bees.HoneyTip.service;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    //회원 가입
    public Long signUp(Member member){
        // 같은 ID 중복X
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 Email 입니다.");
        });
        //member 객체 DB에 저장
        memberRepository.save(member);
        return member.getId();
    }

    //계정 삭제
    public void deleteAccount(Member member){
        //member 객체 DB에서 삭제
        memberRepository.delete(member);
    }

    //회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //로그인
    public Member login(Member member) {
        //session or cookie 사용해서 처리하는 부분 추가 필요
        //Email, Password 일치하지 않으면 예외처리
        Optional<Member> result = memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword());
        result.orElseThrow(() -> new IllegalStateException("로그인에 실패했습니다. Email 또는 Password를 확인해주세요."));

        return result.get();
    }
    //로그아웃
    public void logout(Member member) {
        //session or cookie 사용해서 처리하는 부분 추가 필요
    }
    //로그인 체크
    public boolean loginCheck(Member member) {
        //session or cookie 사용해서 처리하는 부분 추가 필요
        return true;
    }
}
