package jwd.wafepa.service;

import jwd.wafepa.model.User;
import jwd.wafepa.web.dto.SignInResponse;

public interface UserService {
	
	User signUp(String firstName, String lastName, String email, String company, String username, String password);
	
	SignInResponse signIn(String username, String password);
	
	User changePassword(Long userId, String oldPassword, String newPassword);

}
