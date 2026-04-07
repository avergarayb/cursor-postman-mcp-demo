package com.example.cursorpostmanmcpdemo.controller;

import com.example.cursorpostmanmcpdemo.dto.order.OrderRequestDto;
import com.example.cursorpostmanmcpdemo.dto.order.OrderResponseDto;
import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import com.example.cursorpostmanmcpdemo.service.OrderItemService;
import com.example.cursorpostmanmcpdemo.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Órdenes de compra y sus líneas (subrecurso items)")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderResponseDto> list() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDto get(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDto> listItems(@PathVariable Long orderId) {
        return orderItemService.findByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@Valid @RequestBody OrderRequestDto dto) {
        OrderResponseDto created = orderService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public OrderResponseDto replace(@PathVariable Long id, @Valid @RequestBody OrderRequestDto dto) {
        return orderService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
