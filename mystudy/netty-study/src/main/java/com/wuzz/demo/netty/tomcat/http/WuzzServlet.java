package com.wuzz.demo.netty.tomcat.http;

public abstract class WuzzServlet {
	
	public void service(WuzzRequest request, WuzzResponse response) throws Exception{
		
		//由service方法来决定，是调用doGet或者调用doPost
		if("GET".equalsIgnoreCase(request.getMethod())){
			doGet(request, response);
		}else{
			doPost(request, response);
		}

	}
	
	public abstract void doGet(WuzzRequest request, WuzzResponse response) throws Exception;
	
	public abstract void doPost(WuzzRequest request, WuzzResponse response) throws Exception;
}
