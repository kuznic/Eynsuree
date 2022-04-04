package com.meedra.eynsuree.stitch.utility;



import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.Instant;
import java.util.UUID;

public class VerUtils {

    public String generateJwtToken(Vertx vertx, String clientId) throws IOException {
        File publicKeyFile = new File("keys/public.pem");
        File privateKeyFile = new File("keys/private_key.pem");

        String publicKey = Files.readString(publicKeyFile.toPath(), Charset.defaultCharset());
        String privateKey = Files.readString(privateKeyFile.toPath(), Charset.defaultCharset());

        JWTAuth provider = JWTAuth.create(vertx, new JWTAuthOptions()
                .addPubSecKey(new PubSecKeyOptions()
                        .setAlgorithm("RS256")
                        .setBuffer(publicKey))
                .addPubSecKey(new PubSecKeyOptions()
                        .setAlgorithm("RS256")
                        .setBuffer(privateKey)));


        long issuedAt= Instant.now().getEpochSecond();

        long expiresAt =Instant.now().plusSeconds(60).getEpochSecond();

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("client_id", clientId);
        jsonObject.put("aud", "https://secure.stitch.money/connect/token");
        jsonObject.put("iss", clientId);
        jsonObject.put("sub",clientId);
        jsonObject.put("jti", UUID.randomUUID().toString());
        jsonObject.put("iat", issuedAt);
        jsonObject.put("exp", expiresAt);
        jsonObject.put("nbf", issuedAt);


        return provider.generateToken(jsonObject,
                new JWTOptions().setAlgorithm("RS256"));

    }

}


