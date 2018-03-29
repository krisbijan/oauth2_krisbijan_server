package com.krisbijan.oauth2_krisbijan_server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.krisbijan.oauth2_krisbijan_server.config.AuthorizationServerConfiguration;

import org.springframework.http.HttpStatus;


@Controller
public class LogoutController {


    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = AuthorizationServerConfiguration.tokenStore.readAccessToken(tokenValue);
            AuthorizationServerConfiguration.tokenStore.removeAccessToken(accessToken);
        }
    }
}