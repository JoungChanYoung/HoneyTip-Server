package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.jwt.JwtTokenProvider;
import bees.HoneyTip.repository.MemberRepository;
import bees.HoneyTip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MemberController {
    private MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @Autowired
    public MemberController(JwtTokenProvider jwtTokenProvider, MemberService memberService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
    }

    @GetMapping("/members") //회원 목록 페이지
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/join") //회원 가입 페이지
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/join") //회원 가입 페이지 Post
    public String create(MemberForm form) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.signUp(member);
        return "redirect:/members";
    }

    @GetMapping("/members/login") //로그인 페이지
    public String loginForm() {
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login") //로그인 페이지 Post
    public String login(MemberForm form, HttpServletResponse response) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.login(member);

        String token = jwtTokenProvider.createToken(member);
        System.out.println(token);
        response.setHeader("X-AUTH-TOKEN", token);
        //return jwtTokenProvider.createToken(member);
        return "redirect:/members";
    }
}
