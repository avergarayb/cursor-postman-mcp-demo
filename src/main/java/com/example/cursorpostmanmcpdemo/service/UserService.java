package com.example.cursorpostmanmcpdemo.service;

import com.example.cursorpostmanmcpdemo.dto.user.UserRequestDto;
import com.example.cursorpostmanmcpdemo.dto.user.UserResponseDto;
import com.example.cursorpostmanmcpdemo.entity.User;
import com.example.cursorpostmanmcpdemo.exception.DuplicateEmailException;
import com.example.cursorpostmanmcpdemo.exception.ResourceNotFoundException;
import com.example.cursorpostmanmcpdemo.mapper.UserMapper;
import com.example.cursorpostmanmcpdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    public UserResponseDto findById(Long id) {
        return userMapper.toResponse(getUser(id));
    }

    @Transactional
    public UserResponseDto create(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }
        User user = userMapper.toEntity(dto);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = getUser(id);
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail())
                && userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException(dto.getEmail());
        }
        userMapper.updateEntity(dto, user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw ResourceNotFoundException.forId("Usuario", id);
        }
        userRepository.deleteById(id);
    }

    User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.forId("Usuario", id));
    }
}
