package jwd.wafepa.web.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpRequest implements Serializable {
	
	  private static final long serialVersionUID = 1L;

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
	    @Size(min = 3, max = 128)
	    private String username;

	    @NotNull
	    @Size(min = 6, max = 32)
	    //@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$")
	    @Pattern(regexp = "^[0-9].*[a-z]")
	    private String password;

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

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        final SignUpRequest other = (SignUpRequest) obj;
	        if ((firstName == null && other.firstName != null) || !firstName.equals(other.firstName))
	            return false;
	        if ((lastName == null && other.lastName != null) || !lastName.equals(other.lastName))
	            return false;
	        if ((email == null && other.email != null) || !email.equals(other.email))
	            return false;
	        if ((company == null && other.company != null) || !company.equals(other.company))
	            return false;
	        if ((username == null && other.username != null) || !username.equals(other.username))
	            return false;
	        if ((password == null && other.password != null) || !password.equals(other.password))
	            return false;
	        return true;
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	        result = prime * result + ((email == null) ? 0 : email.hashCode());
	        result = prime * result + ((company == null) ? 0 : company.hashCode());
	        result = prime * result + ((username == null) ? 0 : username.hashCode());
	        result = prime * result + ((password == null) ? 0 : password.hashCode());
	        return result;
	    }

	    @Override
	    public String toString() {
	        return "SignUpRequest[" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", company=" + company + ", username=" + username + "]";
	    }

}
