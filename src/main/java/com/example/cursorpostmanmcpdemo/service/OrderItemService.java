package com.example.cursorpostmanmcpdemo.service;

import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemRequestDto;
import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Order;
import com.example.cursorpostmanmcpdemo.entity.OrderItem;
import com.example.cursorpostmanmcpdemo.entity.Product;
import com.example.cursorpostmanmcpdemo.exception.ResourceNotFoundException;
import com.example.cursorpostmanmcpdemo.mapper.OrderItemMapper;
import com.example.cursorpostmanmcpdemo.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;
    private final ProductService productService;

    public List<OrderItemResponseDto> findAll() {
        return orderItemRepository.findAll().stream().map(orderItemMapper::toResponse).toList();
    }

    public OrderItemResponseDto findById(Long id) {
        return orderItemMapper.toResponse(getOrderItem(id));
    }

    public List<OrderItemResponseDto> findByOrderId(Long orderId) {
        orderService.getOrder(orderId);
        return orderItemRepository.findByOrderId(orderId).stream().map(orderItemMapper::toResponse).toList();
    }

    @Transactional
    public OrderItemResponseDto create(OrderItemRequestDto dto) {
        Order order = orderService.getOrder(dto.getOrderId());
        Product product = productService.getProduct(dto.getProductId());
        OrderItem item = orderItemMapper.toEntity(dto);
        item.setOrder(order);
        item.setProduct(product);
        return orderItemMapper.toResponse(orderItemRepository.save(item));
    }

    @Transactional
    public OrderItemResponseDto update(Long id, OrderItemRequestDto dto) {
        OrderItem item = getOrderItem(id);
        orderItemMapper.updateEntity(dto, item);
        if (dto.getOrderId() != null) {
            item.setOrder(orderService.getOrder(dto.getOrderId()));
        }
        if (dto.getProductId() != null) {
            item.setProduct(productService.getProduct(dto.getProductId()));
        }
        return orderItemMapper.toResponse(orderItemRepository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw ResourceNotFoundException.forId("Línea de pedido", id);
        }
        orderItemRepository.deleteById(id);
    }

    OrderItem getOrderItem(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.forId("Línea de pedido", id));
    }
}
