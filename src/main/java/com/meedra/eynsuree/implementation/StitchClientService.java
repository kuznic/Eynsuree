package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.model.StitchCredential;
import com.meedra.eynsuree.repository.StitchCredentialRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.util.ArrayList;



@Slf4j
@Service
public class StitchClientService {

    @Autowired
    private StitchCredentialRepository stitchCredentialRepository;



    @Transactional
    public void saveClientDetails(){

        if(stitchCredentialRepository.findAll().size() == 0){

            var stitchCredential = new StitchCredential();

            JSONParser parser = new JSONParser();

            try {
                Object obj = parser.parse(new FileReader("keys/client.json"));
                JSONObject jsonObject = (JSONObject) obj;

                stitchCredential = StitchCredential
                        .builder()
                        .id((String) jsonObject.get("id"))
                        .scopes((ArrayList<String>) jsonObject.get("allowedScopes"))
                        .grantTypes((ArrayList<String>) jsonObject.get("allowedGrantTypes"))
                        .redirectUrls((ArrayList<String>) jsonObject.get("redirectUrls"))
                        .build();

                stitchCredentialRepository.save(stitchCredential);
            } catch (Exception e) {
                log.error("An error occurred while trying to save credentials");
            }

        }

    }



    @Cacheable(value = "credential")
    public StitchCredential getCredentials(){

        var stitchCredential = stitchCredentialRepository.findAll();
        return stitchCredential.get(0);
    }


}
