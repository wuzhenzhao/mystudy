package com.wuzz.demo.web;

import com.wuzz.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

	@Autowired
	private ClientService service;

	@RequestMapping(value = "/feign/hello", method = RequestMethod.GET)
	public String hello() throws InterruptedException{

		return service.hello("1");
	}
}
