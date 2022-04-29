package com.meedra.eynsuree.stitch.service;

import com.google.gson.Gson;
import com.meedra.eynsuree.config.StitchParameters;
import com.meedra.eynsuree.dto.StitchPaymentRequestDto;
import com.meedra.eynsuree.implementation.StitchClientService;
import com.meedra.eynsuree.stitch.utility.Mutations;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ClientTokenService {

    private final StitchClientService stitchClientService;
    private final AccessionTokenService accessionTokenService;
    private final Gson gson;
    private final StitchParameters stitchParameters;

    @Autowired
    public ClientTokenService(StitchClientService stitchClientService, AccessionTokenService accessionTokenService,
                              Gson gson, StitchParameters stitchParameters) {
        this.stitchClientService = stitchClientService;
        this.accessionTokenService = accessionTokenService;
        this.gson = gson;
        this.stitchParameters = stitchParameters;
    }


    public String callStitchGraphQLService(StitchPaymentRequestDto paymentRequest) {

        var clientToken = getClientToken();

        if(clientToken.size() == 0){
            return "";
        }

        String userToken = clientToken.get("access_token");
        String query = buildPaymentQuery(paymentRequest);
        String url = stitchParameters.getGraphqlServer();

        Client client = Client.create();

        WebResource resource = client.resource(url);

        ClientResponse response = resource.
                accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+ userToken)
                .post(ClientResponse.class, query);

        return  response.getEntity(String.class);

        //return gson.fromJson(jsonResponse, HashMap.class);


    }



    private String buildPaymentQuery(StitchPaymentRequestDto paymentRequest) {

        HashMap<String, String> payloadMap = new HashMap<>();
        var mutations = new Mutations();

        payloadMap.put("query",mutations.getCreatePaymentRequestMutation());
        payloadMap.put("variables", gson.toJson(paymentRequest));

        return gson.toJson(payloadMap);

    }

    private HashMap<String,String> getClientToken() {

        var credentials = stitchClientService.getCredentials();
        HashMap<String, String> payloadMap = new HashMap<>();
        StringBuilder result = new StringBuilder();
        Client client = Client.create();
        boolean first = true;
        String jsonResponse;
        String assertionToken = accessionTokenService.getAssertionToken();
        String clientId = credentials.getId();

//        List<String> scopes = credentials
//                .getScopes()
//                .stream()
//                .filter(element -> !element.equals("openid"))
//                .collect(Collectors.toList());
        List<String> scopes = List.of("client_paymentrequest");


        payloadMap.put("grant_type", stitchParameters.getGrantType());
        payloadMap.put("client_id", clientId);
        payloadMap.put("scope", scopes.get(0));
        payloadMap.put("audience", stitchParameters.getAudience());
        payloadMap.put("client_assertion_type", stitchParameters.getClientAssertionType());
        payloadMap.put("client_assertion", assertionToken);

        WebResource resource = client.resource(stitchParameters.getTokenUrl());


        for (Map.Entry<String, String> entry : payloadMap.entrySet()) {
            if (first) first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }




            ClientResponse response = resource.
                    accept(MediaType.APPLICATION_JSON)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(ClientResponse.class, result.toString());

            log.info("TOKEN: {}", assertionToken);

            log.info("Response:{}", response);
            jsonResponse = response.getEntity(String.class);
            log.info(jsonResponse);
            log.info(Arrays.toString(scopes.toArray()));


            try{

                return gson.fromJson(jsonResponse, HashMap.class);

        }catch (IllegalStateException | IllegalThreadStateException e){
            return new HashMap();
        }

    }
}
