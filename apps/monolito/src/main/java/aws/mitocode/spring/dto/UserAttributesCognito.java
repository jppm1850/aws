package aws.mitocode.spring.dto;

public class UserAttributesCognito {

	private String email_verified;
	private String email;
	
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(String email_verified) {
		this.email_verified = email_verified;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
