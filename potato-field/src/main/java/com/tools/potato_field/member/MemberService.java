package com.tools.potato_field.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member findByUserID(String username) {
        return memberRepository.findByUserID(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public Member registerMember(Member member) {
        if (memberRepository.findByUserID
                (member.getUserID()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }
    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member loginMember(String userID, String password) {
        Member member = memberRepository.findByUserID(userID)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return member;
    }
}