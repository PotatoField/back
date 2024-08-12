package com.tools.potato_field.member;

import com.tools.potato_field.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Member member = memberService.loginMember(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("MEMBER_ID", member.getId());
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutMember(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/me")
    public ResponseEntity<Member> getCurrentMember(HttpSession session) {
        Long memberId = (Long) session.getAttribute("MEMBER_ID");
        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Member member = memberService.findMember(memberId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("MEMBER_ID") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(memberService.findMember(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(HttpSession session) {
        if (session.getAttribute("MEMBER_ID") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("MEMBER_ID") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}