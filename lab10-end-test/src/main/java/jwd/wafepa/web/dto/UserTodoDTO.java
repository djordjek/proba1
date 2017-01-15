package jwd.wafepa.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jwd.wafepa.model.User;
import jwd.wafepa.model.enumeration.UserRole;

public class UserTodoDTO {
	
	@NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 40)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 60)
    private String email;

    @NotNull
    @Size(max = 255)
    private String company;

    @NotNull
    private UserRole role;

    @NotNull
    @Size(min = 3, max = 128)
    private String username;
    

	@NotNull
    @Size(min = 6, max = 128)
    private String passwordHash;
	
	
	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	private AddressDTO addressDTO;
	
    public UserTodoDTO() {
	}

	public UserTodoDTO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.company = user.getCompany();
		this.role = user.getRole();
		this.username = user.getUsername();
		this.passwordHash = user.getPasswordHash();
		this.addressDTO = new AddressDTO(user.getAddress());
	}

	public UserTodoDTO(Long id, String firstName, String lastName, String email, String company, UserRole role,
			String username, String passwordHash) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
		this.role = role;
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}


}
