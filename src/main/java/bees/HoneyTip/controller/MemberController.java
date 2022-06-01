package bees.HoneyTip.controller;

import bees.HoneyTip.domain.Member;
import bees.HoneyTip.jwt.JwtTokenProvider;
import bees.HoneyTip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {
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
        member.setRoles(Collections.singletonList("ROLE_USER"));
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

        Member loginMember = memberService.login(member);
        System.out.println("member roles:" + loginMember.getRoles());

        String token = jwtTokenProvider.createToken(loginMember);
        System.out.println(token);
        //response.setHeader("X-AUTH-TOKEN", token);
        String emailByToken = jwtTokenProvider.getMemberEmailByToken(token);
        System.out.println(emailByToken);
        return "redirect:/members";
    }

    @PostMapping("/user/test")
    @ResponseBody
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "user ok");
        return result;
    }
}
