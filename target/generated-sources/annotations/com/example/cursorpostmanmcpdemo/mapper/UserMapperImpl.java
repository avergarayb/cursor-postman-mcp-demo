package com.example.cursorpostmanmcpdemo.mapper;

import com.example.cursorpostmanmcpdemo.dto.user.UserRequestDto;
import com.example.cursorpostmanmcpdemo.dto.user.UserResponseDto;
import com.example.cursorpostmanmcpdemo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-06T22:28:34-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.name( user.getName() );
        userResponseDto.email( user.getEmail() );

        return userResponseDto.build();
    }

    @Override
    public User toEntity(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( dto.getName() );
        user.email( dto.getEmail() );

        return user.build();
    }

    @Override
    public void updateEntity(UserRequestDto dto, User user) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
    }
}
