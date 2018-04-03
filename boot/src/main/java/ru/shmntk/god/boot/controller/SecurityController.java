package ru.shmntk.god.boot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/security")
@Log4j2
public class SecurityController {

    private final JdbcUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityController(JdbcUserDetailsManager userDetailsService, PasswordEncoder passwordEncoder) {
	this.userDetailsService = userDetailsService;
	this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/createUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createUser(@RequestParam String user, @RequestParam String password, @RequestParam String[] roles) {
	UserDetails userDetails = User.withUsername(user).password(passwordEncoder.encode(password)).roles(roles).build();
	if (userDetailsService.userExists(user)) {
	    userDetailsService.updateUser(userDetails);
	} else {
	    userDetailsService.createUser(userDetails);
	}
	log.info("Создан пользователь " + userDetails.getUsername() + ". Роли: " + String.join(", ",
			userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())));
	return userDetails.getUsername();
    }

    @GetMapping("updatePassword")
    public void updatePassword(@AuthenticationPrincipal User user, @RequestParam String newPassword) {
	UserDetails userDetails = User.withUsername(user.getUsername()).password(passwordEncoder.encode(newPassword))
			.authorities(user.getAuthorities()).build();
	userDetailsService.updateUser(userDetails);
	log.info("Изменен пароль пользователя " + userDetails.getUsername());
    }
}
