package bees.HoneyTip.service;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        //memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //@AfterEach
    //public void afterEach() {
    //    memberRepository.clearStore();
    //}

    @Test
    public void signUp() {
        Member member = new Member();
        member.setEmail("cyjoungg@gmail.com");

        Long saveId = memberService.signUp(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
        System.out.println("member Email = " + member.getEmail());
    }

    @Test
    public void signUp_실패_중복Email() {
        Member member1 = new Member();
        member1.setEmail("cyjoungg@gmail.com");
        Member member2 = new Member();
        member2.setEmail("cyjoungg@gmail.com");

        memberService.signUp(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.signUp(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 Email 입니다.");
    }

    @Test
    public void deleteAccount() {
        Member member = new Member();
        member.setEmail("cyjoungg@gmail.com");
        member.setPassword("123123");

        memberService.signUp(member);
        memberService.deleteAccount(member);

        //assertThat(memberRepository.findByEmail("cyjoungg@gmail.com")).isEmpty();
    }

    @Test
    public void login() {
        Member member = new Member();
        member.setEmail("cyjoungg@gmail.com");
        member.setPassword("123123");

        memberService.signUp(member);
        Member loginMember = memberService.login(member);

        assertThat(loginMember.getId()).isEqualTo(member.getId());
    }
    @Test
    public void login_실패_패스워드불일치() {
        Member member = new Member();
        member.setEmail("cyjoungg@gmail.com");
        member.setPassword("123123");
        memberService.signUp(member);

        Member member2 = new Member();
        member2.setEmail("cyjoungg@gmail.com");
        member2.setPassword("321321");

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.login(member2));
        assertThat(e.getMessage()).isEqualTo("로그인에 실패했습니다. Email 또는 Password를 확인해주세요.");
    }

    @Test
    public void modify_이메일패스워드() {
        Member member = new Member();
        member.setEmail("cyjoungg@gmail.com");
        member.setPassword("123123");
        memberService.signUp(member); //회원가입

        String beforeModifyEmail = memberRepository.findById(member.getId()).get().getEmail(); //현재 회원 Email 출력
        String beforeModifyPassword = memberRepository.findById(member.getId()).get().getPassword(); //현재 회원 Email 출력

        member.setEmail("chanyoung.joung@gmail.com");
        member.setPassword("111111");
        Long modifyId = memberService.modify(member);

        //회원 정보 수정. modify 이후 Email, Password 달라짐.
        assertThat(memberRepository.findById(modifyId).get().getEmail()).isNotEqualTo(beforeModifyEmail);
        assertThat(memberRepository.findById(modifyId).get().getPassword()).isNotEqualTo(beforeModifyPassword);
    }
}
