package br.com.fiap.utils;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class JwtUtils {
    private static final String ISSUER;
    private static final int EXPIRATION;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        ISSUER = bundle.getString("mp.jwt.verify.issuer");
        EXPIRATION = Integer.parseInt(bundle.getString("jwt.expiration.seconds"));
    }

    public static String generateToken (
        String id,
        String nome,
        String email,
        String telefone,
        String acompanhantes,
        String dadosSaude
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        claims.put("email", email);
        claims.put("telefone", telefone);
        claims.put("acompanhantes", acompanhantes);
        claims.put("dadosSaude", dadosSaude);

        JwtClaimsBuilder builder = Jwt.claims(claims)
                .subject(id)
                .issuer(ISSUER)
                .expiresIn(EXPIRATION);

        return builder.sign();
    }
}
