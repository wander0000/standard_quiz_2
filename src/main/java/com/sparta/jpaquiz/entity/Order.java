package com.sparta.jpaquiz.entity;

import com.sparta.jpaquiz.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "P_ORDER")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private LocalDateTime createdAt;

    private String status;

    /**
     * TODO 2(Entity 생성자 기본값 설정): @PrePersist를 사용해 createdAt, status 필드에 기본값을 설정합니다.
     * <hr/>
     * Entity 가 생성되는 시점에 createdAt, status 를 설정해주세요!<br/><br/>
     * 조건: createdAt은 현재시간(LocalDateTime.now()) 으로 설정<br/>
     * 조건: status 는 "PENDING" 으로 설정<br/>
     * @return @PrePersist 어노테이션을 사용하여 객체가 생성되는 시점에 필드에 값을 추가합니다.
     */

    public void prePersist() {
        ...
    }

    /**
     * TODO 5: Entity 가 Dto 에 의존하는 '의존성 역전 원칙 위반'을 수정
     * <hr>
     * 현재 고수준 모듈인 Entity 가 저수준 모듈인 {@link OrderDto} 에 영향을 받는 구조로 설계되어 있습니다.<br/>
     * Layered Architecture 의 계층에 따라 고수준 모듈을 보호하는 설계로 변경해주세요!<br/>
     * <a href="https://github.com/user-attachments/assets/c71a5429-0481-49d0-a125-95f10f27db16">HINT: 계층 분리 참고 자료</a>
     * @param orderDto {@link OrderDto} 주문 생성 요청 객체 입니다.
     *
     * @return 고수준 모듈의 Entity 가 저수준 모듈의 수정에 영향이 없도록 변경합니다.
     */
    public void setOrderNumberFromOrderDto(OrderDto orderDto) {
        this.orderNumber = orderDto.getOrderNumber();
    }
}

