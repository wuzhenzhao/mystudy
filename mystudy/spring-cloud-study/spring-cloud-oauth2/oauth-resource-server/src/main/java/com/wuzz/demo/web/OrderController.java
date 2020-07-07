package com.wuzz.demo.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @RequestMapping("addOrder")
    public String addOrder() {
        return "addOrder";
    }

    @RequestMapping(value = "/me", method = {RequestMethod.GET})
    public Object me(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = Jwts.parser().setSigningKey("wuzz".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String company = (String) claims.get("company");
        System.out.println(company);
        return user;
    }
}
