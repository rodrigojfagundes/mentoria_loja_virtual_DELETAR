package jdev.mentoria.lojavirtual.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jdev.mentoria.lojavirtual.ApplicationContextLoad;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

//Essa class JWTTOkenAutenticacaoService, tem basicamente 2 funcoes
//Criar a autenticacao e retornar tambem a autenticacao JWT
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gera o token e da a responsta para o cliente o com JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		// Montagem do Token
		//
		// chama o gerador de Token e adiciona o user
		// e informamos o tempo de expiracao do token q ta na var EXPIRATION_TIME
		// e com signwith informamos o algoritmo de criptografia e a senha
		// para fazer a assinatura do token
		String JWT = Jwts.builder()./*Chama o gerador de token*/
				setSubject(username) /*Adiciona o user*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Temp de expiração*/
		
				// Exe: Bearer *-/hash do token JWT
		/*
		 * Exe: Bearer *-/a*dad9s5d6as5d4s5d4s45dsd5
		 * 4s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d
		 */
		
		//JWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2RyaWdvam9zZWZhZ3VuZGVzQGdtYWlsLmNvbSIsImV4cCI6MTc0MzY1MTY3OH0.y3iHtXn0eM4T6YU_pBXpXlh_qp-HUnScIKruLQvQYbqctxYoUSS5KfS7JNXWX2COFSmPQIEIBIcH3aPXNIugPw";
		String token = TOKEN_PREFIX + " " + JWT;
		
		/*Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamadajava*/
		response.addHeader(HEADER_STRING, token);
		liberacaoCors(response);
		
		/*Usado para ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	// metodo que retorna o usuario validado com o token, caso o usuario
	// nao exista vai retorna null... Ou seja esse metodo vai verificar
	// se as credenciais sao validas, se sim dai retorna o token JWT
	//
	// esse metodo recebe uma requisicao q vem no frontend do tipo
	// HTTPServletRequest de nome request, e HttpServletResponse de nome
	// response
	public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		
		try {
		
		if (token != null) {
			
			// tirando o Bearer no inicio do token
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			//tokenLimpo = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2RyaWdvam9zZWZhZ3VuZGVzQGdtYWlsLmNvbSIsImV4cCI6MTc0MzY1MTY3OH0.y3iHtXn0eM4T6YU_pBXpXlh_qp-HUnScIKruLQvQYbqctxYoUSS5KfS7JNXWX2COFSmPQIEIBIcH3aPXNIugPw";
			
			/*Faz a validacao do token do usuário na requisicao e obtem o USER*/
			String user = Jwts.parser().
					setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject(); /*ADMIN ou Alex*/
			
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad.
						getApplicationContext().
						getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(), 
							usuario.getAuthorities());
				}
				
			}
			
		}
		
		}catch (SignatureException e) {
			response.getWriter().write("Token está inválido.");

		}catch (ExpiredJwtException e) {
			response.getWriter().write("Token está expirado, efetue o login novamente.");
		}
		finally {
			liberacaoCors(response);
		}
		
		return null;
	}
	
	
	// Metodo de configuracao de CORS - fazendo liberacao contra erros de
	// CORS no navegador
	//
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}
	
	

}
