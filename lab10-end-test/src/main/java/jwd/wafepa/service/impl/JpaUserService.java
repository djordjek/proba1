package jwd.wafepa.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jwd.wafepa.config.CustomProperties;
import jwd.wafepa.model.User;
import jwd.wafepa.model.enumeration.UserRole;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.security.JWTUtils;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.SignInResponse;
import jwd.wafepa.web.exception.AuthenticationError;

@Service
public class JpaUserService implements UserService{
	
	  private final Logger log = LoggerFactory.getLogger(UserService.class);

	    @Inject
	    private CustomProperties customProperties;

	    @Inject
	    private PasswordEncoder passwordEncoder;

	    @Inject
	    private UserRepository userRepository;

	    public User signUp(String firstName, String lastName, String email, String company, String username, String password) {
	        log.debug("signUp(firstName: {}, lastName: {}, email: {}, company: {}, username: {})", firstName, lastName, email, company, username);

	        final User user = new User();
	        user.setFirstName(firstName);
	        user.setLastName(lastName);
	        user.setEmail(email);
	        user.setCompany(company);
	        user.setUsername(username);
	        user.setRole(UserRole.UNAUTHORIZED);
	        user.setPasswordHash(passwordEncoder.encode(password));
	        userRepository.save(user);
	        return user;
	    }

	    public SignInResponse signIn(String username, String password) {

	        log.debug("signIn(username: {})", username);

	        final User user = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationError("credentials.invalid", "Credentials are invalid!"));
	        if (!passwordEncoder.matches(password, user.getPasswordHash()))
	            throw new AuthenticationError("credentials.invalid", "Credentials are invalid!");

	        final SignInResponse response = new SignInResponse();
	        final String accessToken = JWTUtils.createToken(user.getId(), user.getRole(), customProperties.getSecretKey());
	        response.setAccessToken(accessToken);
	        response.setId(user.getId());
	        response.setFirstName(user.getFirstName());
	        response.setLastName(user.getLastName());
	        response.setEmail(user.getEmail());
	        response.setCompany(user.getCompany());
	        response.setRole(user.getRole());
	        response.setUsername(user.getUsername());
	        return response;
	    }

	    public User changePassword(Long userId, String oldPassword, String newPassword) {

	        log.debug("changePassword(userId: {})", userId);

	        final User user = userRepository.findOne(userId);
	        if (user == null) {
	            throw new AuthenticationError("credentials.invalid", "Credentials are invalid!");
	        }
	        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
	            throw new AuthenticationError("credentials.invalid", "Credentials are invalid!");
	        }
	        user.setPasswordHash(passwordEncoder.encode(newPassword));
	        userRepository.save(user);
	        return user;
	    }

}
