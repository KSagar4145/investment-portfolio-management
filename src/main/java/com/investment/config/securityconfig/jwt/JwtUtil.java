package com.investment.config.securityconfig.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {

	private static final String SECRET_KEY = "cc307e59f5e10a5f53e0a6f0e3d3c9242c704c96299bbfef748e350bb9e6251340940e7f90b5e1f793d303d89648a7b6b8ba7dd1ab8a43cdb1e62e50028973a0209df35b67cfb88c05876ac0bfdb39fbf74cad8357976c1fa80945c3e92cdd8acb41b3a120d402c223174acb0250d1ad3eb799e186503de9fc9366fed4d60ea898405ac775d2872787de7aad09c9d3ddcd7099f55ebcb93d0440ee4489c8f1e57ab43bc7412918e2589f15b630f1dc959c765c4dc19a7490e0f409bfd0438de922fbd8e7354493610c89fdf49503b2c291377e47c40a6e63618cfd31a610051e7749bd7c985b54157b731f82478031c441c593a40d4113911ab19f3d8bdb703a";

	public String generateToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", "USER"); // Example claim

		return Jwts.builder()
				.setClaims(claims)  // Adding claims
				.setSubject(email)   // Setting subject (email or username)
				.setHeaderParam("typ", "JWT")  // Setting header type
				.setIssuedAt(new Date(System.currentTimeMillis())) // Issued time
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // Expiration (5 min )
				.signWith(getSignKey(),SignatureAlgorithm.HS256) // Signing with a secure key
				.compact();
	}


	private Key getSignKey() {
		byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		Key key = Keys.hmacShaKeyFor(keyBytes);
		System.err.println("Generated Secret Key (Base64) key: " + key);
		return key;	
	}


	//	  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..here is logic for JwtAuthFilter>>>>>>>>>>>>>


	// ====================== TOKEN EXTRACTION METHODS ======================

	/*
	 * Extracts the username (subject) from the JWT token. 
	 * The subject of the token is set when generating the JWT.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}


	/*
	 * Extracts the expiration date from the JWT token.
	 *  This is used to check if the token is still valid.
	 */
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}


	/*
	 * Extracts a specific claim from the JWT token. Uses a function to apply a
	 * specific claim retrieval logic.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token); // Extracts all claims first
		return claimsResolver.apply(claims); // Applies the given function to retrieve the specific claim
	}

	/*
	 * Extracts all claims from the JWT token.
	 * This is useful when we need multiple claims from the token.
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey()) // Set the signing key to validate the token
				.build()
				.parseClaimsJws(token) // Parse and validate the JWT
				.getBody(); // Extract the claims (payload)
	}

	// ====================== TOKEN VALIDATION METHODS ======================

	/*
	 * Checks if the JWT token is expired.
	 * Compares the expiration date with the current date.
	 */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date()); // If expiration date is before now, token is expired
	}

	/*
	 * Validates the JWT token against the provided UserDetails.
	 * Ensures that the username in the token matches the user details and that the token is not expired.
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token); // Get username from token
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token); // Check username match and expiration
	}

}





