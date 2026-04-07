package com.example.cursorpostmanmcpdemo.service;

import com.example.cursorpostmanmcpdemo.dto.product.ProductRequestDto;
import com.example.cursorpostmanmcpdemo.dto.product.ProductResponseDto;
import com.example.cursorpostmanmcpdemo.entity.Product;
import com.example.cursorpostmanmcpdemo.exception.ResourceNotFoundException;
import com.example.cursorpostmanmcpdemo.mapper.ProductMapper;
import com.example.cursorpostmanmcpdemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toResponse).toList();
    }

    public ProductResponseDto findById(Long id) {
        return productMapper.toResponse(getProduct(id));
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto dto) {
        Product product = productMapper.toEntity(dto);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto dto) {
        Product product = getProduct(id);
        productMapper.updateEntity(dto, product);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw ResourceNotFoundException.forId("Producto", id);
        }
        productRepository.deleteById(id);
    }

    Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.forId("Producto", id));
    }
}
