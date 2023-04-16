package com.academia.ikub.spring.project.service.impl;

import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.dto.user.UserUpdateDTO;
import com.academia.ikub.spring.project.domain.entity.User;
import com.academia.ikub.spring.project.domain.entity.UserRole;
import com.academia.ikub.spring.project.domain.exception.ResourceNotFoundException;
import com.academia.ikub.spring.project.domain.mapper.UserMapper;
import com.academia.ikub.spring.project.repository.UserRepository;
import com.academia.ikub.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                String.format("User with id %s not found",id)));
    }


    @Override
    public List<UserDTO> findUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Void deleteUser(Integer id) {
        userRepository.findById(id)
                .ifPresentOrElse(u->userRepository.deleteById(id),()-> new ResourceNotFoundException(
                        String.format("User with id %s not found",id)
                        ));
        return null;
    }

    @Override
    public UserUpdateDTO updateUser(Integer id, UserUpdateDTO userDto) {
        User user = UserMapper.toUpdateEntity(findUserById(id),userDto);
        return UserMapper.toUpdateDto(userRepository.save(user));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO, String userRole) {
        User u = UserMapper.toEntity(userDTO);
        u.setRole(userRole!=null? UserRole.fromValue(userRole):UserRole.BUYER);
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u = userRepository.save(u);
        return UserMapper.toDto(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("User with email %s not found",email)
                ));
    }
    @Override
    public User getUserFromToken(Jwt jwt) {
        String sub = (String) jwt.getClaims().get("sub");
        return userRepository.findByEmail(sub).get();
    }
}
