package br.com.fiap.utils;

import io.smallrye.jwt.build.Jwt;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String generateToken (String id, String nome, String acompanhantes) {
        int expiration = Integer.parseInt(System.getProperty("jwt.expiration.seconds", "21600"));
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        claims.put("acompanhantes", acompanhantes);

        return Jwt.claims(claims)
                .subject(id)
                .issuer(System.getProperty("mp.jwt.verify.issuer", "consulta-certa-api"))
                .expiresIn(expiration)
                .sign();
    }
}
