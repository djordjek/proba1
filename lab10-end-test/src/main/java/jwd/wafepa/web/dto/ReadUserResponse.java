package jwd.wafepa.web.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import jwd.wafepa.model.enumeration.UserRole;



public class ReadUserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

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
    
    private Long address_id;
    
    private AddressDTO address;

    public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReadUserResponse other = (ReadUserResponse) obj;
        if ((id == null && other.id != null) || !id.equals(other.id))
            return false;
        if ((firstName == null && other.firstName != null) || !firstName.equals(other.firstName))
            return false;
        if ((lastName == null && other.lastName != null) || !lastName.equals(other.lastName))
            return false;
        if ((email == null && other.email != null) || !email.equals(other.email))
            return false;
        if ((company == null && other.company != null) || !company.equals(other.company))
            return false;
        if ((role == null && other.role != null) || !role.equals(other.role))
            return false;
        if ((username == null && other.username != null) || !username.equals(other.username))
            return false;
        if ((passwordHash == null && other.passwordHash != null) || !passwordHash.equals(other.passwordHash))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ReadUserResponse[" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", company=" + company + ", role=" + role + ", username=" + username
                + "]";
    }

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

}
