package com.wuzz.demo.netty.tomcat.servlet;


import com.wuzz.demo.netty.tomcat.http.WuzzRequest;
import com.wuzz.demo.netty.tomcat.http.WuzzResponse;
import com.wuzz.demo.netty.tomcat.http.WuzzServlet;

public class SecondServlet extends WuzzServlet {


	@Override
	public void doGet(WuzzRequest request, WuzzResponse response) throws Exception {
		this.doPost(request, response);
	}

	@Override
	public void doPost(WuzzRequest request, WuzzResponse response) throws Exception {
		response.write("This is Second Serlvet");
	}

}
