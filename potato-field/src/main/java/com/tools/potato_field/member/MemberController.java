package com.tools.potato_field.controller;

import com.tools.potato_field.entity.Member;
import com.tools.potato_field.service.MemberService;
import com.tools.potato_field.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.registerMember(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findMember(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Member> loginMember(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(memberService.loginMember(loginRequest.getEmail(), loginRequest.getPassword()));
    }
}