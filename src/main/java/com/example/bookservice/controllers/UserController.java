package com.example.bookservice.controllers;

import com.example.bookservice.common.entities.User;
import com.example.bookservice.common.enums.UserRole;
import com.example.bookservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/users")
public class UserController {

//    @Autowired
//    private UserService userService;
//
////    @PermitAll
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
////        return null;
//        User cuser = userService.registerUser(user.getUsername(), user.getPassword(), user.getRole());//(userName, password, role);
////        User user = userService.registerUser("Shira1", "password", UserRole.USER);
//        return ResponseEntity.status(HttpStatus.CREATED).body(cuser);
//    }
//
////    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{id}/role")
//    public ResponseEntity<User> updateUserRole(@PathVariable Long id, @RequestParam UserRole role) {
//        try {
//            User user = userService.updateUserRole(id, role);
//            return ResponseEntity.ok(user);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
}
