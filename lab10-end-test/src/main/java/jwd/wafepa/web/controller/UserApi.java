package jwd.wafepa.web.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.annotation.Timed;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.User;
import jwd.wafepa.repository.AddressRepository;
import jwd.wafepa.repository.UserRepository;
import jwd.wafepa.web.dto.AddressDTO;
import jwd.wafepa.web.dto.AllUsersResponse;
import jwd.wafepa.web.dto.CreateUserRequest;
import jwd.wafepa.web.dto.CreateUserResponse;
import jwd.wafepa.web.dto.ReadUserResponse;
import jwd.wafepa.web.dto.RestUpdateUserRequest;
import jwd.wafepa.web.dto.UpdateUserResponse;
import jwd.wafepa.web.dto.UserAddressDTO;

@RestController
@RequestMapping("/api/")
public class UserApi {
	
	private final Logger log = LoggerFactory.getLogger(UserApi.class);

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private AddressRepository addressRepository;
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional(readOnly = true)
    public ResponseEntity<ReadUserResponse> readUser(@PathVariable Long id) {
        log.debug("GET /user/{}", id);
        final Optional<User> result = Optional.ofNullable(userRepository.findOne(id));
        if (result.isPresent()) {
            return ResponseEntity.ok().body(convertToReadUserResponse(result.get()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/search-user-firstname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional(readOnly = true)
    public ResponseEntity<List<AllUsersResponse>> searchUserByFirstName(@RequestParam(value="firstName", required=false) String firstName) {
    	List<User> result = userRepository.findByFirstName(firstName);
        return ResponseEntity.ok().body(result.stream().map(this::convertToAllUsersResponse).collect(Collectors.toList()));
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) throws URISyntaxException {
        log.debug("POST /user {}", request);
        final User user = convertToUser(request);
        final User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/user/" + result.getId())).body(convertToCreateUserResponse(result));
    }
    
    @RequestMapping(value = "/userWithAddress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<UserAddressDTO> createUserWithAddress(@Valid @RequestBody UserAddressDTO request) throws URISyntaxException {
        log.debug("POST /user {}", request);
        final User user = convertToUserWithAddress(request);
        final User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/user/" + result.getId())).body(convertToCreateUserResponseWithAddress(result));
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody RestUpdateUserRequest request) {
        log.debug("PUT /user/{} {}", id, request);
        final User user = convertToUser(id, request);
        final User result = userRepository.save(user);
        return ResponseEntity.ok().body(convertToUpdateUserResponse(result));
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("DELETE /user/{}", id);
        userRepository.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(value = "/all-users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AllUsersResponse>> allUsers() {
        log.debug("GET /all-users");
        final List<User> result = userRepository.findAll();
        return ResponseEntity.ok().body(result.stream().map(this::convertToAllUsersResponse).collect(Collectors.toList()));
    }

    
    private ReadUserResponse convertToReadUserResponse(User model) {
        final ReadUserResponse dto = new ReadUserResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        dto.setPasswordHash(model.getPasswordHash());
        dto.setAddress_id(model.getAddress().getId());
        dto.setAddress(new AddressDTO(model.getAddress()));
        
        return dto;
    }
    
    private User convertToUser(CreateUserRequest dto) {
        final User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCompany(dto.getCompany());
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        return user;
    }
    
    private User convertToUserWithAddress(UserAddressDTO dto){
    	final User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCompany(dto.getCompany());
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        user.setAddress(addressRepository.findOne(dto.getAddressId()));
        return user;
    }
    
    private CreateUserResponse convertToCreateUserResponse(User model) {
        final CreateUserResponse dto = new CreateUserResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        dto.setPasswordHash(model.getPasswordHash());
        return dto;
    }
    
    private UserAddressDTO convertToCreateUserResponseWithAddress(User model){
    	return new UserAddressDTO(model);
    }
    
    private User convertToUser(Long id, RestUpdateUserRequest dto) {
        final User user = new User();
        user.setId(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setCompany(dto.getCompany());
        user.setRole(dto.getRole());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        return user;
    }
    
    
    
    private UpdateUserResponse convertToUpdateUserResponse(User model) {
        final UpdateUserResponse dto = new UpdateUserResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        dto.setPasswordHash(model.getPasswordHash());
        return dto;
    }
    
    private AllUsersResponse convertToAllUsersResponse(User model) {
        final AllUsersResponse dto = new AllUsersResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        dto.setPasswordHash(model.getPasswordHash());
        return dto;
    }
    
    

}
