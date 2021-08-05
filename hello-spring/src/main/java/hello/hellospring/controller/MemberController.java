package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// Controller라는 annotation이 있으면, 스프링 컨테이너에 객체 하나를 딱 만들어서 가지고 있는 상황이 된다.
// 이걸, 컨테이너에서 스프링 빈이 관리된다고 표현한다
// 그림에서 보면, 컨테이너 내부에 초록색 동그라미가 스프링 빈!!(콩)
public class MemberController {
    // 객체 여러개를 new로 생성하는 것이 아니라, 하나의 객체를 여러번 써야하므로 autowired 사용한다.
    private final MemberService memberService;

    @Autowired
    // 스프링 컨테이너에서 객체 하나를 가져오는 것.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
