package ru.shmntk.god.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final JdbcUserDetailsManager userDetailsService;

    @Autowired
    public SecurityController(JdbcUserDetailsManager userDetailsService) {
	this.userDetailsService = userDetailsService;
    }

    @GetMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createUser(@RequestParam String user, @RequestParam String password, @RequestParam String[] roles) {
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	UserDetails userDetails = User.withUsername(user).password(encoder.encode(password)).roles(roles).build();
	if (userDetailsService.userExists(user)) {
	    userDetailsService.updateUser(userDetails);
	} else {
	    userDetailsService.createUser(userDetails);
	}
	return userDetails.getUsername();
    }
}
