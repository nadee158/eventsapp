package com.janaka.projects.services.web.security.utils;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.compression.CompressionCodecs;

// reference
// http://blog.jdriven.com/2014/10/stateless-spring-security-part-2-stateless-authentication/
// https://github.com/jwtk/jjwt

public final class TokenHandler {

  private static final String HMAC_ALGO = "HmacSHA256";

  private final Key key;

  public TokenHandler(byte[] secretKey) {
    this.key = new SecretKeySpec(secretKey, HMAC_ALGO);
  }

  public String createTokenForUser(String sessionUUID) {
    String s = Jwts.builder().compressWith(CompressionCodecs.DEFLATE).setSubject(sessionUUID)
        .signWith(SignatureAlgorithm.HS512, key).compact();
    System.out.println(s);
    return s;
  }


  public boolean isValidToken(String sessionUUID, String token) {
    boolean isValid = false;
    try {
      Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
      // OK, we can trust this JWT
      isValid = jws.getBody().getSubject().equals(sessionUUID);
    } catch (SignatureException e) {
      e.printStackTrace();
    }
    return isValid;
  }

}
