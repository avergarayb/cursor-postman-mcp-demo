package com.example.cursorpostmanmcpdemo.controller;

import com.example.cursorpostmanmcpdemo.dto.order.OrderResponseDto;
import com.example.cursorpostmanmcpdemo.dto.user.UserRequestDto;
import com.example.cursorpostmanmcpdemo.dto.user.UserResponseDto;
import com.example.cursorpostmanmcpdemo.service.OrderService;
import com.example.cursorpostmanmcpdemo.service.UserService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Alta, consulta, actualización y baja de usuarios")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public List<UserResponseDto> list() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> listOrders(@PathVariable Long id) {
        return orderService.findByUserId(id);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto dto) {
        UserResponseDto created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public UserResponseDto replace(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
