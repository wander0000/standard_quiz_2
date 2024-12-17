package com.sparta.jpaquiz.repository;

import com.sparta.jpaquiz.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * TODO 3-1(페이징): 페이징 처리 시 Pageable, Page<T>을 사용하여 효율적으로 처리
     * ------------------------------
     * 조건: JPA 의 Page<T> 를 사용하여 페이징 처리를 진행해주세요.
     * 조건: 인자에는 Pageable 객체가 존재해야 합니다.
     */

    List<Order> findAll(); // 모든 데이터를 불러오는 비효율적인 메서드

}

