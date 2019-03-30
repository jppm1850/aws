package aws.mitocode.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

@Component
public class AwsCognitoJwtAuthenticationFilter extends GenericFilterBean {

	private static final String ALLOW_HEADERS = "Content-Type";

	private static final String METHODS_HTTP = "GET, POST, PUT , OPTIONS, DELETE";

	private static final String ALL_ORIGIN = "*";

	private static final String HEADER_ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

	private static final String HEADER_ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

	private static final String HEADER_ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

	private static final String EMPTY = "";

	private static final String PREFIX_ROLE = "ROLE_";

	private static final String COGNITO_GROUPS = "cognito:groups";

	private static final String COGNITO_USERNAME = "cognito:username";

	private static final String HEADER_BEARER = "Bearer ";

	private static final String HEADER_AUTHORIZATION = "Authorization";

	private static final Logger logger = LoggerFactory.getLogger(AwsCognitoJwtAuthenticationFilter.class);

	@Autowired
	private JWTVerifier verificadorJWT;

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		Authentication authentication = null;
		HttpServletRequest rq = null;
		String tokenJWT = null;
		
		rq = (HttpServletRequest) request;

		if (rq.getServletPath().indexOf("/security/login") >= 0
				|| rq.getServletPath().indexOf("/security/first-reset-password") >= 0
				|| rq.getServletPath().indexOf("/security/change-password") >= 0
				|| rq.getServletPath().indexOf("/security/signout") >= 0
				|| rq.getServletPath().indexOf("/security/refresh-token") >= 0) {
			filterChain.doFilter(request, response);
		} else {

			try {

				tokenJWT = rq.getHeader(HEADER_AUTHORIZATION);

				if (tokenJWT != null) {

					tokenJWT = tokenJWT.replaceAll(HEADER_BEARER, EMPTY);

					verificadorJWT.verify(tokenJWT);

					JWTClaimsSet tokenCognitoDecodificado = JWTParser.parse(tokenJWT).getJWTClaimsSet();

					String usuarioCognito = tokenCognitoDecodificado.getClaims().get(COGNITO_USERNAME).toString();

					if (usuarioCognito != null) {

						List<String> groups = (List<String>) tokenCognitoDecodificado.getClaims().get(COGNITO_GROUPS);
						List<GrantedAuthority> listaRolesSpring = convertirLista(groups);

						User usuarioTokenJWT = new User(usuarioCognito, EMPTY, listaRolesSpring);

						authentication = new UsernamePasswordAuthenticationToken(usuarioTokenJWT, EMPTY,
								listaRolesSpring);
					}

				}

				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}

			} catch (Exception e) {
				if (e instanceof TokenExpiredException) {
					logger.error("Expiró la sesión del token " + tokenJWT);
				} else {
					logger.error("Error occured while processing Cognito ID Token", e);
				}
				SecurityContextHolder.clearContext();

				HttpServletResponse responseNew = (HttpServletResponse) response;
				responseNew.setHeader(HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, ALL_ORIGIN);
				responseNew.setHeader(HEADER_ACCESS_CONTROL_ALLOW_METHODS, METHODS_HTTP);
				responseNew.setHeader(HEADER_ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);
			}

			filterChain.doFilter(request, response);
		}
	}

	private static List<GrantedAuthority> convertirLista(List<String> rolesCognito) {
		List<GrantedAuthority> listaRolesSpring = new ArrayList<GrantedAuthority>();
		for (String roleCognito : rolesCognito) {
			listaRolesSpring.add(new SimpleGrantedAuthority(PREFIX_ROLE + roleCognito.toUpperCase()));
		}
		return listaRolesSpring;
	}

}
