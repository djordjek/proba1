package jwd.wafepa.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.User;
import jwd.wafepa.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Inject
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.trace(".loadUserByUsername({})", username);

        final String lowercaseUsername = username.toLowerCase();
        final Optional<User> optionalUser = userRepository.findByUsername(lowercaseUsername);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        final User user = optionalUser.get();
        final List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(lowercaseUsername, null, grantedAuthorities);
    }

}
