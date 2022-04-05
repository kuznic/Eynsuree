package com.meedra.eynsuree.stitch.service;


import com.meedra.eynsuree.implementation.StitchClientService;
import com.meedra.eynsuree.model.ClientAssertionToken;
import com.meedra.eynsuree.repository.ClientAssertionTokenRepository;
import com.meedra.eynsuree.stitch.utility.VerUtils;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;


@Slf4j
@Service
public class AccessionTokenService {

    @Autowired
    Vertx vertx;

    @Autowired
    StitchClientService stitchClientService;

    @Autowired
    ClientAssertionTokenRepository clientAssertionTokenRepository;


    @Scheduled(fixedRate =120000)
    @Transactional
    private void  generateAccessionToken() throws IOException, OutOfMemoryError {
        log.info("Generating new AccessionToken");


        var assertionToken = new ClientAssertionToken();

        VerUtils verUtils = new VerUtils();
        var clientCredential = stitchClientService.getCredentials();
        String clientId = clientCredential.getId();

        var assertionTokenList = clientAssertionTokenRepository.findAll();

        if(assertionTokenList.size() == 0){
            assertionToken.setAssertionToken(verUtils.generateJwtToken(vertx,clientId));
        }else{

            assertionToken = assertionTokenList.get(0);
            assertionToken.setAssertionToken(verUtils.generateJwtToken(vertx,clientId));
        }

        clientAssertionTokenRepository.save(assertionToken);

        log.info(assertionToken.toString());


        //return verUtils.generateJwtToken(vertx,clientId);


    }



    public String getAssertionToken(){

        return clientAssertionTokenRepository
                .findAll()
                .get(0)
                .getAssertionToken();
    }
}
