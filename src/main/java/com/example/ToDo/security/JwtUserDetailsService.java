package com.example.ToDo.security;

import com.example.ToDo.entity.UserEntity;
import com.example.ToDo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);
	private final UserRepository userRepository;

	public JwtUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("Loading user by email: {}", email);
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		UserEntity user = userOptional.orElseThrow(() -> {
			logger.error("User not found with email: {}", email);
			return new UsernameNotFoundException("User not found with email: " + email);
		});
		return new CustomUserDetails(user.getId().toString(), user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}