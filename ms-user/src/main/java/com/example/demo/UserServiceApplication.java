package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.entity.Role;
import com.example.demo.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UserServiceApplication {

	private final RoleRepository roleRepository;

	UserServiceApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@PostConstruct
	void inicializarRoles() {
		List.of("USER", "ADMIN", "MANAGER").forEach(role -> {
			var r = new Role();
			r.setName("ROLE_" + role);
			r.setCreatedAt(LocalDateTime.now());
			r.setUpdatedAt(LocalDateTime.now());
			roleRepository.save(r);
		});
	}
}
