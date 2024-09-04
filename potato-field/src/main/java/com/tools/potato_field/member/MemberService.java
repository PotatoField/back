package com.tools.potato_field.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tools.potato_field.dto.ProfileDto;
import com.tools.potato_field.dto.AccountInfoDto;

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
        if (memberRepository.findByUserID(member.getUserID()).isPresent()) {
            throw new RuntimeException("User ID already exists");
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

    // 새로 추가된 메소드: 프로필 정보 조회
    public ProfileDto getProfile(String userID) {
        Member member = findByUserID(userID);
        ProfileDto profileDTO = new ProfileDto();
        profileDTO.setName(member.getName());
        profileDTO.setDescription(member.getDescription());
        profileDTO.setProfileImageUrl(member.getProfileImageUrl());
        profileDTO.setHeight(member.getHeight());
        profileDTO.setGender(member.getGender());
        profileDTO.setAge(member.getAge());
        profileDTO.setProfilePublic(member.isProfilePublic());
        profileDTO.setStatusMessage(member.getStatusMessage());
        return profileDTO;
    }

    // 새로 추가된 메소드: 계정 정보 조회
    public AccountInfoDto getAccountInfo(String userID) {
        Member member = findByUserID(userID);
        AccountInfoDto accountInfoDTO = new AccountInfoDto();
        accountInfoDTO.setUserID(member.getUserID());
        accountInfoDTO.setPhoneNumber(member.getNumber());
        accountInfoDTO.setEmail(member.getEmail());
        return accountInfoDTO;
    }

    // 새로 추가된 메소드: 프로필 정보 업데이트
    public void updateProfile(String userID, ProfileDto profileDTO) {
        Member member = findByUserID(userID);
        member.setName(profileDTO.getName());
        member.setDescription(profileDTO.getDescription());
        member.setProfileImageUrl(profileDTO.getProfileImageUrl());
        member.setHeight(profileDTO.getHeight());
        member.setGender(profileDTO.getGender());
        member.setAge(profileDTO.getAge());
        member.setProfilePublic(profileDTO.isProfilePublic());
        member.setStatusMessage(profileDTO.getStatusMessage());
        memberRepository.save(member);
    }

    // 새로 추가된 메소드: 프로필 공개 설정 업데이트
    public void updatePrivacySettings(String userID, boolean isProfilePublic) {
        Member member = findByUserID(userID);
        member.setProfilePublic(isProfilePublic);
        memberRepository.save(member);
    }
}