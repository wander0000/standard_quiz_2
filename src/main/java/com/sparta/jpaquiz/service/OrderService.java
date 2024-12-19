package com.sparta.jpaquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.jpaquiz.dto.OrderDto;
import com.sparta.jpaquiz.entity.Order;
import com.sparta.jpaquiz.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order createOrder(OrderDto orderDto) {
		Order order = new Order();
		order.setOrderNumberFromOrderDto(orderDto.getOrderNumber());
		return orderRepository.save(order);
	}

	/**
	 * TODO 6: save()를 호출하지 말고, 더티 체킹에 의해 변경 사항이 자동 반영되도록 설정
	 * <hr>
	 * HINT: {@link Transactional}<br/>
	 * 더티체킹을 활용하면 코드가 간결해지고, 불필요한 I/O를 최고화하며, 트랜잭션 범위에서 일관된 동작 보장 <br/>
	 *
	 * @param orderId 주문 ID 입니다.
	 * @param status  주문의 상태 입니다.
	 * @return repository.save 를 사용하지 않고 영속성 컨텍스트의 Dirty Checking 을 이용하여 엔티티를 저장합니다.
	 */

	//     HINT:  @Transactional
	//     비고:  더티체킹을 활용하면 코드가 간결해지고, 불필요한 I/O를 최고화하며, 트랜잭션 범위에서 일관된 동작 보장
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		order.setStatus(status);
	}

	/**
	 * TODO 3-2(페이징): 페이징 처리 시 Pageable, Page<T>을 사용하여 효율적으로 처리
	 * <hr>
	 * List 객체의 형태로 리턴되고 있는 것을 {@link Page} 로 변경해주세요.
	 *
	 * @param pageable 페이지네이션과 정렬 객체입니다.
	 * @return JPA 의 {@link Page} 클래스를 사용하여 페이지네이션을 적용한 {@link Order}객체를 리턴합니다. <br/>
	 */

	public Page<Order> getAllOrders(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}
}

