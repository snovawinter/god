package ru.shmntk.god.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource godH2DataSource;

    @Autowired
    public SecurityConfig(DataSource godH2DataSource) {
	this.godH2DataSource = godH2DataSource;
    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Все запросы к придложению только через авторизацию
        // Только админ может делать запросы к актуатору
	http.authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
			.anyRequest().authenticated().and().httpBasic();
    }
    // @formatter:on

    @Bean
    public JdbcUserDetailsManager userDetailsService() {
	JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
	manager.setDataSource(godH2DataSource);
	return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(godH2DataSource);
    }
}
