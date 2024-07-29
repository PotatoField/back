package com.tools.potato_field.controller;

import com.tools.potato_field.entity.member;
import com.tools.potato_field.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member registerMember(@RequestBody Member member) {
        return memberService.registerMember(member);
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}