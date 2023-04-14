package ar.edu.unq.grupok.backenddesappapi.webservice;

import ar.edu.unq.grupok.backenddesappapi.model.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDTO {
		
	    @Size(min = 3, max = 30, message = "Invalid length for name")
	    private String name;
	    
	    @Size(min = 3, max = 30, message = "Invalid length for last name")
	    private String lastName;

	    @Email(message = "The email entered is invalid") 
	    private String email;
	    
	    @Size(min = 10, max = 30, message = "Invalid length for address")
	    private String address;
	    
	    @ValidPassword
	    @Size(min = 6, message = "The password is less than 6 characters")
	    private String password;
	    
	    @Size(min = 22, max = 22, message = "Invalid length for CVU")
	    private String cvuMercadoPago;
	    
	    @Size(min = 8, max = 8, message = "Invalid length for wallet")
	    private String criptoWallet;


	    public UserDTO() {
	    	super();
	    }
		
	    public String getCriptoWallet() {
	        return criptoWallet;
	    }

	    public String getCvu() {
	        return cvuMercadoPago;
	    }
	    
	    public String getPassword() {
	        return password;
	    }

	    public String getAddress() {
	        return address;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setCvu(String cvuMercadoPago) {
	        this.cvuMercadoPago = cvuMercadoPago;
	    }

	    public void setCriptoWallet(String criptoWallet) {
	        this.criptoWallet = criptoWallet;
	    }

}
