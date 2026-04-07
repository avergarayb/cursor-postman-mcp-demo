package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.order.OrderRequestDto;
import com.example.cursorpostmanmcpdemo.dto.order.OrderResponseDto;
import com.example.cursorpostmanmcpdemo.dto.orderitem.OrderItemResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Order;
import com.example.cursorpostmanmcpdemo.entity.OrderItem;
import com.example.cursorpostmanmcpdemo.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-06T22:28:34-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderResponseDto toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto.OrderResponseDtoBuilder orderResponseDto = OrderResponseDto.builder();

        orderResponseDto.userId( orderUserId( order ) );
        orderResponseDto.userName( orderUserName( order ) );
        orderResponseDto.userEmail( orderUserEmail( order ) );
        orderResponseDto.id( order.getId() );
        orderResponseDto.date( order.getDate() );
        orderResponseDto.items( orderItemListToOrderItemResponseDtoList( order.getItems() ) );

        return orderResponseDto.build();
    }

    @Override
    public Order toEntity(OrderRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.date( dto.getDate() );

        return order.build();
    }

    @Override
    public void updateEntity(OrderRequestDto dto, Order order) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getDate() != null ) {
            order.setDate( dto.getDate() );
        }
    }

    private Long orderUserId(Order order) {
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private String orderUserName(Order order) {
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getName();
    }

    private String orderUserEmail(Order order) {
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getEmail();
    }

    protected List<OrderItemResponseDto> orderItemListToOrderItemResponseDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemResponseDto> list1 = new ArrayList<OrderItemResponseDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toResponse( orderItem ) );
        }

        return list1;
    }
}
