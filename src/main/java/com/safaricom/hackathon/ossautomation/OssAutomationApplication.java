package com.safaricom.hackathon.ossautomation;

import com.safaricom.hackathon.ossautomation.pojo.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@SpringBootApplication
public class OssAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OssAutomationApplication.class, args);
	}

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

	@Bean
	public AuditorAware<Users> auditor() {
		return () -> Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(Users.class::cast);
	}
}
