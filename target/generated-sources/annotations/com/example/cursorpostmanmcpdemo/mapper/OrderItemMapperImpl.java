package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemRequestDto;
import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Order;
import com.example.cursorpostmanmcpdemo.entity.OrderItem;
import com.example.cursorpostmanmcpdemo.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-06T22:28:34-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemResponseDto toResponse(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponseDto.OrderItemResponseDtoBuilder orderItemResponseDto = OrderItemResponseDto.builder();

        orderItemResponseDto.orderId( orderItemOrderId( orderItem ) );
        orderItemResponseDto.productId( orderItemProductId( orderItem ) );
        orderItemResponseDto.productName( orderItemProductName( orderItem ) );
        orderItemResponseDto.productPrice( orderItemProductPrice( orderItem ) );
        orderItemResponseDto.id( orderItem.getId() );
        orderItemResponseDto.quantity( orderItem.getQuantity() );

        return orderItemResponseDto.build();
    }

    @Override
    public OrderItem toEntity(OrderItemRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.quantity( dto.getQuantity() );

        return orderItem.build();
    }

    @Override
    public void updateEntity(OrderItemRequestDto dto, OrderItem orderItem) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getQuantity() != null ) {
            orderItem.setQuantity( dto.getQuantity() );
        }
    }

    private Long orderItemOrderId(OrderItem orderItem) {
        Order order = orderItem.getOrder();
        if ( order == null ) {
            return null;
        }
        return order.getId();
    }

    private Long orderItemProductId(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String orderItemProductName(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }

    private Double orderItemProductPrice(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getPrice();
    }
}
