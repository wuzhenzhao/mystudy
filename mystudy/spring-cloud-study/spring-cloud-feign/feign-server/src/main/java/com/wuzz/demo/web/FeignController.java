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

	@RequestMapping(value = "/testHello", method = RequestMethod.GET)
	public String hello() throws InterruptedException{

		return service.hello("1");
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello2() throws InterruptedException{

		return service.hello("2");
	}

	@RequestMapping(value = "/fegin/hello", method = RequestMethod.GET)
	public String hello3() throws InterruptedException{

		return "service.hello(2)";
	}
}
