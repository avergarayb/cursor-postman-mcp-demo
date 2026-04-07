package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.user.UserRequestDto;
import com.example.cursorpostmanmcpdemo.dto.user.UserResponseDto;
import com.example.cursorpostmanmcpdemo.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toEntity(UserRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    void updateEntity(UserRequestDto dto, @MappingTarget User user);
}
