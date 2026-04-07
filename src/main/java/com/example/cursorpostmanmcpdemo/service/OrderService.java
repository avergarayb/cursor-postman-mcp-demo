package com.example.cursorpostmanmcpdemo.service;

import com.example.cursorpostmanmcpdemo.dto.order.OrderRequestDto;
import com.example.cursorpostmanmcpdemo.dto.order.OrderResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Order;
import com.example.cursorpostmanmcpdemo.entity.User;
import com.example.cursorpostmanmcpdemo.exception.ResourceNotFoundException;
import com.example.cursorpostmanmcpdemo.mapper.OrderMapper;
import com.example.cursorpostmanmcpdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).toList();
    }

    public OrderResponseDto findById(Long id) {
        return orderMapper.toResponse(getOrder(id));
    }

    public List<OrderResponseDto> findByUserId(Long userId) {
        userService.getUser(userId);
        return orderRepository.findByUserId(userId).stream().map(orderMapper::toResponse).toList();
    }

    @Transactional
    public OrderResponseDto create(OrderRequestDto dto) {
        User user = userService.getUser(dto.getUserId());
        Order order = orderMapper.toEntity(dto);
        order.setUser(user);
        order.setDate(dto.getDate() != null ? dto.getDate() : LocalDateTime.now());
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDto update(Long id, OrderRequestDto dto) {
        Order order = getOrder(id);
        orderMapper.updateEntity(dto, order);
        if (dto.getUserId() != null) {
            order.setUser(userService.getUser(dto.getUserId()));
        }
        if (dto.getDate() != null) {
            order.setDate(dto.getDate());
        }
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Transactional
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw ResourceNotFoundException.forId("Pedido", id);
        }
        orderRepository.deleteById(id);
    }

    Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.forId("Pedido", id));
    }
}
