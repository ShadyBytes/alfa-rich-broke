package com.timur.alfa.giphy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiphyHandler {
    @Value("${giphyApiKey}")
    String giphyApiKey;

    @Value("${giphyBaseUrl}")
    String giphyBaseUrl;

    public String getSearchURL(String query) {
        return giphyBaseUrl + "api_key=" + giphyApiKey + "&q=" + query + "&limit=1";
    }

}
