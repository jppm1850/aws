package aws.mitocode.spring.dto;

public class RespuestaApi{

	private String status;
	private String accessToken;
	private String idToken;
	private String refreshToken;
	private String sessionId;
	
	private Object body;
	
	public RespuestaApi() {}
	
	public RespuestaApi(String status, Object body) {
		this.status = status;
		this.body = body;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
