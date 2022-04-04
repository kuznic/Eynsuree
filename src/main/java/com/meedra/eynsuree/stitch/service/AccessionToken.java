package com.meedra.eynsuree.stitch.service;


import com.meedra.eynsuree.implementation.StitchClientService;
import com.meedra.eynsuree.stitch.utility.VerUtils;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class AccessionToken {

    @Autowired
    Vertx vertx;

    @Autowired
    StitchClientService stitchClientService;


   // @Scheduled(fixedRate = 60000, initialDelay = 1800000)
    public String  generateAccessionToken() throws IOException, OutOfMemoryError {

        VerUtils verUtils = new VerUtils();
        var clientCredential = stitchClientService.getCredentials();
        String clientId = clientCredential.getId();

        System.out.println("Your jwt:");
        return verUtils.generateJwtToken(vertx,clientId);


    }
}
