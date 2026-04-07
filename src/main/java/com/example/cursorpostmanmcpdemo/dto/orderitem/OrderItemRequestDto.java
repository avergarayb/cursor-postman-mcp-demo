package com.example.cursorpostmanmcpdemo.dto.orderitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
