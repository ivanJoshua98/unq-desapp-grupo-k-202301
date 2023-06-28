package ar.edu.unq.grupok.backenddesappapi.dto;

public class UserEmailDTO {

	private String userEmail;
	
	public UserEmailDTO() {
		super();
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "UserEmailDTO{" +
				"userEmail='" + userEmail + '\'' +
				'}';
	}
}
