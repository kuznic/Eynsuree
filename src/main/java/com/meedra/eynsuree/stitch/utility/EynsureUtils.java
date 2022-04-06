package com.meedra.eynsuree.stitch.utility;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.meedra.eynsuree.dto.StitchPaymentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;


@Slf4j
public class EynsureUtils {

    public static String generateRandomStrings(){

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }


    public static StitchPaymentResponseDto parsePaymentResponse(String json) {
        var stitchPaymentResponsePayload = new StitchPaymentResponseDto();
        LinkedTreeMap<String, Object> treeMap = new Gson().<LinkedTreeMap<String, Object>>fromJson(json, LinkedTreeMap.class);
        treeMap.entrySet().stream().forEach(e -> {
            Object value = e.getValue();

            log.info(value.toString());

            if (value instanceof LinkedTreeMap) {
                LinkedTreeMap<String, Object> data = (LinkedTreeMap) value;

                data.entrySet().stream().forEach(f ->{
                    Object val2 = f.getValue();

                    LinkedTreeMap<String, Object> data2 = (LinkedTreeMap) val2;

                    data2.entrySet().stream().forEach(g ->{
                        Object val3 = g.getValue();

                        LinkedTreeMap<String, Object> data3 = (LinkedTreeMap) val3;

                        stitchPaymentResponsePayload.setId((String) data3.get("id"));
                        stitchPaymentResponsePayload.setUrl((String) data3.get("url"));
                    });
                });
            }
        });
        return stitchPaymentResponsePayload;
    }


    public static  String getDebitUrl(String stitchUrl,String redirectUrl){

        redirectUrl.replaceAll(":", "%3A");
        redirectUrl.replaceAll("//", "%2F");

        return stitchUrl + "?redirect_uri=" + redirectUrl;



    }





}
