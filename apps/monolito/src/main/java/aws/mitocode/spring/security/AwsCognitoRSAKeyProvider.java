package aws.mitocode.spring.security;

import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.interfaces.RSAKeyProvider;

public class AwsCognitoRSAKeyProvider implements RSAKeyProvider {

	private final URL aws_kid_store_url;

	public AwsCognitoRSAKeyProvider(String aws_cognito_region, String aws_user_pools_id) {

		try {
			//Este archivo .json está en la carpeta src/main/resources y actualizado con el contenido configurado en su pool cognito
			//El contenido se puede descargar de: https://cognito-idp.REGION-AWS.amazonaws.com/POOL-ID/.well-known/jwks.json
			Resource resource = new ClassPathResource("json_web_key.json");
			System.out.println(resource.getURL());
			this.aws_kid_store_url = resource.getURL();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Invalid URL provided, URL"));
		}
	}

	@Override
	public RSAPublicKey getPublicKeyById(String kid) {
		try {
			JwkProvider provider = new JwkProviderBuilder(aws_kid_store_url).build();
			Jwk jwk = provider.get(kid);
			return (RSAPublicKey) jwk.getPublicKey();
		} catch (Exception e) {
			throw new RuntimeException(
					String.format("Failed to get JWT kid=%s from aws_kid_store_url=%s", kid, aws_kid_store_url));
		}
	}

	@Override
	public RSAPrivateKey getPrivateKey() {
		return null;
	}

	@Override
	public String getPrivateKeyId() {
		return null;
	}

}
