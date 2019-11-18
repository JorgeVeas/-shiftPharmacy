package com.pharmacy.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pharmacy.domain.Commune;
import com.pharmacy.service.CommuneService;
import com.pharmacy.util.Constants;
import com.pharmacy.wsclient.WSCommuneClient;

@Component
public class CommuneImpl implements CommuneService{

    @Autowired
    private WSCommuneClient wsCommuneClient;
    
    /**
     * 
     * @param idRegion
     * @return listCommune
     * @throws IOException
     */
    public List<Commune> getCommmune(String idRegion) throws IOException{
        String responseCommune = wsCommuneClient.getCommune(idRegion);
        responseCommune = responseCommune.replaceAll(Constants.SEPARATOR, Constants.SEPARATOR_REPLACE);
        String[] communes = responseCommune.split(Constants.LINE_BREAK);
        
        List<Commune> listCommune = new ArrayList<>();
        if(null != communes && communes.length > 0) {
            for(int i = 1; i < communes.length; i++) {
                if(!communes[i].contains(Constants.SHOOSE_COMMUNE)) {
                    Commune commune = new Commune();
                    Document doc = Jsoup.parse(communes[i]);
                    commune.setName(doc.body().text());
                    listCommune.add(commune);
                }
            }
        }
        
        return listCommune;
        
    }
}
