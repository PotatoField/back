package com.tools.potato_field.repository;

import com.tools.potato_field.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // 회원 ID로 주문 목록 조회
    List<Order> findByMemberId(Long memberId);

    // 주문 상태로 주문 목록 조회
    List<Order> findByOrderStatus(String orderStatus);

    // 추가적인 쿼리 메서드들을 필요에 따라 정의할 수 있습니다.
}