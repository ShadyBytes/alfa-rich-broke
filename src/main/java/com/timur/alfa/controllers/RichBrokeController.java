package com.timur.alfa.controllers;

import com.timur.alfa.giphy.GiphyHandler;
import com.timur.alfa.openexchange.OpenExchangeFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RichBrokeController {

    @Value("${currencyApiURL}")
    String currencyApiURL;
    @Value("${baseCurrency}")
    String currency;
    @Value("${currencyApiKey}")
    String currencyApiKey;

    final GiphyHandler giphyHandler;
    private final OpenExchangeFeign openExchangeFeign;
    String datePathYesterday;
//    LocalDate current_date = LocalDate.now();
//    datePath = current_date.getYear() + "-" + current_date.getMonth() + "-" +
//            current_date.getDayOfMonth();
//    final String path = "/historical/" + date + ".json";

    public RichBrokeController(GiphyHandler giphyHandler, OpenExchangeFeign openExchangeFeign) {
        this.giphyHandler = giphyHandler;
        this.openExchangeFeign = openExchangeFeign;

        LocalDate current_date = LocalDate.now().minusDays(1);
        this.datePathYesterday = current_date.getYear() + "-" +
                Utils.numberToXXFormatter(current_date.getMonthValue()) + "-" +
                Utils.numberToXXFormatter(current_date.getDayOfMonth());
    }

    @GetMapping("/richorbroke")
    public String getRichOrBrokeGif(@RequestParam(name = "currency") String currency) {
//        return "currency is " + currency + " and URL is " + currencyApiURL;
        return giphyHandler.getSearchURL("Rich");

    }

    @GetMapping("/feign")
    public ResponseEntity getSampleDataByIds(/*@RequestParam("app_id") String appID*/) {
        ResponseEntity responseEntity = openExchangeFeign.getSampleDataByIds(datePathYesterday,
                currencyApiKey);
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }


}
