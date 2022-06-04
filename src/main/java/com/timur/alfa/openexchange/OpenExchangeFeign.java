package com.timur.alfa.openexchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@FeignClient(value = "exchange-client", url = "${currencyApiURL}")
public interface OpenExchangeFeign {


    @RequestMapping(method = RequestMethod.GET,
            value = "/historical/{dateToCompareWith}.json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> getSampleDataByIds(@PathVariable String dateToCompareWith,
                                              @RequestParam("app_id") String appID);

}
