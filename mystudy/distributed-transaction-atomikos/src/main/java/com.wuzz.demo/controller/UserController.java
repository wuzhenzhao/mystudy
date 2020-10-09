package com.wuzz.demo.controller;

import com.wuzz.demo.domain.User;
import com.wuzz.demo.mapper.mapper1.UserMapper1;
import com.wuzz.demo.mapper.mapper2.UserMapper2;
import com.wuzz.demo.repository.UserRepository;
import com.wuzz.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ：wuzhenzhao
 * @date ：Created in 2019/1/23 15:53
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/web/mvc/user/save")
    public int save() throws ExecutionException, InterruptedException {
//        Future<Boolean> future = executorService.submit(() -> {
//            return userRepository.save(user);
//        });
//
//        return  future.get();
        System.out.printf("[Thread : %s ] UserController starts saving user...\n",
                Thread.currentThread().getName());
        User user =new User();
        user.setId(123456);
        user.setName("分布式事务");
        return  userService.insert(user);
    }
}
