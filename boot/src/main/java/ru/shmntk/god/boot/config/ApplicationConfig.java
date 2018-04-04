package ru.shmntk.god.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shmntk.god.boot.aspect.VerifiableAspect;

@Configuration
public class ApplicationConfig {

    @Bean
    public VerifiableAspect verifiableAspect() {
	return new VerifiableAspect();
    }
}
