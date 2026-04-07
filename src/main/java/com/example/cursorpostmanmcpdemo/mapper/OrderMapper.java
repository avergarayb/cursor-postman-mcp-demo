package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.order.OrderRequestDto;
import com.example.cursorpostmanmcpdemo.dto.order.OrderResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "userEmail", source = "user.email")
    OrderResponseDto toResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderRequestDto dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            ignoreUnmappedSourceProperties = {"userId"})
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(OrderRequestDto dto, @MappingTarget Order order);
}
