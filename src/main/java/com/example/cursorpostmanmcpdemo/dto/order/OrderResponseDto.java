package com.example.cursorpostmanmcpdemo.dto.order;

import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private Long id;
    private LocalDateTime date;
    private Long userId;
    private String userName;
    private String userEmail;
    private List<OrderItemResponseDto> items;
}
