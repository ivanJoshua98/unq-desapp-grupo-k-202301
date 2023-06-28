package ar.edu.unq.grupok.backenddesappapi.dto;

public class UserLoginDTO {
	
	private String userEmail;
	
	private String password;
	
	
	public UserLoginDTO() {
		super();
	}
	
	public UserLoginDTO(String userEmail, String password) {
		this.userEmail = userEmail;
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String toString () {
//		return "peter";
//	}


	@Override
	public String toString() {
		return "UserLoginDTO{" +
				"userEmail='" + userEmail + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
