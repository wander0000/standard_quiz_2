package com.sparta.jpaquiz.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.jpaquiz.dto.OrderDto;
import com.sparta.jpaquiz.entity.Order;
import com.sparta.jpaquiz.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	// 주문 생성
	@PostMapping
	public String createOrder(@RequestBody OrderDto orderDto) {
		orderService.createOrder(orderDto);
		return "Order created successfully!";
	}

	// 주문 상태 업데이트
	@PutMapping("/{id}/status")
	public String updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
		orderService.updateOrderStatus(id, status);
		return "Order status updated!";
	}

	// 페이징을 통한 주문 조회

	/**
	 * TODO 4(페이징): @PageableDefault를 사용해 페이지 사이즈 및 정렬 기준 기본값 설정
	 * <hr/>
	 * 조건: 페이지네이션의 Default 값은 아래와 같이 설정합니다. <br/>
	 * <b>size</b> = 10</b><br/> <b>sort</b> = "id"<br/> <b>direction</b> = Sort.Direction.ASC<br/>
	 *
	 * @param pageable 페이지네이션 요청 객체입니다. 내부에는 page, size, sort 값이 있습니다.
	 * @return JPA의 페이지네이션이 적용된 {@link Order} 객체를 리턴합니다.
	 */
	@GetMapping

	public Page<Order> getAllOrders(
		@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return orderService.getAllOrders(pageable);
	}
}

