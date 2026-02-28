package com.winwin.travel.authapi.security.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.winwin.travel.authapi.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.nimbusds.jose.JWSAlgorithm.HS256;

@Service
public final class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationSeconds;

    @Override
    public String generateToken(final UserDetails user) {
        try {
            final JWSHeader header = new JWSHeader
                    .Builder(HS256)
                    .build();

            final JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .claim("preferred_username", user.getUsername())
                    .claim("roles", user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .issueTime(Date.from(Instant.now()))
                    .expirationTime(Date.from(Instant.now().plus(expirationSeconds, ChronoUnit.SECONDS)))
                    .build();

            final SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            final MACSigner signer = new MACSigner(secretKey.getBytes(StandardCharsets.UTF_8));
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (final JOSEException e) {
            throw new JwtException("Error generating JWT", e);
        }
    }
}
