package bees.HoneyTip.service;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.domain.Post;
import bees.HoneyTip.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    //회원 가입
    public Long signUp(Member member){
        // 같은 ID 중복X
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 Email 입니다.");
        });

        // 회원 가입한 날짜, 시간 저장
        LocalDateTime now = LocalDateTime.now();
        member.setCreateDate(now);

        //member 객체 DB에 저장
        memberRepository.save(member);
        return member.getId();
    }

    //계정 삭제
    public void deleteAccount(Member member){
        //member 객체 DB에서 삭제
        memberRepository.delete(member);
    }

    //Id로 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
    //모든 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //로그인
    public Member login(Member member) {
        //Email, Password 일치하지 않으면 예외처리
        Optional<Member> result = memberRepository.findByEmailAndPassword(member.getEmail(), member.getPassword());

        result.orElseThrow(() -> new IllegalStateException("로그인에 실패했습니다. Email 또는 Password를 확인해주세요."));

        return result.get();
    }
    //로그아웃
    public void logout(Member member) {
        //JWT 사용해서 처리하는 부분 추가 필요
    }
    //로그인 체크
    public boolean loginCheck(Member member) {
        //JWT 사용해서 처리하는 부분 추가 필요
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    //회원 정보 수정
    public Long modify(Member member) {
        Member modifiedMember = memberRepository.findById(member.getId()).get();

        modifiedMember.setEmail(member.getEmail());
        modifiedMember.setPassword(member.getPassword());

        return member.getId();
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return memberRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//    }
}
