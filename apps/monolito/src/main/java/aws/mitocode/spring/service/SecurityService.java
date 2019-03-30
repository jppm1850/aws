package aws.mitocode.spring.service;

import aws.mitocode.spring.dto.RenewPasswordFirstDTO;
import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.dto.UpdatePasswordDTO;

public interface SecurityService {

	public RespuestaApi getToken(String username, String password);
	public RespuestaApi resetNewPasswordFirst(RenewPasswordFirstDTO updatePassword);
	public RespuestaApi updatePassword(UpdatePasswordDTO updatePassword);
	public RespuestaApi signOut(String token);
	public RespuestaApi refreshToken(String token);
}
