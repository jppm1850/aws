package aws.mitocode.spring.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.GlobalSignOutRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.model.PasswordResetRequiredException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;

import aws.mitocode.spring.dto.RenewPasswordFirstDTO;
import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.dto.UpdatePasswordDTO;
import aws.mitocode.spring.service.SecurityService;

/**
 * Integración con SDK Cognito
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${clientId}")
	private String cognitoClienteId;

	@Value("${userPoolId}")
	private String cognitoPoolId;

	@Autowired
	private AWSCognitoIdentityProviderClient cognitoClient;

	@Override
	public RespuestaApi getToken(String username, String password) {
		RespuestaApi rpta = new RespuestaApi();
		rpta.setStatus("ERROR");
		rpta.setBody("No se pudo autenticar");

		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("USERNAME", username);
		authParams.put("PASSWORD", password);

		try {
			AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
					.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
					.withAuthParameters(authParams)
					.withClientId(cognitoClienteId)
					.withUserPoolId(cognitoPoolId);

			AdminInitiateAuthResult authResponse = cognitoClient.adminInitiateAuth(authRequest);
			
			if (authResponse.getChallengeName() == null || authResponse.getChallengeName().isEmpty()) {
				authResponse.getAuthenticationResult().getAccessToken();
				rpta.setStatus("OK");
				rpta.setAccessToken(authResponse.getAuthenticationResult().getAccessToken());
				rpta.setIdToken(authResponse.getAuthenticationResult().getIdToken());
				rpta.setRefreshToken(authResponse.getAuthenticationResult().getRefreshToken());
				rpta.setBody("Autenticacion correcta");
			} else if (ChallengeNameType.NEW_PASSWORD_REQUIRED.name().equals(authResponse.getChallengeName())) {
				rpta.setStatus("OK-UPDATE");
				rpta.setBody("nuevo password requerido");
				rpta.setSessionId(authResponse.getSession());
			}
		} catch (NotAuthorizedException e) {
			rpta.setBody("Usuario no autorizado");
		} catch (UserNotFoundException e) {
			rpta.setBody("Usuario no autorizado");
		} catch(PasswordResetRequiredException e) {
			rpta.setBody("Reinicie su password");
			rpta.setStatus("OK-RESET");
		}

		return rpta;
	}

	@Override
	public RespuestaApi resetNewPasswordFirst(RenewPasswordFirstDTO updatePassword) {
		RespuestaApi rpta = new RespuestaApi();
		rpta.setStatus("ERROR");
		rpta.setBody("No se pudo autenticar");

		Map<String, String> challengeResponses = new HashMap<String, String>();
		challengeResponses.put("USERNAME", updatePassword.getUsername());
		challengeResponses.put("NEW_PASSWORD", updatePassword.getPassword());

		try {

			AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
					.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
					.withChallengeResponses(challengeResponses)
					.withClientId(cognitoClienteId)
					.withUserPoolId(cognitoPoolId)
					.withSession(updatePassword.getSession());

			AdminRespondToAuthChallengeResult authResponse = cognitoClient.adminRespondToAuthChallenge(request);
			if (authResponse.getChallengeName() == null || authResponse.getChallengeName().isEmpty()) {
				authResponse.getAuthenticationResult().getAccessToken();
				rpta.setStatus("OK");
				/**
				 * Se puede hacer asumir que estos tokens (comentados) ya es suficiente para el login o
				 * quiza se necesite pedir nuevamente el login
				 */
				/*
				rpta.setAccessToken(authResponse.getAuthenticationResult().getAccessToken());
				rpta.setIdToken(authResponse.getAuthenticationResult().getIdToken());
				rpta.setRefreshToken(authResponse.getAuthenticationResult().getRefreshToken());
				*/
			}
		} catch (NotAuthorizedException e) {
			rpta.setBody("Usuario no autorizado");
		} catch (UserNotFoundException e) {
			rpta.setBody("Usuario no autorizado");
		}

		return rpta;
	}
	
	@Override
	public RespuestaApi updatePassword(UpdatePasswordDTO updatePassword) {
		RespuestaApi rpta = new RespuestaApi();
		rpta.setStatus("ERROR");
		rpta.setBody("No se pudo autenticar");

		try {
			
			ConfirmForgotPasswordRequest confirmForgotPasswordRequest = new ConfirmForgotPasswordRequest()
					.withClientId(cognitoClienteId)
					.withConfirmationCode(updatePassword.getVerificationCode())
					.withPassword(updatePassword.getNewPassword())
					.withUsername(updatePassword.getUsername());
			
			cognitoClient.confirmForgotPassword(confirmForgotPasswordRequest);

			/**
			 * Usar este codigo para cambiar el password siempre y cuando el usuario este en sesion (con token)
			 */
			/*
			ChangePasswordRequest changePasswordRequest= new ChangePasswordRequest()
		              .withAccessToken(updatePassword.getToken())
		              .withPreviousPassword(updatePassword.getOldPassword())
		              .withProposedPassword(updatePassword.getNewPassword());
		 
		    cognitoClient.changePassword(changePasswordRequest);
		    */
		    
		    rpta.setStatus("OK");
			rpta.setBody("Clave cambiada correctamente");
		} catch (NotAuthorizedException e) {
			rpta.setBody("Usuario no autorizado");
		} catch (UserNotFoundException e) {
			rpta.setBody("Usuario no autorizado");
		} catch(Exception e) {
			logger.error("[updatePassword] Ocurrio un error inesperado: ", e);
			rpta.setBody("Ocurrio un error inespera     do");
		}

		return rpta;
	}

	/**
	 * Se puede ingresar el accessToken o idToken
	 */
	@Override
	public RespuestaApi signOut(String token) {
		RespuestaApi rpta = new RespuestaApi();
		rpta.setStatus("ERROR");
		rpta.setBody("No se pudo autenticar");
		
		try {
			GlobalSignOutRequest rq = new GlobalSignOutRequest()
					.withAccessToken(token);
			cognitoClient.globalSignOut(rq);
			rpta.setStatus("OK");
			rpta.setBody("SignOut correcto");
		}catch(Exception e) {
			logger.error("[signOut] Ocurrio un error inesperado: ", e);
			rpta.setBody(e.getMessage());
		}
		
		return rpta;
	}

	/**
	 * Se necesita el refreshToken como input
	 */
	@Override
	public RespuestaApi refreshToken(String token) {
		RespuestaApi rpta = new RespuestaApi();
		rpta.setStatus("ERROR");
		rpta.setBody("No se pudo autenticar");

		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("REFRESH_TOKEN", token);

		try {
			InitiateAuthRequest authRequest = new InitiateAuthRequest()
					.withAuthFlow(AuthFlowType.REFRESH_TOKEN_AUTH)
					.withAuthParameters(authParams)
					.withClientId(cognitoClienteId);

			InitiateAuthResult authResponse = cognitoClient.initiateAuth(authRequest);
			
			if (authResponse.getChallengeName() == null || authResponse.getChallengeName().isEmpty()) {
				authResponse.getAuthenticationResult().getAccessToken();
				rpta.setStatus("OK");
				rpta.setAccessToken(authResponse.getAuthenticationResult().getAccessToken());
				rpta.setIdToken(authResponse.getAuthenticationResult().getIdToken());
				rpta.setBody("actualizacion token correcta");
			}
		} catch (NotAuthorizedException e) {
			rpta.setBody("Usuario no autorizado");
		} catch (UserNotFoundException e) {
			rpta.setBody("Usuario no autorizado");
		} catch(PasswordResetRequiredException e) {
			rpta.setBody("Reinicie su password");
			rpta.setStatus("OK-RESET");
		}

		return rpta;
	}
}
