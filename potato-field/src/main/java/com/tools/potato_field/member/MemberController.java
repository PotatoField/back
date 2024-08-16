package com.tools.potato_field.member;

import com.tools.potato_field.dto.LoginRequest;
import com.tools.potato_field.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @Autowired
    public MemberController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.registerMember(member));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody LoginRequest loginRequest) {
        Member member = memberService.loginMember(loginRequest.getUserID(), loginRequest.getPassword());
        String token = jwtUtil.generateToken(member.getUserID());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutMember() {
        // JWT는 서버 측에서 세션을 유지하지 않으므로, 클라이언트에서 토큰을 삭제하도록 안내
        return ResponseEntity.ok("Logout successful. Please remove the token from the client side.");
    }

    @GetMapping("/me")
    public ResponseEntity<Member> getCurrentMember(Authentication authentication) {
        String username = authentication.getName();
        Member member = memberService.findByUserID

(username);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id, Authentication authentication) {
        // 인증된 사용자만 접근 가능
        return ResponseEntity.ok(memberService.findMember(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(Authentication authentication) {
        // 인증된 사용자만 접근 가능
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id, Authentication authentication) {
        // 인증된 사용자만 접근 가능
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}