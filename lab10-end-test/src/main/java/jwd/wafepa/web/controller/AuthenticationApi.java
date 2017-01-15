package jwd.wafepa.web.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.annotation.Timed;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;
import jwd.wafepa.web.dto.ChangePasswordRequest;
import jwd.wafepa.web.dto.ChangePasswordResponse;
import jwd.wafepa.web.dto.SignInRequest;
import jwd.wafepa.web.dto.SignInResponse;
import jwd.wafepa.web.dto.SignUpRequest;
import jwd.wafepa.web.dto.SignUpResponse;

@RestController
@RequestMapping("/api/")
public class AuthenticationApi {
	
	private final Logger log = LoggerFactory.getLogger(AuthenticationApi.class);

    @Inject
    private UserService userService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        log.debug("POST /sign-up {}", request);
        final User user = userService.signUp(request.getFirstName(), request.getLastName(), request.getEmail(), request.getCompany(), request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(convertToSignUpResponse(user));
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/JSON")
    @Timed(millis = 0)
    @Transactional
    public ResponseEntity<SignInResponse> signIn(@Valid @RequestBody SignInRequest request) {
        log.debug("POST /sign-in {}", request);
        final SignInResponse response = userService.signIn(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 0)
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request, @ApiIgnore @AuthenticationPrincipal Long principalId) {
        log.debug("POST /change-password {}", request);
        final User user = userService.changePassword(principalId, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok().body(convertToChangePasswordResponse(user));
    }

    private SignUpResponse convertToSignUpResponse(User model) {
        final SignUpResponse dto = new SignUpResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        return dto;
    }

    private ChangePasswordResponse convertToChangePasswordResponse(User model) {
        final ChangePasswordResponse dto = new ChangePasswordResponse();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setCompany(model.getCompany());
        dto.setRole(model.getRole());
        dto.setUsername(model.getUsername());
        return dto;
    }

}
