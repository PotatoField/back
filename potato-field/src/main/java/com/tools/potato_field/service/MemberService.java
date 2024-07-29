package com.tools.potato_field.service;

import com.tools.potato_field.entity.Member;
import com.tools.potato_field.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        return memberRepository.save(member);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}

