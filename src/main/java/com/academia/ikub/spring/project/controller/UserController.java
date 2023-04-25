package com.academia.ikub.spring.project.controller;

import com.academia.ikub.spring.project.domain.dto.user.UserDTO;
import com.academia.ikub.spring.project.domain.dto.user.UserUpdateDTO;
import com.academia.ikub.spring.project.domain.mapper.UserMapper;
import com.academia.ikub.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateDTO> updateUser (@PathVariable Integer id, @RequestBody UserUpdateDTO updateDTO){
        return ResponseEntity.ok(userService.updateUser(id,updateDTO));
    }
    @RolesAllowed("ADMIN")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> findUser (@PathVariable Integer id){
        return ResponseEntity.ok(UserMapper.toDto(userService.findUserById(id)));
    }

    @RolesAllowed("ADMIN")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }


    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
