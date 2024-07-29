package com.tools.potato_field.repository;

import com.tools.potato_field.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
