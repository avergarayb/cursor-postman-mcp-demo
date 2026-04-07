package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.product.ProductRequestDto;
import com.example.cursorpostmanmcpdemo.dto.product.ProductResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-06T22:28:34-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDto toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto.ProductResponseDtoBuilder productResponseDto = ProductResponseDto.builder();

        productResponseDto.id( product.getId() );
        productResponseDto.name( product.getName() );
        productResponseDto.price( product.getPrice() );

        return productResponseDto.build();
    }

    @Override
    public Product toEntity(ProductRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( dto.getName() );
        product.price( dto.getPrice() );

        return product.build();
    }

    @Override
    public void updateEntity(ProductRequestDto dto, Product product) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            product.setName( dto.getName() );
        }
        if ( dto.getPrice() != null ) {
            product.setPrice( dto.getPrice() );
        }
    }
}
