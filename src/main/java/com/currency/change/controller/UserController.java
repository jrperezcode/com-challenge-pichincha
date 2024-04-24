package com.currency.change.controller;

import com.currency.change.User.User;
import com.currency.change.User.UserRepository;
import com.currency.change.model.AuditControl;
import com.currency.change.service.ServiceAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/support/user")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> registerTransactionExchange(@RequestBody User user){
      return  new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
}
