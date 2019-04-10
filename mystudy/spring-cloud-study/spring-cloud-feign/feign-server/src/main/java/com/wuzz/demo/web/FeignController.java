package com.wuzz.demo.web;

import com.wuzz.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(value ="/feign/hello")
	public String hello() {
		
		return service.hello();
	}
}
