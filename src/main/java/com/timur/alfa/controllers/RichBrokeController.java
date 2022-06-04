package com.timur.alfa.controllers;

import com.timur.alfa.giphy.GiphyHandler;
import com.timur.alfa.openexchange.OpenExchangeFeign;
import org.json.JSONObject;
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
    @Value("${currencyToCompare}")
    String currency;
    @Value("${currencyApiKey}")
    String currencyApiKey;

    final GiphyHandler giphyHandler;
    private final OpenExchangeFeign openExchangeFeign;
    String datePathYesterday;


    public RichBrokeController(GiphyHandler giphyHandler, OpenExchangeFeign openExchangeFeign) {
        this.giphyHandler = giphyHandler;
        this.openExchangeFeign = openExchangeFeign;

        LocalDate current_date = LocalDate.now().minusDays(3);
        this.datePathYesterday = current_date.getYear() + "-" +
                Utils.numberToXXFormatter(current_date.getMonthValue()) + "-" +
                Utils.numberToXXFormatter(current_date.getDayOfMonth());
    }

    @GetMapping("/latest")
    public ResponseEntity<String> latest() {
//        return "currency is " + currency + " and URL is " + currencyApiURL;
//        return giphyHandler.getSearchURL("Rich");
        return openExchangeFeign.getCurrencyInfoForToday(currencyApiKey);
    }

    @GetMapping("/richorbroke")
    public String getCurrencyInfoForYesterday(/*@RequestParam(name = "currency") String currency*/) {
        ResponseEntity<String> responseEntityYesterday = openExchangeFeign.getCurrencyInfoForYesterday(datePathYesterday,
                currencyApiKey);
        ResponseEntity<String> responseEntityToday = openExchangeFeign.getCurrencyInfoForToday(currencyApiKey);

        JSONObject jsonObject = new JSONObject(responseEntityYesterday.getBody());
        Double yesterdayValue = jsonObject.getJSONObject("rates").getDouble(currency);

        jsonObject = new JSONObject(responseEntityToday.getBody());
        Double todayValue = jsonObject.getJSONObject("rates").getDouble(currency);

        return "" + yesterdayValue + " and today is " + todayValue;


//        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
//        throw new RuntimeException(String.valueOf(jsonObject.getJSONObject("rates").getDouble(currency)));
//        throw new RuntimeException(String.valueOf(responseEntity.getBody().toString()));
//        return new ResponseEntity<>(jsonObject.getJSONObject("rates"), HttpStatus.OK);
    }


}
