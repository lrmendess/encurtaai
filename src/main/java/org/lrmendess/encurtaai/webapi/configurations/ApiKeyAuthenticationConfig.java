package org.lrmendess.encurtaai.webapi.configurations;

import org.lrmendess.encurtaai.webapi.filters.ApiKeyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@EnableWebSecurity
public class ApiKeyAuthenticationConfig extends WebSecurityConfigurerAdapter  {
    
    @Value("${encurtaai.http.apikey-header-name}")
    private String principalRequestHeader;

    @Value("${encurtaai.http.apikey-value}")
    private String principalRequestValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApiKeyAuthenticationFilter filter = new ApiKeyAuthenticationFilter(principalRequestHeader);

        filter.setAuthenticationManager(new AuthenticationManager() {
            
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String principal = (String) authentication.getPrincipal();

                if (!principalRequestValue.equals(principal))
                {
                    throw new BadCredentialsException("Api key was not found or is incorrect");
                }

                authentication.setAuthenticated(true);
                return authentication;
            }

        });

        http.antMatcher("/api/**")
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(filter).authorizeRequests().anyRequest().authenticated();
            // .and()
            // .requiresChannel().anyRequest().requiresSecure();
    }

}
