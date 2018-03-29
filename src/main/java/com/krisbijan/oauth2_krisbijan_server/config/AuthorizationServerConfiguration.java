package com.krisbijan.oauth2_krisbijan_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.krisbijan.oauth2_krisbijan_server.service.user.CustomUserDetailsService;



@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	

	static final String RESOURCE_ID = "fit_it_rest_service";

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired
	@Qualifier("authenticationManagerBean")
	AuthenticationManager authenticationManager;
	
	public static TokenStore tokenStore = new InMemoryTokenStore();
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
	{
	   oauthServer.checkTokenAccess("permitAll()");    
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(this.tokenStore);
		return tokenServices;
		
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("myclientapp").authorizedGrantTypes("password","refresh_token").scopes("read","write").secret("9999").resourceIds(RESOURCE_ID);
	}

}
