package com.timur.alfa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RichBrokeController {

    @GetMapping("/richorbroke")
    public String getRichOrBrokeGif() {
        return "hello";
    }

}
