package com.wuzz.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @RequestMapping("addOrder")
    public String addOrder(){
        return "addOrder";
    }
}
