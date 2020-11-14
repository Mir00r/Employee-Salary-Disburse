package com.mir00r.salarydisburse.config.security.oauth;


import com.mir00r.salarydisburse.commons.utils.PasswordUtil;
import com.mir00r.salarydisburse.domains.users.services.beans.CustomUserDetailsService;
import com.mir00r.salarydisburse.exceptions.nullpointer.NullPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Value("${app.client.id}")
    private String clientId;
    @Value("${app.client.secret}")
    private String clientSecret;
    @Value("${app.loginEndpoint}")
    private String loginEndpoint;

    @Value("${token.accesstoken.validity.seconds}")
    private String accessTokenValidity;

    @Value("${auth.token.type}")
    private String tokenType;
    @Value("${auth.token.private-signing-key}")
    private String signingKey;
    @Value("${auth.token.public-verifier-key}")
    private String verifierKey;

    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(PasswordUtil.getBCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        try {
            clients
                    //                .jdbc(dataSource);
                    .inMemory().withClient(this.clientId)
                    .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                    .authorities("ROLE_CLIENT", "ROLE_ANDROID_CLIENT")
                    .scopes("read", "write", "trust")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(20000)
                    .secret(PasswordUtil.encryptPassword(this.clientSecret, PasswordUtil.EncType.BCRYPT_ENCODER, null)).refreshTokenValiditySeconds(1209600);
        } catch (NullPasswordException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager).userDetailsService(this.userDetailsService)
                .pathMapping("/oauth/token", this.loginEndpoint)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(inMemoryTokenStore())
                .tokenEnhancer(new CustomTokenEnhancer());
    }


    @Bean
    public TokenStore inMemoryTokenStore() {
        return new InMemoryTokenStore();
    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(this.authenticationManager).userDetailsService(this.userDetailsService)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(tokenStore());
//        if ("jwt".equalsIgnoreCase(this.tokenType))
//            endpoints.accessTokenConverter(jwtTokenConverter());
//        else endpoints.tokenEnhancer(new CustomTokenEnhancer());
//    }
//
//
//    @Bean
//    public TokenStore tokenStore() {
//        if ("jwt".equalsIgnoreCase(this.tokenType))
//            return new JwtTokenStore(jwtTokenConverter());
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    public JwtTokenConverter jwtTokenConverter() {
//        JwtTokenConverter converter = new JwtTokenConverter();
//        converter.setSigningKey(this.signingKey);
//        return converter;
//    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(this.datasourceDriverClassname);
//        dataSource.setUrl(this.datasourceUrl);
//        dataSource.setUsername(this.datasourceUsername);
//        dataSource.setPassword(this.datasourcePassword);
//        return dataSource;
//    }
}
