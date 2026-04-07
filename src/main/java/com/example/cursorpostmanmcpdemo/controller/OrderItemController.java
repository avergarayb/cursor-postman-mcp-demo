package com.example.cursorpostmanmcpdemo.controller;

import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemRequestDto;
import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import com.example.cursorpostmanmcpdemo.service.OrderItemService;
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
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
@Tag(name = "Líneas de pedido", description = "Ítems asociados a un pedido y un producto")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponseDto> list() {
        return orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public OrderItemResponseDto get(@PathVariable Long id) {
        return orderItemService.findById(id);
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> create(@Valid @RequestBody OrderItemRequestDto dto) {
        OrderItemResponseDto created = orderItemService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public OrderItemResponseDto replace(@PathVariable Long id, @Valid @RequestBody OrderItemRequestDto dto) {
        return orderItemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
