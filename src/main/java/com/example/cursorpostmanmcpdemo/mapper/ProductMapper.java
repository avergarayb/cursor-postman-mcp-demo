package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.product.ProductRequestDto;
import com.example.cursorpostmanmcpdemo.dto.product.ProductResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product toEntity(ProductRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    void updateEntity(ProductRequestDto dto, @MappingTarget Product product);
}
