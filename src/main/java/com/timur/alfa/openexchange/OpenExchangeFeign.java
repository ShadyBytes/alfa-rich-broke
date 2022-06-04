package com.timur.alfa.openexchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchange-client", url = "${currencyApiURL}")
public interface OpenExchangeFeign {


    @RequestMapping(method = RequestMethod.GET,
            value = "/historical/{date}.json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> getCurrencyInfoForYesterday(@PathVariable String date,
                                                       @RequestParam("app_id") String appID);

    @RequestMapping(method = RequestMethod.GET,
            value = "/latest.json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<String> getCurrencyInfoForToday(@RequestParam("app_id") String appID);

}
