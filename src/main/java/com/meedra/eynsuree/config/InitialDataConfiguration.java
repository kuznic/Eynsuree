package com.meedra.eynsuree.config;

import com.meedra.eynsuree.implementation.InsuredItemService;
import com.meedra.eynsuree.implementation.StitchClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;


/*
Runs immediately after the Application Starts
 */

@Slf4j
@Configuration
public class InitialDataConfiguration {


    @Autowired
    StitchClientService stitchClientService;


    @Autowired
    InsuredItemService insuredItemService;

    @PostConstruct
    public void postConstruct() throws NoSuchFieldException {
        log.info("Started after Spring boot application !");


        //Load credential information into the database
        stitchClientService.saveClientDetails();


        //load initial insured item detail
        var insuredItemMap = new HashMap<String, String>();
        var insuredItemMap2 = new HashMap<String,String>();

        insuredItemMap.put("model","Toyota Camry");
        insuredItemMap.put("year", "2009");
        insuredItemMap.put("cost", "20000");

        insuredItemMap2.put("model","Mazda X20");
        insuredItemMap2.put("year", "2009");
        insuredItemMap2.put("cost", "20000");

        insuredItemService.updateInsuredItem(1L,insuredItemMap);
        insuredItemService.updateInsuredItem(2L,insuredItemMap2);

    }

}