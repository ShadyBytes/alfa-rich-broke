package com.timur.alfa.giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy-client", url = "${giphyBaseUrl}")
public interface GiphyFeign {
    @RequestMapping(method = RequestMethod.GET,
            value = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> getGifLinkFromSearch(@RequestParam("api_key") String apiKey,
                                                @RequestParam String q,
                                                @RequestParam(required = false, defaultValue = "1") int limit,
                                                @RequestParam int offset);
}
